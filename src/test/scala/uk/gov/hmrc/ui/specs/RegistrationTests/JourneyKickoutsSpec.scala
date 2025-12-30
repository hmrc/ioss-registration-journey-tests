/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs.RegistrationTests

import uk.gov.hmrc.ui.pages.{Auth, EmailVerification, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class JourneyKickoutsSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("Other Journey Kickout pages") {

    Scenario("Kickout when the user does not have a Fixed Establishment") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers the filter questions and first part of the journey")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("ni-based")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user adds other registration without a fixed establishment")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("France")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("no")

      And("the user is on the cannot-register-need-to-operate-as-fe page")
      registration.checkJourneyUrl("cannot-register-need-to-operate-as-fe/1")

      And("the user continues and goes back to the beginning of the section")
      registration.continue()
      registration.checkJourneyUrl("tax-in-eu")
    }

    Scenario(
      "Can resume registration journey for an already entered country with a Fixed Establishment following the Fixed Establishment kickout page"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers the filter questions and first part of the journey")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("ni-based")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user adds other registration with a fixed establishment")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Ireland")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("IE1234777WI")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Irish Goods")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("168 George Square", "", "Dublin", "", "")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()

      Then("the user adds other registration without fixed establishment")
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/2")
      registration.selectCountry("Belgium")
      registration.checkJourneyUrl("eu-fixed-establishment/2")
      registration.answerRadioButton("no")

      And("the user is on the cannot-register-need-to-operate-as-fe page")
      registration.checkJourneyUrl("cannot-register-need-to-operate-as-fe/2")

      And("the user presses continue and carries on with the rest of the journey")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.first-website.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Trader Name", "07771117771", "test@testemail.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Trader Name", "ABCDEF2A", "GB33BUKB20201555555555")

      And("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()
      registration.checkJourneyUrl("successful")
    }
  }
}

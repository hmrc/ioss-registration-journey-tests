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

class RegistrationFailuresSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("Failures and kickouts relating to user's registration") {

    Scenario("User is already registered for the IOSS service") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "alreadyRegistered", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers the filter questions")
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

      Then("the user is on the already-registered page")
      registration.checkJourneyUrl("already-registered")

      When("the user clicks on the Back to your account button")
      registration.clickLink("backToYourAccount")

      Then("the user is redirected to the returns dashboard")
      registration.checkDashboardJourneyUrl("your-account")
    }

    Scenario("Error creating enrolment on submission of registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("222222233", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers all of the filter questions")
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

      Then("the user only adds minimal details to their registration")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("https://www.onlywebsite.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Another Trader Name", "", "GB29NWBK60161331926819")

      And("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the error-submitting-registration page")
      registration.checkJourneyUrl("error-submitting-registration")
    }

    Scenario("Error from ETMP when submitting registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("666000001", "Organisation", "vatOnly", "etmpCredId")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers all of the filter questions")
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

      Then("the user only adds minimal details to their registration")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("https://www.onlywebsite.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Another Trader Name", "", "GB29NWBK60161331926819")

      And("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the error-submitting-registration page")
      registration.checkJourneyUrl("error-submitting-registration")

      When("the trader accesses their IOSS Registration again")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("666000001", "Organisation", "vatOnly", "etmpCredIdRetrieve")

      Then("the trader is on the continue-registration page")
      registration.checkJourneyUrl("continue-registration")

      And("the user answers yes on the continue-registration page")
      registration.clickLink("continueProgress")
      registration.continue()

      Then("the user is on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
    }

    Scenario("ETMP error on submission of registration when account already exists") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("666000000", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers all of the filter questions")
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

      Then("the user only adds minimal details to their registration")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("https://www.onlywebsite.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Another Trader Name", "", "GB29NWBK60161331926819")

      And("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the account-restore-error page")
      registration.checkJourneyUrl("account-restore-error")
    }
  }
}

/*
 * Copyright 2026 HM Revenue & Customs
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

class VatGroupSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("VAT group journeys") {

    Scenario("IOSS Registration journey for NI Trader who is part of a VAT group - no previous registrations") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("777777779", "Organisation", "vatOnly", "registration")
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

      And("the user is on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")

      And("the user enters their registration information with no previous registrations")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user is not shown the fixed establishment section of the journey")
      registration.checkJourneyUrl("website-address/1")
      registration.continue()
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Another Trader Name", "", "GB29NWBK60161331926819")

      And("the fixed establishments section is not displayed on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.noFixedEstablishments()
      registration.noWebsitesAdded()

      And("the user can submit their registration successfully")
      registration.submit()
      registration.checkJourneyUrl("successful")
    }

    Scenario("IOSS Registration journey for NI Trader who is part of a VAT group - has previous registrations") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("777777779", "Organisation", "vatOnly", "registration")
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

      And("the user is on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")

      And("the user enters their registration information with previous registrations")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Hungary")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/1/1")
      registration.enterAnswer("HU11122233")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user is not shown the fixed establishment section of the journey")
      registration.checkJourneyUrl("website-address/1")
      registration.continue()
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Another Trader Name", "", "GB29NWBK60161331926819")

      And("the fixed establishments section is not displayed on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.noFixedEstablishments()
      registration.noWebsitesAdded()

      And("the user can submit their registration successfully")
      registration.submit()
      registration.checkJourneyUrl("successful")
    }

    Scenario(
      "IOSS Amend Registration journey for NI Trader who is now part of a VAT group but has fixed establishments in their ETMP registration"
    ) {

      Given("the trader accesses the IOSS Registration Service to view their registration")
      auth.goToAuthorityWizard()

      When("the trader is now part of a VAT group")
      auth.loginUsingAuthorityWizard("777777779", "Organisation", "amendAccount", "amend")

      //Further implementation of an intercept page to be added in VEIOSS-903
      Then("the trader's existing fixed establishments are removed from the registration")
      registration.checkJourneyUrl("change-your-registration")
      registration.noFixedEstablishments()

      And("the trader can submit an amendment to their registration successfully")
      registration.selectChangeOrRemoveLink(
        "add-website-address\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-website-address?waypoints=change-your-registration")
      registration.selectChangeOrRemoveLink(
        "remove-website-address\\/1\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("remove-website-address/1?waypoints=change-your-registration")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-website-address?waypoints=change-your-registration")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")
    }

    Scenario(
      "IOSS Rejoin Registration journey for NI Trader who is now part of a VAT group but has fixed establishments in their ETMP registration"
    ) {

      Given("the trader accesses the IOSS Registration Service to rejoin")
      auth.goToAuthorityWizard()

      When("the trader is now part of a VAT group")
      auth.loginUsingAuthorityWizard("777777779", "Organisation", "fullRejoin", "rejoin")

      //Further implementation of an intercept page to be added in VEIOSS-903
      Then("the trader's existing fixed establishments are removed from the registration")
      registration.checkJourneyUrl("rejoin-registration")
      registration.noFixedEstablishments()

      And("the trader can submit their registration to rejoin the service")
      registration.submit()
      registration.checkJourneyUrl("successful-rejoin")
    }
  }
}

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

package uk.gov.hmrc.ui.specs.AmendTests

import uk.gov.hmrc.ui.pages.{Auth, EmailVerification, PreviousRegistration, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class MultipleIOSSNumbersSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification
  private val previousRegistration        = PreviousRegistration

  Feature("Multiple IOSS Number Registration journeys") {

    Scenario("An IOSS registered user with one previous registration can amend the correct sections of each registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "onePreviousRegistration", "amend")

      When("the user's current registration is displayed")
      registration.checkJourneyUrl("change-your-registration")
      previousRegistration.checkIossNumber("IM9007230000")

      Then("the user clicks on the View or change your previous registration link")
      registration.clickLink("change-previous-registrations")

      And("the user selects yes to view their one previous registration")
      registration.checkJourneyUrl("change-your-previous-registration?waypoints=change-your-registration")
      registration.answerRadioButton("yes")

      And("the intermediary's previous registration is displayed")
      registration.checkJourneyUrl("change-a-previous-registration")
      previousRegistration.checkIossNumber("IM9006230000")

      Then("the user amends the contact details on the previous registration")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-a-previous-registration"
      )

      Then("the user amends some of their contact details")
      registration.checkJourneyUrl("business-contact-details")
      registration.updateField("fullName", "Previous Single Registration Trader")
      registration.updateField("telephoneNumber", "01234567891")
      registration.updateField("emailAddress", "previous-single-registration-test@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("previousRegistration")
      registration.checkJourneyUrl("change-a-previous-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-a-previous-registration"
      )

      Then("the user amends some of their bank details on their previous registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Previous Single Registration Trader Name")
      registration.updateField("iban", "GB29NWBK60161331926819")
      registration.continue()
      registration.checkJourneyUrl("change-a-previous-registration")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      previousRegistration.checkAmendedAnswers("IM9006230000")

      When("the user manually navigates to the start-amend-journey page")
      registration.goToPage("start-amend-journey")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user's current registration is displayed")
      previousRegistration.checkIossNumber("IM9007230000")

      And("the user can remove and amend trading name details in their current registration")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/1\\?waypoints\\=change-add-uk-trading-name\\%2Cchange-your-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("an amended current trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("a new current trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user can remove other registration details from their current registration")
      registration.selectChangeOrRemoveLink(
        "tax-in-eu\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("remove-all-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      previousRegistration.checkAmendedAnswers("IM9007230000")
    }

    Scenario("An IOSS registered user with multiple previous registrations can amend the correct sections of each registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "multiplePreviousRegistrations", "amend")

      When("the user's current registration is displayed")
      registration.checkJourneyUrl("change-your-registration")
      previousRegistration.checkIossNumber("IM9007230003")

      Then("the user clicks on the View or change your previous registration link")
      registration.clickLink("change-previous-registrations")

      And("the user selects one of their previous registrations")
      registration.checkJourneyUrl("change-your-previous-registrations?waypoints=change-your-registration")
      previousRegistration.selectPreviousRegistration("IM9007230001")

      And("the intermediary's previous registration is displayed")
      registration.checkJourneyUrl("change-a-previous-registration")
      previousRegistration.checkIossNumber("IM9007230001")

      Then("the user amends the contact details on the previous registration")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-a-previous-registration"
      )

      Then("the user amends some of their contact details")
      registration.checkJourneyUrl("business-contact-details")
      registration.updateField("fullName", "Previous Multiple Registration Trader")
      registration.updateField("telephoneNumber", "+17771117771")
      registration.updateField("emailAddress", "previous-registration-test-multiple@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("previousRegistration")
      registration.checkJourneyUrl("change-a-previous-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-a-previous-registration"
      )

      Then("the user amends some of their bank details on their previous registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Previous Multiple Registration Trader Name")
      registration.updateField("iban", "GB29NWBK60161331926819")
      registration.continue()
      registration.checkJourneyUrl("change-a-previous-registration")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      previousRegistration.checkAmendedAnswers("IM9007230001")

      When("the user manually navigates to the start-amend-journey page")
      registration.goToPage("start-amend-journey")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user's current registration is displayed")
      previousRegistration.checkIossNumber("IM9007230003")

      Then("the user clicks on the View or change your previous registration link")
      registration.clickLink("change-previous-registrations")

      And("the user selects one of their previous registrations")
      registration.checkJourneyUrl("change-your-previous-registrations?waypoints=change-your-registration")
      previousRegistration.selectPreviousRegistration("IM9007230002")

      And("the intermediary's previous registration is displayed")
      registration.checkJourneyUrl("change-a-previous-registration")
      previousRegistration.checkIossNumber("IM9007230002")

      Then("the user amends the contact details on the previous registration")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-a-previous-registration"
      )

      Then("the user amends some of their contact details")
      registration.checkJourneyUrl("business-contact-details")
      registration.updateField("fullName", "Previous Multiple Registration Trader 2")
      registration.updateField("emailAddress", "previous-registration-test-another@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("secondPreviousRegistration")
      registration.checkJourneyUrl("change-a-previous-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-a-previous-registration"
      )

      Then("the user amends some of their bank details on their previous registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Previous Multiple Registration Trader Name 2")
      registration.continue()
      registration.checkJourneyUrl("change-a-previous-registration")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      previousRegistration.checkAmendedAnswers("IM9007230002")

      When("the user manually navigates to the start-amend-journey page")
      registration.goToPage("start-amend-journey")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user's current registration is displayed")
      previousRegistration.checkIossNumber("IM9007230003")

      And("the user can remove and amend website details in their current registration")
      registration.selectChangeOrRemoveLink(
        "add-website-address\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-website-address")
      registration.selectChangeOrRemoveLink(
        "remove-website-address\\/2\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("remove-website-address/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-website-address")
      registration.selectChangeOrRemoveLink(
        "website-address\\/1\\?waypoints\\=change-add-website-address\\%2Cchange-your-registration"
      )
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.amended-website-name-multiple-ioss.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("https://www.2ndmultipleiosswebsite.eu")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user can add previous registration details to their current registration")
      registration.selectChangeOrRemoveLink(
        "previous-schemes-overview\\?waypoints\\=change-your-registration"
      )

      Then("the user adds answers within the previous schemes section")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "previous-scheme-answers\\/2\\?waypoints\\=change-previous-schemes-overview\\%2Cchange-your-registration"
      )
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-scheme/2/2")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/2")
      registration.enterAnswer("CY12345678X")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/3")
      registration.selectCountry("Finland")
      registration.checkJourneyUrl("previous-scheme/3/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/3/1")
      registration.enterAnswer("EU222456788")
      registration.checkJourneyUrl("previous-scheme-answers/3")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      previousRegistration.checkAmendedAnswers("IM9007230003")
    }
  }
}

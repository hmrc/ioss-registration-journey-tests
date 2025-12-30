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

import uk.gov.hmrc.ui.pages.{Auth, EmailVerification, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class AmendRegistrationSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("Amend Registration journeys") {

    Scenario("An IOSS registered user amends registration answers from yes to no for optional sections") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amendAccount", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for Have UK trading name")
      registration.selectChangeOrRemoveLink(
        "have-uk-trading-name\\?waypoints\\=change-your-registration"
      )

      Then("the user amends the answer to no")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")

      And("the user answers yes on the remove-all-trading-names page")
      registration.checkJourneyUrl("remove-all-trading-names")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for Tax in EU")
      registration.selectChangeOrRemoveLink(
        "tax-in-eu\\?waypoints\\=change-your-registration"
      )

      Then("the user amends the answer to no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      And("the user answers yes on the remove-all-tax-details page")
      registration.checkJourneyUrl("remove-all-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("change-your-registration")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      registration.checkAmendedAnswers("yesToNo")
    }

    Scenario("An IOSS registered user cannot remove all previous registrations if they were retrieved from the ETMP registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amendAccount", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user manually navigates to the remove-all-previous-registrations?waypoints=change-your-registration page")
      registration.goToPage("remove-all-previous-registrations?waypoints=change-your-registration")

      Then("the user is on the problem page")
      registration.checkJourneyUrl("remove-all-previous-registrations?waypoints=change-your-registration")
      registration.checkProblemPage()
    }

    Scenario("An IOSS registered user cannot use remove all previous registrations if they were entered during the amend registration journey") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amendMinimalAccount", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for previous-oss")
      registration.selectChangeOrRemoveLink(
        "previous-oss\\?waypoints\\=change-your-registration"
      )

      Then("the user enters previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Cyprus")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM1962223333")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Finland")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EU222456788")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user is on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")

      When("the user manually navigates to the remove-all-previous-registrations?waypoints=change-your-registration page")
      registration.goToPage("remove-all-previous-registrations?waypoints=change-your-registration")

      Then("the user is on the problem page")
      registration.checkJourneyUrl("remove-all-previous-registrations?waypoints=change-your-registration")
      registration.checkProblemPage()
    }

    Scenario("An IOSS registered user cannot remove an individual previous scheme if they were retrieved from the ETMP registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amendAccount", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user manually navigates to the remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview%2Cchange-your-registration page")
      registration.goToPage("remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview%2Cchange-your-registration")

      Then("the user is on the cannot-delete-previous-registrations page")
      registration.checkJourneyUrl("cannot-delete-previous-registrations")
    }

    Scenario("An IOSS registered user cannot remove previous schemes for a country if it was retrieved from the ETMP registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amendAccount", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user manually navigates to the remove-registration/1?waypoints=change-your-registration page")
      registration.goToPage("remove-registration/1?waypoints=change-your-registration")

      Then("the user is on the cannot-delete-previous-registrations page")
      registration.checkJourneyUrl("cannot-delete-previous-registrations")
    }

    Scenario("An IOSS registered user can add details for sections that were previously answered no") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amendMinimalAccount", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for have-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "have-uk-trading-name\\?waypoints\\=change-your-registration"
      )

      And("the user enters a trading name")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("A new trading name in amend journey")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user clicks change for previous-oss")
      registration.checkJourneyUrl("change-your-registration")
      registration.selectChangeOrRemoveLink(
        "previous-oss\\?waypoints\\=change-your-registration"
      )

      Then("the user enters previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Cyprus")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM1962223333")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Finland")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EU222456788")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user clicks change for tax-in-eu")
      registration.checkJourneyUrl("change-your-registration")
      registration.selectChangeOrRemoveLink(
        "tax-in-eu\\?waypoints\\=change-your-registration"
      )

      Then("the user enters other registration details")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Romania")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("RO1234567890")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Romanian Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("1 Street Name", "", "A Town", "", "")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")

      Then("the user can submit their amended registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the correct answers are shown as amended")
      registration.checkAmendedAnswers("noToYes")
    }
  }
}

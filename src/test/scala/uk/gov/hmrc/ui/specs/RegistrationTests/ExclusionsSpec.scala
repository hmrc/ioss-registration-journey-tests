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

import uk.gov.hmrc.ui.pages.{Auth, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class ExclusionsSpec extends BaseSpec {

  private val registration = Registration
  private val auth = Auth

  Feature("OSS Exclusion journeys") {

    Scenario("Kickout in the registration journey when the user is quarantined on the One Stop Shop service") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000025", "Organisation", "ossRegistration", "registration")
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

      Then("the user presses continue on the register-to-use-service page and is redirected to the cannot-register-quarantined-trader page")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()
      registration.checkJourneyUrl("cannot-register-quarantined-trader")
    }

    Scenario("User can access the registration journey when a quarantine on the One Stop Shop service has expired") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600001414", "Organisation", "ossRegistration", "registration")
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

      Then("the user presses continue on the register-to-use-service page and is able to continue with the registration journey")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()
      registration.checkJourneyUrl("confirm-vat-details")
    }

    Scenario("Kickout when the user returns to a saved registration but is now quarantined on the One Stop Shop service") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000026", "Organisation", "vatOnly", "registration")
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

      Then("the user completes some of the registration data")
      registration.checkJourneyUrl("confirm-vat-details")
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
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      And("the user clicks save and come back later then logs out")
      registration.saveAndComeBackLater()
      registration.checkJourneyUrl("progress-saved")
      registration.clickLink("signOut")

      When("the trader logs back into the IOSS Registration Service with an OSS quarantine")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000026", "Organisation", "ossRegistration", "registration")
      registration.checkJourneyUrl("ioss-registered")

      And("the user re-answers the filter questions")
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

      Then("the trader is on the cannot-register-quarantined-trader page")
      registration.checkJourneyUrl("cannot-register-quarantined-trader")
    }

    Scenario("Kickout when the user attempts to rejoin the service but is quarantined on the One Stop Shop service") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000025", "Organisation", "quarantinedRejoin", "rejoin")
      
      Then("the user is on the cannot-register-quarantined-trader page")
      registration.checkJourneyUrl("cannot-register-quarantined-trader")
    }

    Scenario("User can access the amend registration journey when quarantined on the One Stop Shop service") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000025", "Organisation", "quarantinedAmend", "amend")

      Then("the user is on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      
      And("the user can submit their registration without amending any details")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }
  }
}

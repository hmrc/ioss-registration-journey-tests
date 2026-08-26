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

package uk.gov.hmrc.ui.specs.ChangeTests

import uk.gov.hmrc.ui.pages.{Auth, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class SaveForLaterKickoutSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth

  Feature("Save For Later Kickouts Feature") {

    Scenario("A user returns to a saved registration where their VAT Registration has now expired") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When("the trader's VAT registration has expired")
      auth.loginUsingAuthorityWizard("600000001", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-expired page")
      registration.checkJourneyUrl("cannot-register-vat-expired")
    }

    Scenario("A user returns to a saved registration where their VRN is now used in another active registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When("the trader is now already registered for IOSS")
      auth.loginUsingAuthorityWizard("333333333", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-already-registered page")
      registration.checkJourneyUrl("cannot-register-vat-already-registered?countryCode=EE")
    }

    Scenario("A user returns to a saved registration where their VRN is now quarantined in another country") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When("the trader is now quarantined from IOSS")
      auth.loginUsingAuthorityWizard("333333334", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01 page")
      registration.checkJourneyUrl("cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01")
    }

    Scenario(
      "A user returns to a saved registration where the details provided for a previous registration are now registered for IOSS in another country"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When(
        "the trader has a saved registration containing a previous IOSS scheme that is still active in another country"
      )
      auth.loginUsingAuthorityWizard("100000100", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-already-registered?countryCode=EE page")
      registration.checkJourneyUrl("cannot-register-vat-already-registered?countryCode=EE")
    }

    Scenario(
      "A user returns to a saved registration where the details provided for a previous registration are now quarantined from IOSS in another country"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When(
        "the trader has a saved registration containing a previous IOSS scheme that is quarantined in another country"
      )
      auth.loginUsingAuthorityWizard("100000200", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01 page")
      registration.checkJourneyUrl("cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01")
    }

    Scenario(
      "A user returns to a saved registration where the details provided for another EU registration are currently registered for IOSS in another country - VRN based"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When(
        "the trader has a saved registration containing other EU registration details that is still active in another country"
      )
      auth.loginUsingAuthorityWizard("100000300", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-already-registered?countryCode=EE page")
      registration.checkJourneyUrl("cannot-register-vat-already-registered?countryCode=EE")
    }

    Scenario(
      "A user returns to a saved registration where the details provided for another EU registration are currently registered for IOSS in another country - tax reference based"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When(
        "the trader has a saved registration containing other EU registration details that is still active in another country"
      )
      auth.loginUsingAuthorityWizard("100000400", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-already-registered?countryCode=EE page")
      registration.checkJourneyUrl("cannot-register-vat-already-registered?countryCode=EE")
    }

    Scenario(
      "A user returns to a saved registration where the details provided for another EU registration are quarantined for IOSS in another country - VRN based"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When(
        "the trader has a saved registration containing other EU registration details that is quarantined in another country"
      )
      auth.loginUsingAuthorityWizard("100000500", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01 page")
      registration.checkJourneyUrl("cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01")
    }

    Scenario(
      "A user returns to a saved registration where the details provided for another EU registration are quarantined for IOSS in another country - tax reference based"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When(
        "the trader has a saved registration containing other EU registration details that is quarantined in another country"
      )
      auth.loginUsingAuthorityWizard("100000600", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01 page")
      registration.checkJourneyUrl("cannot-register-vat-quarantined?countryCode=EE&exclusionDate=2026-01-01")
    }
  }
}

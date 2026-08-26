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

      Then("the user is on the expired-vat-cannot-be-used-for-save-and-come-back page")
      registration.checkJourneyUrl("expired-vat-cannot-be-used-for-save-and-come-back")
    }

    Scenario("A user returns to a saved registration where their VRN is now used in another active registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When("the trader's VAT registration has expired")
      auth.loginUsingAuthorityWizard("333333333", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the already-registered-vat-cannot-be-used-for-save-and-come-back?countryCode=EE page")
      registration.checkJourneyUrl("already-registered-vat-cannot-be-used-for-save-and-come-back?countryCode=EE")
    }

    Scenario("A user returns to a saved registration where their VRN is now quarantined in another country") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()

      When("the trader's VAT registration has expired")
      auth.loginUsingAuthorityWizard("333333334", "Organisation", "vatOnly", "savedRegistration")

      Then("the user is on the quarantined-vat-cannot-be-used-for-save-and-come-back?countryCode=EE&exclusionDate=2026-01-01 page")
      registration.checkJourneyUrl("quarantined-vat-cannot-be-used-for-save-and-come-back?countryCode=EE&exclusionDate=2026-01-01")
    }
  }
}

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

class BTASpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth

  Feature("Entering the Registration service via BTA") {

    Scenario(
      "The user enters the service via BTA and is directed back to BTA via the Already EU Registered kickout page"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user manually navigates to the test-only/from-external page")
      registration.goToPage("test-only/from-external")

      Then("the user answers yes on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")
      registration.answerRadioButton("yes")

      And("the user is on the cannot-register-already-registered page")
      registration.checkJourneyUrl("cannot-register-already-registered")

      And("the user clicks on the BTA link")
      registration.clickLink("back-to-your-account")

      And("the user is directed to the BTA service")
      registration.checkBTA()
    }

    Scenario(
      "A Welsh user enters the service via BTA and is first directed to the Welsh transition page before continuing"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user manually navigates to the test-only/from-external?lang=cy page")
      registration.goToPage("test-only/from-external?lang=cy")

      Then("the user is on the no-welsh-service page")
      registration.checkJourneyUrl("no-welsh-service")

      Then("the user continues and is on the first filter question of the service")
      registration.continue()
      registration.checkJourneyUrl("ioss-registered")
    }

    Scenario(
      "A user enters the service via BTA using the en parameter and is directed straight to the registration service"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user manually navigates to the test-only/from-external?lang=en page")
      registration.goToPage("test-only/from-external?lang=en")

      Then("the user is on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")
    }
  }
}

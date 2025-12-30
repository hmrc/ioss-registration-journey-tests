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

class NiNorwayKickoutsSpec extends BaseSpec {

  private val registration = Registration
  private val auth = Auth

  Feature("Northern Ireland and Norway Kickout journeys") {

    Scenario("Kickout when the user answered yes to ni-based but does not have Single Market Indicator") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("500000001", "Organisation", "vatOnly", "registration")
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

      Then("the user presses continue on the register-to-use-service page and is redirected to the cannot-register-no-ni-protocol page")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()
      registration.checkJourneyUrl("cannot-register-no-ni-protocol")
    }

    Scenario("Kickout when the user answered yes to norway-based but does not have an address in Norway") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("500000001", "Organisation", "vatOnly", "registration")
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
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("norway-based")
      registration.answerRadioButton("yes")

      Then("the user presses continue on the register-to-use-service page and is redirected to the cannot-register-not-norwegian-based-business page")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()
      registration.checkJourneyUrl("cannot-register-not-norwegian-based-business")
    }
  }
}

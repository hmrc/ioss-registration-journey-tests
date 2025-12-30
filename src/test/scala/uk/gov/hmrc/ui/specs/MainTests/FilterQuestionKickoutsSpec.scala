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

package uk.gov.hmrc.ui.specs.MainTests

import uk.gov.hmrc.ui.pages.Registration
import uk.gov.hmrc.ui.specs.BaseSpec

class FilterQuestionKickoutsSpec extends BaseSpec {

  private val registration = Registration

  Feature("Filter Question Kickout journeys") {

    Scenario("Trader already registered for the IOSS scheme in another EU country") {

      Given("the trader accesses the IOSS Registration Service")
      registration.goToRegistrationJourney()

      When("the trader is on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")

      Then("the trader selects yes on the ioss-registered page")
      registration.answerRadioButton("yes")

      And("the trader is on the cannot-register-already-registered page")
      registration.checkJourneyUrl("cannot-register-already-registered")
    }

    Scenario("Trader does not sell goods from countries outside the EU to consumers in the EU/NI") {

      Given("the trader accesses the IOSS Registration Service")
      registration.goToRegistrationJourney()

      When("the trader answers no on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")
      registration.answerRadioButton("no")

      Then("the trader selects no on the selling-goods-outside-single-market page")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("no")

      And("the trader is on the cannot-register-ioss page")
      registration.checkJourneyUrl("cannot-register-ioss")
    }

    Scenario("Trader does not sell goods with a consignment value of £135 or less") {

      Given("the trader accesses the IOSS Registration Service")
      registration.goToRegistrationJourney()

      When("the trader answers no on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")
      registration.answerRadioButton("no")

      Then("the trader selects yes on the selling-goods-outside-single-market page")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")

      Then("the trader selects no on the goods-value page")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("no")

      And("the trader is on the do-not-need-to-account-for-eu-vat page")
      registration.checkJourneyUrl("do-not-need-to-account-for-eu-vat")
    }

    Scenario("Trader is not registered for VAT in the UK") {

      Given("the trader accesses the IOSS Registration Service")
      registration.goToRegistrationJourney()

      When("the trader answers no on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")
      registration.answerRadioButton("no")

      Then("the trader selects yes on the selling-goods-outside-single-market page")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")

      Then("the trader selects yes on the goods-value page")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")

      Then("the trader selects no on the registered-for-vat-in-uk page")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("no")

      And("the trader is on the cannot-register-no-vat-in-uk page")
      registration.checkJourneyUrl("cannot-register-no-vat-in-uk")
    }

    Scenario("Trader is not a Northern Irish or Norwegian business") {

      Given("the trader accesses the IOSS Registration Service")
      registration.goToRegistrationJourney()

      When("the trader answers no on the ioss-registered page")
      registration.checkJourneyUrl("ioss-registered")
      registration.answerRadioButton("no")

      Then("the trader selects yes on the selling-goods-outside-single-market page")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")

      Then("the trader selects yes on the goods-value page")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")

      Then("the trader selects yes on the registered-for-vat-in-uk page")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("yes")

      Then("the trader selects no on the ni-based page")
      registration.checkJourneyUrl("ni-based")
      registration.answerRadioButton("no")

      Then("the trader selects no on the norway-based page")
      registration.checkJourneyUrl("norway-based")
      registration.answerRadioButton("no")

      And("the trader is on the cannot-register-no-ni-or-norway-business page")
      registration.checkJourneyUrl("cannot-register-no-ni-or-norway-business")
    }
  }
}

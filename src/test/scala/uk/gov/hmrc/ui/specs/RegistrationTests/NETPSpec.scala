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

class NETPSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth

  Feature("NETP Scenarios") {

    Scenario(
      "Trader with Single Market Indicator set to true and NETP set to false can progress through registration"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("444666661", "Organisation", "vatOnly", "registration")
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

      Then("the user is on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")
    }

    Scenario(
      "Trader with Single Market Indicator set to true and NETP set to true cannot progress through registration"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("444666662", "Organisation", "vatOnly", "registration")
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

      Then("the user is on the cannot-register-non-established-taxable-person page")
      registration.checkJourneyUrl("cannot-register-non-established-taxable-person")
    }

    Scenario(
      "Trader with Single Market Indicator set to false and NETP set to false cannot progress through registration but hits NI Protocol rejection before NETP"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("444666663", "Organisation", "vatOnly", "registration")
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

      Then("the user is on the cannot-register-no-ni-protocol page")
      registration.checkJourneyUrl("cannot-register-no-ni-protocol")
    }

    Scenario(
      "Trader with Single Market Indicator set to false and NETP set to false and has a Norway address can progress through registration"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("444666664", "Organisation", "vatOnly", "registration")
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
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()

      Then("the user is on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")
    }

    Scenario(
      "Trader with Single Market Indicator set to true and NETP set to false and has a Norway address can progress through registration"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("444666665", "Organisation", "vatOnly", "registration")
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
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()

      Then("the user is on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")
    }
  }
}

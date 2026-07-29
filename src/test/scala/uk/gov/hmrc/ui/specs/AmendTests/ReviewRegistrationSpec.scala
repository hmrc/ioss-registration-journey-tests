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

package uk.gov.hmrc.ui.specs.AmendTests

import uk.gov.hmrc.ui.pages.{Auth, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class ReviewRegistrationSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth

  Feature("Change date over two years journeys") {

    Scenario("An IOSS registered user has not updated their registration for two years - no amendments") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "changeDateTwoYears", "dashboard")

      When("the trader starts a return")
      registration.checkDashboardJourneyUrl("your-account")
      registration.clickLink("start-your-return")
      registration.checkDashboardJourneyUrl("IM9003232323/2024-M1/start-return")
      registration.answerRadioButton("yes")

      Then("the trader is on the review-registration page")
      registration.checkDashboardJourneyUrl("IM9003232323/review-registration")

      And("the trader selects the Review your registration details link")
      registration.selectCssLink("start-amend-journey")

      And("the trader can submit their registration without making amendments")
      registration.checkJourneyUrl("change-your-registration")
      registration.checkAmendHeading("review")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the amend confirmation page shows no amended answers")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }

    Scenario("An IOSS registered user has not updated their registration for two years - with amendments") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "changeDateTwoYears", "dashboard")

      When("the trader starts a return")
      registration.checkDashboardJourneyUrl("your-account")
      registration.clickLink("start-your-return")
      registration.checkDashboardJourneyUrl("IM9003232323/2024-M1/start-return")
      registration.answerRadioButton("yes")

      Then("the trader is on the review-registration page")
      registration.checkDashboardJourneyUrl("IM9003232323/review-registration")

      And("the trader selects the Review your registration details link")
      registration.selectCssLink("start-amend-journey")

      And("the trader amends previous registration section")
      registration.checkJourneyUrl("change-your-registration")
      registration.checkAmendHeading("review")
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
      registration.answerRadioButton("no")

      And("the trader can submit their registration after making amendments")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()
      registration.checkJourneyUrl("successful-amend")

      And("the amend confirmation page shows the amended answers")
      registration.checkAmendedAnswers("reviewRegistration")
    }
  }
}

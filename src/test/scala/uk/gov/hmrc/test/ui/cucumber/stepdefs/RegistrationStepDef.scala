/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class RegistrationStepDef extends BaseStepDef {

  Given("the user accesses the IOSS Registration service") { () =>
    CommonPage.goToRegistrationJourney()
  }

  Given("^the user signs into authority wizard as an (Organisation|Agent) Admin with VAT enrolment (.*)$") {
    (role: String, vrn: String) =>
      AuthPage.loginUsingAuthorityWizard(role, vrn)
  }

  Given("^the user signs in as an Organisation (Admin|Non-Admin) (with|without) VAT enrolment (.*)$") {
    (admin: String, vatEnrolment: String, vrn: String) =>
      AuthPage.loginUsingScpStub("Organisation", admin, vatEnrolment, vrn)
      AuthPage.selectMfaSuccess()
  }

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  Given("the user is at the beginning of the signed in IOSS Registration journey") { () =>
    CommonPage.checkJourneyUrl()
  }

  When("""^the user answers (yes|no) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectAnswer(data)
  }

  When("^the user continues through the (.*) page$") { (url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.clickContinue()
  }

  Then("""^the user is on the (.*) page$""") { (url: String) =>
    CommonPage.checkUrl(url)
  }

}

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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import uk.gov.hmrc.test.ui.pages._

class AuthStepDef extends BaseStepDef {

  Given(
    "^the (user|assistant) signs into authority wizard as an (Organisation|Agent) Admin (with|without) (VAT|IOSS and VAT|OSS and VAT) enrolment (.*)$"
  ) { (user: String, role: String, withStatus: String, enrolment: String, vrn: String) =>
    AuthPage.loginUsingAuthorityWizard(user, false, "registration", role, withStatus, enrolment, vrn, "default")
  }

  Given(
    "^the user signs in as an Organisation (Admin|Non-Admin) (with|without) VAT enrolment (.*)$"
  ) { (admin: String, vatEnrolment: String, vrn: String) =>
    AuthPage.loginUsingScpStub("Organisation", admin, vatEnrolment, vrn)
    AuthPage.selectMfaSuccess()
  }

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  When("""^a user with VRN (.*) and no IOSS enrolment accesses the amend registration journey""") { (vrn: String) =>
    AuthPage.loginUsingAuthorityWizard("user", false, "amend", "organisation", "with", "VAT", vrn, "None")
  }

  When("""^a user with VRN (.*) and IOSS Number (.*) accesses the (amend|rejoin) registration journey""") {
    (vrn: String, iossNumber: String, journey: String) =>
      AuthPage.loginUsingAuthorityWizard(
        "user",
        false,
        journey,
        "organisation",
        "with",
        "IOSS and VAT",
        vrn,
        iossNumber
      )
  }

  When(
    """^a user (registered|quarantined) on OSS with VRN (.*) and IOSS Number (.*) accesses the (amend|rejoin) registration journey"""
  ) { (registrationType: String, vrn: String, iossNumber: String, journey: String) =>
    AuthPage.loginUsingAuthorityWizard(
      "user",
      false,
      journey,
      "organisation",
      "with",
      "OSS and IOSS and VAT",
      vrn,
      iossNumber
    )
  }

  Given(
    "^the user signs into authority wizard with a Cred ID and VRN (.*) to (start registration|retrieve saved registration)$"
  ) { (vrn: String, journey: String) =>
    val whichJourney = journey match {
      case "start registration"          => "registration"
      case "retrieve saved registration" => "saved"
      case _                             => throw new Exception("Journey doesn't exist")
    }
    AuthPage.loginUsingAuthorityWizard("user", true, whichJourney, "Organisation", "with", "VAT", vrn, "default")
  }

  Given(
    "^the user logs into the returns service$"
  ) { () =>
    AuthPage.loginUsingAuthorityWizard(
      "user",
      false,
      "unusableStatus",
      "Organisation",
      "with",
      "IOSS and VAT",
      "100000001",
      "IM9009999990"
    )
  }

  When("""^a user with current IOSS Number (.*) and at least one previous IOSS number accesses the returns journey""") {
    (iossNumber: String) =>
      AuthPage.loginUsingAuthorityWizard(
        "user",
        false,
        "amend",
        "Organisation",
        "with",
        "IOSS and VAT",
        "100000001",
        iossNumber
      )
  }

}

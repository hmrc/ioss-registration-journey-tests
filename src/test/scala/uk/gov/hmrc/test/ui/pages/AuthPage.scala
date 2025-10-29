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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object AuthPage extends BasePage {
  def loginUsingAuthorityWizard(
    user: String,
    credId: Boolean,
    journey: String,
    role: String,
    withStatus: String,
    enrolment: String,
    vrn: String,
    iossNumber: String
  ): Unit = {

    val stubUrl: String = TestConfiguration.url("auth-login-stub") + "/gg-sign-in"
    getCurrentUrl should startWith(stubUrl)

    if (credId) {
      if (journey == "registration") {
        CommonPage.generateCredId()
      }
      sendKeys(By.id("authorityId"), CommonPage.retrieveCredId())
    }

    if (journey == "unusableStatus") {
      sendKeys(By.id("redirectionUrl"), TestConfiguration.url("ioss-returns-frontend"))
    } else if (journey == "amend") {
      sendKeys(By.id("redirectionUrl"), TestConfiguration.url("ioss-registration-frontend") + "/start-amend-journey")
    } else if (journey == "rejoin") {
      sendKeys(By.id("redirectionUrl"), TestConfiguration.url("ioss-registration-frontend") + "/start-rejoin-journey")
    } else if (journey == "saved") {
      sendKeys(By.id("redirectionUrl"), TestConfiguration.url("ioss-registration-frontend") + "/continue-on-sign-in")
    } else {
      sendKeys(By.id("redirectionUrl"), TestConfiguration.url("ioss-registration-frontend"))
    }

    if (role == "Agent") {
      selectByValue(By.id("affinityGroupSelect"), "Agent")
      sendKeys(By.id("agentId"), "123")
      sendKeys(By.id("agentCode"), "123")
      sendKeys(By.id("agentFriendlyName"), "Name")
    } else {
      selectByValue(By.id("affinityGroupSelect"), "Organisation")
    }

    if (withStatus == "with") {
      sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
      sendKeys(By.id("input-0-0-name"), "VRN")
      sendKeys(By.id("input-0-0-value"), vrn)

      if (enrolment == "OSS and VAT") {
        sendKeys(By.id("enrolment[1].name"), "HMRC-OSS-ORG")
        sendKeys(By.id("input-1-0-name"), "VRN")
        sendKeys(By.id("input-1-0-value"), vrn)
      } else if (enrolment == "OSS and IOSS and VAT") {
        sendKeys(By.id("enrolment[1].name"), "HMRC-OSS-ORG")
        sendKeys(By.id("input-1-0-name"), "VRN")
        sendKeys(By.id("input-1-0-value"), vrn)
        sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-2-0-name"), "IOSSNumber")
        sendKeys(By.id("input-2-0-value"), iossNumber)
      } else if (enrolment == "IOSS and VAT") {
        sendKeys(By.id("enrolment[1].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-1-0-name"), "IOSSNumber")
        if (iossNumber == "default") {
          sendKeys(By.id("input-1-0-value"), "IM9001234567")
        } else {
          sendKeys(By.id("input-1-0-value"), iossNumber)
        }
      }
      if (iossNumber == "IM9007230000") {
        if (enrolment == "OSS and IOSS and VAT") {
          sendKeys(By.id("enrolment[3].name"), "HMRC-IOSS-ORG")
          sendKeys(By.id("input-3-0-name"), "IOSSNumber")
          sendKeys(By.id("input-3-0-value"), "IM9006230000")
        } else {
          sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
          sendKeys(By.id("input-2-0-name"), "IOSSNumber")
          sendKeys(By.id("input-2-0-value"), "IM9006230000")
        }
      } else if (iossNumber == "IM9007231111") {
        sendKeys(By.id("enrolment[3].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-3-0-name"), "IOSSNumber")
        sendKeys(By.id("input-3-0-value"), "IM9006231111")
      } else if (iossNumber == "IM9007230003") {
        sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-2-0-name"), "IOSSNumber")
        sendKeys(By.id("input-2-0-value"), "IM9007230002")
        sendKeys(By.id("enrolment[3].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-3-0-name"), "IOSSNumber")
        sendKeys(By.id("input-3-0-value"), "IM9007230001")
      }
    }
    click(By.cssSelector("Input[value='Submit']"))
  }

  def loginUsingScpStub(
    affinityGroup: String,
    admin: String,
    vatEnrolment: String,
    vrn: String
  ): Unit = {

    fluentWait.until(ExpectedConditions.urlContains("http://localhost:9597/bas-stub/login"))

    click(By.partialLinkText("Register SCP User"))

    selectByValue(By.id("accountType"), affinityGroup)

    if (admin == "Admin") {
      click(By.id("isAdmin"))
    }
    sendKeys(By.id("groupProfile"), "123")

    if (vatEnrolment == "with") {
      sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
      sendKeys(By.id("enrolment[0].identifier"), "VRN")
      sendKeys(By.id("enrolment[0].value"), vrn)
    }

    click(By.className("submit"))
  }

  def selectMfaSuccess(): Unit = {
    fluentWait.until(ExpectedConditions.urlContains("http://localhost:9597/bas-stub/required-mfa-register"))
    click(By.id("mfaOutcome"))
    click(By.className("submit"))
  }

  def goToAuthStub(): Unit =
    get("http://localhost:9949/auth-login-stub/gg-sign-in/")

}

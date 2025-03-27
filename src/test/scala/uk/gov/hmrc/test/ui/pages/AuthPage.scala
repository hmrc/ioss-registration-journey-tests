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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
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
    driver.getCurrentUrl should startWith(stubUrl)

    if (credId) {
      if (journey == "registration") {
        CommonPage.generateCredId()
      }
      driver.findElement(By.id("authorityId")).clear()
      driver.findElement(By.id("authorityId")).sendKeys(CommonPage.retrieveCredId())
    }

    if (journey == "unusableStatus") {
      driver
        .findElement(By.id("redirectionUrl"))
        .sendKeys(TestConfiguration.url("ioss-returns-frontend"))
    } else if (journey == "amend") {
      driver
        .findElement(By.id("redirectionUrl"))
        .sendKeys(TestConfiguration.url("ioss-registration-frontend") + "/start-amend-journey")
    } else if (journey == "rejoin") {
      driver
        .findElement(By.id("redirectionUrl"))
        .sendKeys(TestConfiguration.url("ioss-registration-frontend") + "/start-rejoin-journey")
    } else if (journey == "saved") {
      driver
        .findElement(By.id("redirectionUrl"))
        .sendKeys(TestConfiguration.url("ioss-registration-frontend") + "/continue-on-sign-in")
    } else {
      driver.findElement(By.id("redirectionUrl")).sendKeys(TestConfiguration.url("ioss-registration-frontend"))
    }

    val selectAffinityGroup = new Select(driver.findElement(By.id("affinityGroupSelect")))
    if (role == "Agent") {
      selectAffinityGroup.selectByValue("Agent")
      driver.findElement(By.id("agentId")).sendKeys("123")
      driver.findElement(By.id("agentCode")).sendKeys("123")
      driver.findElement(By.id("agentFriendlyName")).sendKeys("Name")
    } else {
      selectAffinityGroup.selectByValue("Organisation")
    }

    if (user == "assistant") {
      val selectCredentialRole = new Select(driver.findElement(By.id("credential-role-select")))
      selectCredentialRole.selectByValue("Assistant")
    }

    if (withStatus == "with") {
      driver.findElement(By.id("enrolment[0].name")).sendKeys("HMRC-MTD-VAT")
      driver
        .findElement(By.id("input-0-0-name"))
        .sendKeys("VRN")
      driver
        .findElement(By.id("input-0-0-value"))
        .sendKeys(vrn)
      if (enrolment == "OSS and VAT") {
        driver.findElement(By.id("enrolment[1].name")).sendKeys("HMRC-OSS-ORG")
        driver
          .findElement(By.id("input-1-0-name"))
          .sendKeys("VRN")
        driver
          .findElement(By.id("input-1-0-value"))
          .sendKeys(vrn)
      } else if (enrolment == "OSS and IOSS and VAT") {
        driver.findElement(By.id("enrolment[1].name")).sendKeys("HMRC-OSS-ORG")
        driver
          .findElement(By.id("input-1-0-name"))
          .sendKeys("VRN")
        driver
          .findElement(By.id("input-1-0-value"))
          .sendKeys(vrn)
        driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
        driver
          .findElement(By.id("input-2-0-name"))
          .sendKeys("IOSSNumber")
        driver
          .findElement(By.id("input-2-0-value"))
          .sendKeys(iossNumber)
      } else if (enrolment == "IOSS and VAT") {
        driver.findElement(By.id("enrolment[1].name")).sendKeys("HMRC-IOSS-ORG")
        driver
          .findElement(By.id("input-1-0-name"))
          .sendKeys("IOSSNumber")
        if (iossNumber == "default") {
          driver
            .findElement(By.id("input-1-0-value"))
            .sendKeys("IM9001234567")
        } else {
          driver
            .findElement(By.id("input-1-0-value"))
            .sendKeys(iossNumber)
        }
      }
      if (iossNumber == "IM9007230000") {
        if (enrolment == "OSS and IOSS and VAT") {
          driver.findElement(By.id("enrolment[3].name")).sendKeys("HMRC-IOSS-ORG")
          driver
            .findElement(By.id("input-3-0-name"))
            .sendKeys("IOSSNumber")
          driver
            .findElement(By.id("input-3-0-value"))
            .sendKeys("IM9006230000")
        } else {
          driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
          driver
            .findElement(By.id("input-2-0-name"))
            .sendKeys("IOSSNumber")
          driver
            .findElement(By.id("input-2-0-value"))
            .sendKeys("IM9006230000")
        }
      } else if (iossNumber == "IM9007231111") {
        driver.findElement(By.id("enrolment[3].name")).sendKeys("HMRC-IOSS-ORG")
        driver
          .findElement(By.id("input-3-0-name"))
          .sendKeys("IOSSNumber")
        driver
          .findElement(By.id("input-3-0-value"))
          .sendKeys("IM9006231111")
      } else if (iossNumber == "IM9007230003") {
        driver.findElement(By.id("enrolment[2].name")).sendKeys("HMRC-IOSS-ORG")
        driver
          .findElement(By.id("input-2-0-name"))
          .sendKeys("IOSSNumber")
        driver
          .findElement(By.id("input-2-0-value"))
          .sendKeys("IM9007230002")
        driver.findElement(By.id("enrolment[3].name")).sendKeys("HMRC-IOSS-ORG")
        driver
          .findElement(By.id("input-3-0-name"))
          .sendKeys("IOSSNumber")
        driver
          .findElement(By.id("input-3-0-value"))
          .sendKeys("IM9007230001")
      }
    }
    driver.findElement(By.cssSelector("Input[value='Submit']")).click()
  }

  def loginUsingScpStub(
    affinityGroup: String,
    admin: String,
    vatEnrolment: String,
    vrn: String
  ): Unit = {

    driver.findElement(By.partialLinkText("Register SCP User")).click()

    val select = new Select(driver.findElement(By.id("accountType")))
    select.selectByValue(affinityGroup)

    if (admin == "Admin") {
      driver.findElement(By.id("isAdmin")).click()
    }
    driver.findElement(By.id("groupProfile")).sendKeys("123")

    if (vatEnrolment == "with") {
      driver.findElement(By.id("enrolment[0].name")).sendKeys("HMRC-MTD-VAT")
      driver.findElement(By.id("enrolment[0].identifier")).sendKeys("VRN")
      driver.findElement(By.id("enrolment[0].value")).sendKeys(vrn)
    }

    driver.findElement(By.className("submit")).click()
  }

  def selectMfaSuccess(): Unit = {
    driver.findElement(By.id("mfaOutcome")).click()
    driver.findElement(By.className("submit")).click()
  }

  def goToAuthStub(): Unit =
    driver.navigate().to("http://localhost:9949/auth-login-stub/gg-sign-in/")

}

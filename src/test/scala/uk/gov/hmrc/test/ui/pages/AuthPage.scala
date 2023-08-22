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
import uk.gov.hmrc.test.ui.pages.CommonPage.driver

object AuthPage extends BasePage {
  def loginUsingAuthorityWizard(role: String, vrn: String): Unit = {

    val stubUrl: String = TestConfiguration.url("auth-login-stub") + "/gg-sign-in"
    driver.getCurrentUrl should startWith(stubUrl)

    val redirectUrl: String = TestConfiguration.url(
      "ioss-registration-frontend"
    )

    driver.findElement(By.id("redirectionUrl")).sendKeys(redirectUrl + "/on-sign-in")

    val selectAffinityGroup = new Select(driver.findElement(By.id("affinityGroupSelect")))
    if (role == "Agent") {
      selectAffinityGroup.selectByValue("Agent")
      driver.findElement(By.id("agentId")).sendKeys("123")
      driver.findElement(By.id("agentCode")).sendKeys("123")
      driver.findElement(By.id("agentFriendlyName")).sendKeys("Name")
    } else {
      selectAffinityGroup.selectByValue("Organisation")
    }
    driver.findElement(By.id("enrolment[0].name")).sendKeys("HMRC-MTD-VAT")
    driver
      .findElement(By.id("input-0-0-name"))
      .sendKeys("VRN")
    driver
      .findElement(By.id("input-0-0-value"))
      .sendKeys(vrn)
    driver.findElement(By.cssSelector("Input[value='Submit']")).click()
  }

  def loginUsingScpStub(affinityGroup: String, admin: String, vatEnrolment: String, vrn: String): Unit = {

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

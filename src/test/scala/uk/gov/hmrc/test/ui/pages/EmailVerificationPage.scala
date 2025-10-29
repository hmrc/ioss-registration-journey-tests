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

object EmailVerificationPage extends BasePage {

  def goToEmailVerificationPasscodeGeneratorUrl(): Unit = {

    val testOnlyUrl =
      "http://localhost:10190/pay-vat-on-goods-sold-to-eu/register-for-import-one-stop-shop/test-only/get-passcodes"
    get(testOnlyUrl)

    fluentWait.until(ExpectedConditions.urlContains(testOnlyUrl))
  }

  def goToEmailVerificationUrl(journeyId: String, mode: String): Unit = {
    val url = mode match {
      case "registration"                                                       => "bank-account-details"
      case "change answers"                                                     => "check-your-answers"
      case "amend registration"                                                 => "change-your-registration"
      case "amend previous registration" | "second amend previous registration" => "change-a-previous-registration"
      case "rejoin registration"                                                => "rejoin-registration"
      case _                                                                    =>
        throw new Exception("URL doesn't exist")
    }

    val emailVerificationUrl =
      s"http://localhost:9890/email-verification/journey/$journeyId/passcode?continueUrl=/pay-vat-on-goods-sold-to-eu/register-for-import-one-stop-shop/$url&origin=IOSS&service=ioss-registration-frontend"
    get(emailVerificationUrl)

    fluentWait.until(ExpectedConditions.urlContains(emailVerificationUrl))
  }

  def enterPasscode(passcode: String): Unit = {
    sendKeys(By.id("passcode"), passcode)
    click(By.className("govuk-button"))
  }

  def checkInterceptPage(): Unit = {
    val interceptUrl = TestConfiguration.url("ioss-returns-frontend") + "/intercept-unusable-email"
    fluentWait.until(ExpectedConditions.urlContains(interceptUrl))
    getCurrentUrl should startWith(interceptUrl)
  }

  def checkBusinessContactDetails(): Unit =
    getCurrentUrl should startWith(
      TestConfiguration.url(
        "ioss-registration-frontend"
      ) + "/business-contact-details?waypoints=change-your-registration"
    )
}

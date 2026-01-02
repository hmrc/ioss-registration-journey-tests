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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import uk.gov.hmrc.configuration.TestEnvironment
import org.scalatest.matchers.should.Matchers._

import scala.util.Random

object Auth extends BasePage {

  private val authUrl: String           = TestEnvironment.url("auth-login-stub") + "/auth-login-stub/gg-sign-in"
  private val registrationUrl: String   =
    TestEnvironment.url("ioss-registration-frontend")
  private val journeyUrl: String        = "/pay-vat-on-goods-sold-to-eu/register-for-import-one-stop-shop"
  private val returnsUrl: String        =
    TestEnvironment.url("ioss-returns-frontend")
  private val returnsJourneyUrl: String = "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"

  var credId: String = "1234123412341234"

  def goToAuthorityWizard(): Unit = {
    get(authUrl)
    fluentWait.until(ExpectedConditions.urlContains(authUrl))
  }

  def checkAuthUrl(): Unit =
    getCurrentUrl should startWith(authUrl)

  def loginUsingAuthorityWizard(vrn: String, affinityGroup: String, accountType: String, journey: String): Unit = {

    getCurrentUrl should startWith(authUrl)

    val redirectUrl = journey match {
      case "amend"                                                                                        =>
        s"$registrationUrl$journeyUrl/start-amend-journey"
      case "savedRegistration" | "registrationFailureSave" | "retrievedWithCredId" | "etmpCredIdRetrieve" =>
        s"$registrationUrl$journeyUrl/continue-on-sign-in"
      case "rejoin"                                                                                       =>
        s"$registrationUrl$journeyUrl/start-rejoin-journey"
      case "dashboard"                                                                                    =>
        s"$returnsUrl$returnsJourneyUrl"
      case _                                                                                              =>
        s"$registrationUrl$journeyUrl"
    }
    sendKeys(By.name("redirectionUrl"), redirectUrl)

    if (journey == "registrationFailure" || journey == "savedWithCredId" || journey == "etmpCredId") {
      generateCredId()
      sendKeys(By.name("authorityId"), retrieveCredId())
    } else if (
      journey == "registrationFailureSave" || journey == "retrievedWithCredId" || journey == "etmpCredIdRetrieve"
    ) {
      sendKeys(By.name("authorityId"), retrieveCredId())
    }

    if (affinityGroup == "Agent") {
      selectByValue(By.id("affinityGroupSelect"), "Agent")
    } else if (affinityGroup == "Individual") {
      selectByValue(By.id("affinityGroupSelect"), "Individual")
      selectByValue(By.id("confidenceLevel"), "250")
      sendKeys(By.id("nino"), "AA123456D")
    } else {
      selectByValue(By.id("affinityGroupSelect"), "Organisation")
    }

    if (vrn != "None") {
      sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
      sendKeys(By.id("input-0-0-name"), "VRN")
      sendKeys(By.id("input-0-0-value"), vrn)
    }

    if (accountType != "noVat" && accountType != "vatOnly") {
      sendKeys(By.id("enrolment[1].name"), "HMRC-IOSS-ORG")
      sendKeys(By.id("input-1-0-name"), "IOSSNumber")

      val iossNumber = accountType match {
        case "unusableEmailStatus"                        => "IM9009999990"
        case "quarantinedOSSRejoin" | "quarantinedRejoin" => "IM9002999993"
        case "amendMinimalAccount"                        => "IM9009999998"
        case "etmpNotFound"                               => "IM9009999999"
        case "amendFailure"                               => "IM9002222222"
        case "excludedSixYears"                           => "IM9019999994"
        case "excludedFuture"                             => "IM9009999997"
        case "quarantined"                                => "IM9009999993"
        case "rejoinFailure"                              => "IM9029999997"
        case "minimalRejoin"                              => "IM9019999998"
        case "fullRejoin"                                 => "IM9019999997"
        case "deregisteredVat"                            => "IM9039999994"
        case "coreActiveOSS"                              => "IM9005999992"
        case "coreActiveIOSS"                             => "IM9005999994"
        case "coreActiveOSSNonUnion"                      => "IM9005999996"
        case "coreQuarantinedOSS"                         => "IM9005999991"
        case "coreQuarantinedIOSS"                        => "IM9005999993"
        case "coreActiveVRN"                              => "IM9004999994"
        case "coreQuarantinedVRN"                         => "IM9004999993"
        case "coreActiveTaxId"                            => "IM9004999992"
        case "coreQuarantinedTaxId"                       => "IM9004999991"
        case "onePreviousRegistration"                    => "IM9007230000"
        case "multiplePreviousRegistrations"              => "IM9007230003"
        case _                                            => "IN9001234567"
      }
      if (accountType != "registration") {
        sendKeys(By.id("input-1-0-value"), iossNumber)
      }

      if (accountType == "ossRegistration") {
        sendKeys(By.id("enrolment[1].name"), "HMRC-OSS-ORG")
        sendKeys(By.id("input-1-0-name"), "VRN")
        sendKeys(By.id("input-1-0-value"), vrn)
      } else if (accountType == "quarantinedOSSRejoin" || accountType == "quarantinedOSSAmend") {
        sendKeys(By.id("enrolment[2].name"), "HMRC-OSS-ORG")
        sendKeys(By.id("input-2-0-name"), "VRN")
        sendKeys(By.id("input-2-0-value"), vrn)
      }

      if (accountType == "onePreviousRegistration") {
        sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-2-0-name"), "IOSSNumber")
        sendKeys(By.id("input-2-0-value"), "IM9006230000")
      }

      if (accountType == "multiplePreviousRegistrations") {
        sendKeys(By.id("enrolment[2].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-2-0-name"), "IOSSNumber")
        sendKeys(By.id("input-2-0-value"), "IM9007230002")

        sendKeys(By.id("enrolment[3].name"), "HMRC-IOSS-ORG")
        sendKeys(By.id("input-3-0-name"), "IOSSNumber")
        sendKeys(By.id("input-3-0-value"), "IM9007230001")
      }
    }

    click(By.cssSelector("Input[value='Submit']"))

    if (journey == "retrievedWithCredId") {
      fluentWait.until(
        ExpectedConditions.urlToBe(
          "http://localhost:10190/pay-vat-on-goods-sold-to-eu/register-for-import-one-stop-shop/continue-registration"
        )
      )
    }

  }

  def retrieveCredId(): String =
    credId

  def generateCredId(): Unit =
    credId = Random.between(1000000000000000L, 9000000000000000L).toString

}

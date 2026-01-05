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

import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver

object PreviousRegistration extends BasePage {

  def checkIossNumber(iossNumber: String): Unit = {
    val body = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(body.contains(s"IOSS number: $iossNumber"))
  }

  def checkAmendedAnswers(previousRegistrationAccount: String): Unit = {
    val body = Driver.instance.findElement(By.tagName("body")).getText

    previousRegistrationAccount match {
      case "IM9006230000" =>
        Assert.assertTrue(body.contains("Contact name Previous Single Registration Trader"))
        Assert.assertTrue(body.contains("Telephone number 01234567891"))
        Assert.assertTrue(body.contains("Email address previous-single-registration-test@email.com"))
        Assert.assertTrue(body.contains("Name on the account Previous Single Registration Trader Name"))
        Assert.assertTrue(body.contains("IBAN GB29NWBK60161331926819"))
      case "IM9007230000" =>
        Assert.assertTrue(body.contains("Trading names added an amended current trading name"))
        Assert.assertTrue(body.contains("a new current trading name"))
        Assert.assertTrue(body.contains("Trading names removed tradingName1"))
        Assert.assertTrue(body.contains("tradingName2"))
        Assert.assertTrue(body.contains("Registered for tax in EU countries No"))
        Assert.assertTrue(body.contains("EU tax details removed Germany"))
      case "IM9007230001" =>
        Assert.assertTrue(body.contains("Contact name Previous Multiple Registration Trader"))
        Assert.assertTrue(body.contains("Telephone number +17771117771"))
        Assert.assertTrue(body.contains("Email address previous-registration-test-multiple@email.com"))
        Assert.assertTrue(body.contains("Name on the account Previous Multiple Registration Trader Name"))
        Assert.assertTrue(body.contains("IBAN GB29NWBK60161331926819"))
      case "IM9007230002" =>
        Assert.assertTrue(body.contains("Contact name Previous Multiple Registration Trader 2"))
        Assert.assertTrue(body.contains("Email address previous-registration-test-another@email.com"))
        Assert.assertTrue(body.contains("Name on the account Previous Multiple Registration Trader Name 2"))
      case "IM9007230003" =>
        Assert.assertTrue(body.contains("Countries registered in Finland"))
        Assert.assertTrue(body.contains("Countries registered in changed Cyprus"))
        Assert.assertTrue(body.contains("Trading websites added https://www.amended-website-name-multiple-ioss.com"))
        Assert.assertTrue(body.contains("https://www.2ndmultipleiosswebsite.eu"))
        Assert.assertTrue(body.contains("Trading websites removed www.website1.com"))
        Assert.assertTrue(body.contains("www.website2.com"))
      case _              =>
        throw new Exception("This amend variation does not exist")
    }
  }

  def selectPreviousRegistration(iossNumber: String): Unit = {
    iossNumber match {
      case "IM9007230001" => click(By.id("value_0"))
      case "IM9007230002" => click(By.id("value_1"))
      case _              => throw new Exception("Option doesn't exist")
    }
    click(continueButton)
  }

}

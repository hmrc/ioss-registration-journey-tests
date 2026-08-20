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

package uk.gov.hmrc.ui.pages

import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver

object CrossSchema extends BasePage {

  def checkTradingNames(): Unit = {
    val header = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(header.equals("You have 2 UK trading names from previous One Stop Shop scheme registrations"))
  }

  def checkHintTextAndWarnings(journey: String, displayed: Boolean, version: String): Unit = {
    val hintText    =
      "We have added the details you entered for a previous One Stop Shop scheme. Check they are still correct."
    val warningText =
      s"Changes you make here will also update the $version in any One Stop Shop accounts you registered for."

    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText

    if (!displayed) {
      Assert.assertFalse(htmlBody.contains(warningText))
      if (journey == "registration") {
        Assert.assertFalse(htmlBody.contains(hintText))
      }
    } else {
      Assert.assertTrue(htmlBody.contains(warningText))
      if (journey == "registration") {
        Assert.assertTrue(htmlBody.contains(hintText))
      }
    }
  }

  def checkConfirmation(displayed: Boolean): Unit = {

    val htmlBody         = Driver.instance.findElement(By.tagName("body")).getText
    val confirmationText =
      "We've also updated any other One Stop Shop registrations you have."

    if (!displayed) {
      Assert.assertFalse(htmlBody.contains(confirmationText))
    } else {
      Assert.assertTrue(htmlBody.contains(confirmationText))
    }
  }

  def checkAmendedAnswers(amendJourney: String): Unit = {
    val body = Driver.instance.findElement(By.tagName("body")).getText

    amendJourney match {
      case "ossAndIoss"         =>
        Assert.assertTrue(body.contains("You changed the following details:"))
        Assert.assertTrue(body.contains("Trading names added an amended cross schema trading name"))
        Assert.assertTrue(body.contains("Trading names removed tradingName1"))
        Assert.assertTrue(body.contains("Telephone number +17771117771"))
        Assert.assertTrue(body.contains("IBAN (International Bank Account Number) GB29NWBK60161331926819"))
      case "removedTradingName" =>
        Assert.assertTrue(body.contains("You changed the following details:"))
        Assert.assertTrue(body.contains("Trading names removed tradingName2"))
      case "updatedIban"        =>
        Assert.assertTrue(body.contains("You changed the following details:"))
        Assert.assertTrue(body.contains("IBAN (International Bank Account Number) GB29NWBK60161331926819"))
      case _                    =>
        Assert.assertTrue(body.contains("You changed the following details:"))
        Assert.assertTrue(body.contains("Trading names added another"))
        Assert.assertTrue(body.contains("Trading names removed tradingName2"))
        Assert.assertTrue(body.contains("Email address amend-cross-schema-test@email.com"))
        Assert.assertTrue(body.contains("Name on the account Another Cross Schema Name"))
    }
  }

  def onlyNewTradingNameAdded(): Unit = {
    val header = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(header.equals("You have added one UK trading name"))
  }
}

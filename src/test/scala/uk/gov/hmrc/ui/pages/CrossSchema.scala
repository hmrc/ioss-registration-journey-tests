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

object CrossSchema extends BasePage {

  def checkTradingNames(version: String): Unit = {
    val header = Driver.instance.findElement(By.tagName("h1")).getText

    val headingText = version match {
      case "an OSS"        => "You have 2 UK trading names from your One Stop Shop registration"
      case "OSS and IOSS"  =>
        "You have 2 UK trading names from your One Stop Shop and Import One Stop Shop registrations"
      case "multiple IOSS" => "You have 2 UK trading names from your Import One Stop Shop registrations"
      case _               => "You have 2 UK trading names from your Import One Stop Shop registration"
    }

    Assert.assertTrue(header.equals(headingText))
  }

  def checkWarningsForTradingNames(journey: String, displayed: Boolean, version: String): Unit = {
    val hintText    =
      "We added the trading names you entered when you registered for the One Stop Shop service. Check they are still correct."
    val warningText = if (version == "an OSS") {
      "Any changes you make here will also update the trading names in your One Stop Shop registration."
    } else if (version == "both OSS and IOSS") {
      "Any changes you make here will also update the trading names in your One Stop Shop and previous Import One Stop Shop registrations."
    } else if (version == "multiple IOSS") {
      "Any changes you make here will also update the trading names in all of your Import One Stop Shop registrations."
    } else if (version == "one previous IOSS") {
      "Any changes you make here will also update the trading names in your previous Import One Stop Shop registration."
    } else {
      "Any changes you make here will also update the trading names in"
    }

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

  def checkWarningsForBankAndContactDetails(
    journey: String,
    displayed: Boolean,
    version: String,
    page: String
  ): Unit = {

    val htmlBody    = Driver.instance.findElement(By.tagName("body")).getText
    val hintText    =
      "We have added the details you entered for the One Stop Shop service. Check they are still correct."
    val warningText = if (version == "an OSS") {
      s"Any changes you make here will also update the $page details in your One Stop Shop registration."
    } else if (version == "both OSS and IOSS") {
      s"Any changes you make here will also update the $page details in your One Stop Shop and previous Import One Stop Shop registrations."
    } else if (version == "multiple IOSS") {
      s"Any changes you make here will also update the $page details in all of your Import One Stop Shop registrations."
    } else if (version == "one previous IOSS") {
      s"Any changes you make here will also update the $page details in your previous Import One Stop Shop registration."
    } else {
      s"Any changes you make here will also update the $page details in"
    }

    if (!displayed) {
      if (journey == "registration") {
        Assert.assertFalse(htmlBody.contains(hintText))
      }
      Assert.assertFalse(htmlBody.contains(warningText))
    } else {
      if (journey == "registration") {
        Assert.assertTrue(htmlBody.contains(hintText))
      }
      Assert.assertTrue(htmlBody.contains(warningText))
    }
  }

  def checkConfirmation(journey: String, displayed: Boolean, version: String): Unit = {

    val htmlBody         = Driver.instance.findElement(By.tagName("body")).getText
    val confirmationText = if (version == "an OSS") {
      "We've also updated your One Stop Shop registration."
    } else if (version == "both OSS and IOSS") {
      "We've also updated your One Stop Shop and previous Import One Stop Shop registrations."
    } else if (version == "multiple IOSS") {
      "We've also updated your previous Import One Stop Shop registrations."
    } else if (version == "one previous IOSS") {
      "We've also updated your previous Import One Stop Shop registration."
    } else {
      "We've also updated your"
    }

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
        Assert.assertTrue(body.contains("Trading names removed firstPreviousTradingName1"))
        Assert.assertTrue(body.contains("Telephone number +17771117771"))
        Assert.assertTrue(body.contains("IBAN GB29NWBK60161331926819"))
      case "removedTradingName" =>
        Assert.assertTrue(body.contains("You changed the following details:"))
        Assert.assertTrue(body.contains("Trading names removed tradingName2"))
      case "updatedIban"        =>
        Assert.assertTrue(body.contains("You changed the following details:"))
        Assert.assertTrue(body.contains("IBAN GB29NWBK60161331926819"))
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

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

import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver

class CrossSchemaStepDef extends BaseStepDef {

  Then(
    """^the correct number of existing trading names are displayed for a trader with (an OSS|OSS and IOSS|multiple IOSS|one previous IOSS) (registration|registrations)$"""
  ) { (version: String, registrationNumber: String) =>
    val header = Driver.instance.findElement(By.tagName("h1")).getText

    val headingText = if (version == "an OSS") {
      "You have 2 UK trading names from your One Stop Shop registration"
    } else if (version == "OSS and IOSS") {
      "You have 2 UK trading names from your One Stop Shop and Import One Stop Shop registrations"
    } else if (version == "multiple IOSS") {
      "You have 2 UK trading names from your Import One Stop Shop registrations"
    } else if (version == "one previous IOSS") {
      "You have 2 UK trading names from your Import One Stop Shop registration"
    }

    Assert.assertTrue(header.equals(headingText))
  }

  Then("""^the new trading name is the only trading name where there are no previous registrations$""") { () =>
    val header = Driver.instance.findElement(By.tagName("h1")).getText
    Assert.assertTrue(header.equals("You have added one UK trading name"))
  }

  Then("""^there are no headings or warnings for trading names mentioning other registrations$""") { () =>
    val header   = Driver.instance.findElement(By.tagName("h1")).getText
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(header.equals("You have added 2 UK trading names"))
    Assert.assertFalse(htmlBody.contains("Any changes you make here will also update the trading names in"))
  }

  Then(
    """^the (registration|amend|rejoin) trading name warnings (are|are not) displayed for a trader with (an OSS|both OSS and IOSS|multiple IOSS|one previous IOSS|no other) (registration|registrations)$"""
  ) { (journey: String, displayed: String, version: String, registrationNumber: String) =>
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

    if (displayed == "are not") {
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

  Then(
    """^the (registration|amend|rejoin) (contact|bank) details warnings (are|are not) displayed for a trader with (an OSS|both OSS and IOSS|multiple IOSS|one previous IOSS|one current IOSS|no other) (registration|registrations)$"""
  ) { (journey: String, page: String, displayed: String, version: String, registrationNumber: String) =>
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

    if (displayed == "are not") {
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

  Then("""^the contact details are blank$""") { () =>
    Assert.assertTrue(Driver.instance.findElement(By.id("fullName")).getAttribute("value").isEmpty)
    Assert.assertTrue(Driver.instance.findElement(By.id("telephoneNumber")).getAttribute("value").isEmpty)
    Assert.assertTrue(Driver.instance.findElement(By.id("emailAddress")).getAttribute("value").isEmpty)
  }

  Then("""^the bank details are blank$""") { () =>
    Assert.assertTrue(Driver.instance.findElement(By.id("accountName")).getAttribute("value").isEmpty)
    Assert.assertTrue(Driver.instance.findElement(By.id("bic")).getAttribute("value").isEmpty)
    Assert.assertTrue(Driver.instance.findElement(By.id("iban")).getAttribute("value").isEmpty)
  }

  Then(
    """^the text on the (registration|amend|rejoin) confirmation page (is|is not) displayed when the trader (has|has not) made changes and has (an OSS|both OSS and IOSS|multiple IOSS|one previous IOSS|no other) (registration|registrations)$"""
  ) { (journey: String, displayed: String, madeChanges: String, version: String, registrationNumber: String) =>
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

    if (displayed == "is not") {
      Assert.assertFalse(htmlBody.contains(confirmationText))
    } else {
      Assert.assertTrue(htmlBody.contains(confirmationText))
    }
  }

  Then(
    """^all of the updated answers are displayed as changed on the amend confirmation page for (OSS and IOSS|multiple IOSS accounts)$"""
  ) { (version: String) =>
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    if (version == "OSS and IOSS") {
      Assert.assertTrue(htmlBody.contains("You changed the following details:"))
      Assert.assertTrue(htmlBody.contains("Trading names added an amended cross schema trading name"))
      Assert.assertTrue(htmlBody.contains("Trading names removed tradingName1"))
      Assert.assertTrue(htmlBody.contains("Telephone number +17771117771"))
      Assert.assertTrue(htmlBody.contains("IBAN GB29NWBK60161331926819"))
    } else {
      Assert.assertTrue(htmlBody.contains("You changed the following details:"))
      Assert.assertTrue(htmlBody.contains("Trading names added another"))
      Assert.assertTrue(htmlBody.contains("Trading names removed tradingName2"))
      Assert.assertTrue(htmlBody.contains("Email address amend-cross-schema-test@email.com"))
      Assert.assertTrue(htmlBody.contains("Name on the account Another Cross Schema Name"))
    }

  }

  Then(
    """^the removed trading name is displayed on the amend confirmation$"""
  ) { () =>
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("Trading names removed tradingName2"))
  }

  Then(
    """^the updated iban is displayed on the amend confirmation$"""
  ) { () =>
    val htmlBody = Driver.instance.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("IBAN GB29NWBK60161331926819"))
  }
}

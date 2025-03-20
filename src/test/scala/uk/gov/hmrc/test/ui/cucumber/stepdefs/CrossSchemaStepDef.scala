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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import org.junit.Assert
import org.openqa.selenium.By

class CrossSchemaStepDef extends BaseStepDef {

  Then("""^the correct number of existing trading names are displayed for a trader with an OSS registration$""") { () =>
    val header = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(header.equals("You have added 2 UK trading names from your One Stop Shop registration"))
  }

  Then("""^the new trading name is the only trading name where there are no previous registrations$""") { () =>
    val header = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(header.equals("You have added one UK trading name"))
  }

  Then("""^the trading name warnings (are|are not) displayed for a trader with an OSS registration$""") {
    (version: String) =>
      val htmlBody    = driver.findElement(By.tagName("body")).getText
      val hintText    =
        "We added the trading names you entered when you registered for the One Stop Shop service. Check they are still correct."
      val warningText =
        "Any changes you make here will also update the trading names in your One Stop Shop registration."

      if (version == "are not") {
        Assert.assertFalse(htmlBody.contains(hintText))
        Assert.assertFalse(htmlBody.contains(warningText))
      } else {
        Assert.assertTrue(htmlBody.contains(hintText))
        Assert.assertTrue(htmlBody.contains(warningText))
      }
  }

  Then("""^the contact details warnings (are|are not) displayed for a trader with an OSS registration$""") {
    (version: String) =>
      val htmlBody    = driver.findElement(By.tagName("body")).getText
      val hintText    =
        "We have added the details you entered for the One Stop Shop service. Check they are still correct."
      val warningText =
        "Any changes you make here will also update the contact details in your One Stop Shop registration."

      if (version == "are not") {
        Assert.assertFalse(htmlBody.contains(hintText))
        Assert.assertFalse(htmlBody.contains(warningText))
      } else {
        Assert.assertTrue(htmlBody.contains(hintText))
        Assert.assertTrue(htmlBody.contains(warningText))
      }
  }

  Then("""^the contact details are blank$""") { () =>
    Assert.assertTrue(driver.findElement(By.id("fullName")).getAttribute("value").isEmpty)
    Assert.assertTrue(driver.findElement(By.id("telephoneNumber")).getAttribute("value").isEmpty)
    Assert.assertTrue(driver.findElement(By.id("emailAddress")).getAttribute("value").isEmpty)
  }

  Then("""^the bank details warnings (are|are not) displayed for a trader with an OSS registration$""") {
    (version: String) =>
      val htmlBody    = driver.findElement(By.tagName("body")).getText
      val hintText    =
        "We have added the details you entered for the One Stop Shop service. Check they are still correct."
      val warningText =
        "Any changes you make here will also update the bank details in your One Stop Shop registration."

      if (version == "are not") {
        Assert.assertFalse(htmlBody.contains(hintText))
        Assert.assertFalse(htmlBody.contains(warningText))
      } else {
        Assert.assertTrue(htmlBody.contains(hintText))
        Assert.assertTrue(htmlBody.contains(warningText))
      }
  }

  Then("""^the bank details are blank$""") { () =>
    Assert.assertTrue(driver.findElement(By.id("accountName")).getAttribute("value").isEmpty)
    Assert.assertTrue(driver.findElement(By.id("bic")).getAttribute("value").isEmpty)
    Assert.assertTrue(driver.findElement(By.id("iban")).getAttribute("value").isEmpty)
  }

  Then(
    """^the text on the confirmation page (is|is not) displayed when the trader (has|has not) made changes and (has an|has no) OSS registration$"""
  ) { (version: String, madeChanges: String, hasRegistration: String) =>
    val htmlBody            = driver.findElement(By.tagName("body")).getText
    val ossConfirmationText = "We've also updated your One Stop Shop registration."

    if (version == "is not") {
      Assert.assertFalse(htmlBody.contains(ossConfirmationText))
    } else {
      Assert.assertTrue(htmlBody.contains(ossConfirmationText))
    }
  }
}

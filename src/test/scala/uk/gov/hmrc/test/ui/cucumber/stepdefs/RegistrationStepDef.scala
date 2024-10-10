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
import uk.gov.hmrc.test.ui.pages.CommonPage._
import uk.gov.hmrc.test.ui.pages.RegistrationPage._

class RegistrationStepDef extends BaseStepDef {

  Given("the user accesses the IOSS Registration service") { () =>
    goToRegistrationJourney()
  }

  Given("the user is at the beginning of the signed in IOSS Registration journey") { () =>
    checkJourneyUrl()
  }

  When("""^the user chooses (Yes|Yes, details incorrect|No, different business) on the (.*) page$""") {
    (data: String, url: String) =>
      checkUrl(url)
      selectVatInfoChoice(data)
      clickContinue()
  }

  When(
    """^the user selects (.*) on the (first|second|third|cya-new-first-previous-scheme) (.*) page$"""
  ) { (country: String, index: String, url: String) =>
    val pageIndex = index match {
      case "first" | "cya-new-first-previous-scheme" => "1"
      case "second"                                  => "2"
      case "third"                                   => "3"
      case _                                         => throw new Exception("Index doesn't exist")
    }
    if (index == "cya-new-first-previous-scheme") {
      checkUrl(s"$url/$pageIndex?waypoints=check-your-answers")
    } else {
      checkUrl(s"$url/$pageIndex")
    }
    selectValueAutocomplete(country)
  }

  When(
    """^the user picks (oss|ioss|vat number|tax id number|IM9007230001|IM9007230002) on the (.*) page$"""
  ) { (answer: String, url: String) =>
    val radioButtonToSelect = answer match {
      case "oss" | "vat number" | "IM9007230001"     => "1"
      case "ioss" | "tax id number" | "IM9007230002" => "2"
      case _                                         =>
        throw new Exception("Selection doesn't exist")
    }
    checkUrl(url)
    selectRadioButton(radioButtonToSelect)
  }

  Then("""^the user is directed to the BTA service$""") { () =>
    checkBTA()
  }

  Then("""^the user submits their registration$""") { () =>
    clickSubmit()
    checkUrl("successful")
  }

  Then("""^the user selects the register button$""") { () =>
    clickSubmit()
  }

  Then("^the user accesses the continue on sign in url$") { () =>
    driver.manage().deleteAllCookies()
    goToContinueOnSignInPage()
  }

  When("""^the user picks (Yes|No,delete my answers and start again) on the continue-registration page$""") {
    (data: String) =>
      checkUrl("continue-registration")
      selectContinueRegistration(data)
  }

  Then("""^the correct IOSS number (.*) is displayed on the page$""") { (iossNumber: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("IOSS number: " + iossNumber))
  }

  Then("""^the confirmation of no answers changed is displayed$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You have not changed any of your registration details."))
  }

  Then("""^all of the yes to no amendments are displayed as changed on the confirmation page$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("Have a different UK trading name No"))
    Assert.assertTrue(htmlBody.contains("Trading names removed tradingName1"))
    Assert.assertTrue(htmlBody.contains("tradingName2"))
    Assert.assertTrue(htmlBody.contains("Registered for tax in EU countries No"))
    Assert.assertTrue(htmlBody.contains("EU tax details removed Germany"))
  }

  Then("""^all of the no to yes amendments are displayed as changed on the confirmation page$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("Have a different UK trading name Yes"))
    Assert.assertTrue(htmlBody.contains("Trading names added A new trading name in amend journey"))
    Assert.assertTrue(htmlBody.contains("Other One Stop Shop registrations Yes"))
    Assert.assertTrue(htmlBody.contains("Countries registered in Republic of Cyprus"))
    Assert.assertTrue(htmlBody.contains("Finland"))
    Assert.assertTrue(htmlBody.contains("Registered for tax in EU countries Yes"))
    Assert.assertTrue(htmlBody.contains("EU tax details added Romania"))
  }

  Then("""^all of the amended non-mandatory answers are displayed as changed on the confirmation page$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("Trading names added an amended trading name"))
    Assert.assertTrue(htmlBody.contains("new 2nd name"))
    Assert.assertTrue(htmlBody.contains("Trading names removed tradingName1"))
    Assert.assertTrue(htmlBody.contains("tradingName2"))
    Assert.assertTrue(htmlBody.contains("Countries registered in Finland"))
    Assert.assertTrue(htmlBody.contains("Countries registered in changed Republic of Cyprus"))
    Assert.assertTrue(htmlBody.contains("EU tax details added Estonia"))
    Assert.assertTrue(htmlBody.contains("Portugal"))
    Assert.assertTrue(htmlBody.contains("EU tax details changed Germany"))
  }

  Then(
    """^all of the removed answers and amended mandatory answers are displayed as changed on the confirmation page$"""
  ) { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("EU tax details added Estonia"))
    Assert.assertTrue(htmlBody.contains("EU tax details removed Germany"))
    Assert.assertTrue(htmlBody.contains("Trading websites added https://www.amended-website-name.com"))
    Assert.assertTrue(htmlBody.contains("https://www.2nd-website.eu"))
    Assert.assertTrue(htmlBody.contains("Trading websites removed www.website1.com"))
    Assert.assertTrue(htmlBody.contains("www.website2.com"))
    Assert.assertTrue(htmlBody.contains("Contact name Another Trader"))
    Assert.assertTrue(htmlBody.contains("Telephone number +17771117771"))
    Assert.assertTrue(htmlBody.contains("Name on the account Another Trader Name"))
    Assert.assertTrue(htmlBody.contains("IBAN GB29NWBK60161331926819"))
  }

  Then("""^the amended email address is displayed as changed on the confirmation page$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    Assert.assertTrue(htmlBody.contains("Email address amend-test@email.com"))
  }

  Then(
    """^all of the amended answers for (current|previous) registration (.*) are displayed on the confirmation$"""
  ) { (registrationType: String, iossNumber: String) =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertTrue(htmlBody.contains("You changed the following details:"))
    if (iossNumber == "IM9007230001") {
      Assert.assertTrue(htmlBody.contains("Contact name Previous Multiple Registration Trader"))
      Assert.assertTrue(htmlBody.contains("Telephone number +17771117771"))
      Assert.assertTrue(htmlBody.contains("Email address previous-registration-test-multiple@email.com"))
      Assert.assertTrue(htmlBody.contains("Name on the account Previous Multiple Registration Trader Name"))
      Assert.assertTrue(htmlBody.contains("IBAN GB29NWBK60161331926819"))
    } else if (iossNumber == "IM9007230002") {
      Assert.assertTrue(htmlBody.contains("Contact name Previous Multiple Registration Trader 2"))
      Assert.assertTrue(htmlBody.contains("Name on the account Previous Multiple Registration Trader Name 2"))
    } else if (iossNumber == "IM9007230003") {
      Assert.assertTrue(htmlBody.contains("Countries registered in Finland"))
      Assert.assertTrue(htmlBody.contains("Countries registered in changed Republic of Cyprus"))
      Assert.assertTrue(htmlBody.contains("Trading websites added https://www.amended-website-name-multiple-ioss.com"))
      Assert.assertTrue(htmlBody.contains("https://www.2ndmultipleiosswebsite.eu"))
      Assert.assertTrue(htmlBody.contains("Trading websites removed www.website1.com"))
      Assert.assertTrue(htmlBody.contains("www.website2.com"))
    } else if (iossNumber == "IM9006230000") {
      Assert.assertTrue(htmlBody.contains("Contact name Previous Single Registration Trader"))
      Assert.assertTrue(htmlBody.contains("Telephone number 01234567891"))
      Assert.assertTrue(htmlBody.contains("Email address previous-single-registration-test@email.com"))
      Assert.assertTrue(htmlBody.contains("Name on the account Previous Single Registration Trader Name"))
      Assert.assertTrue(htmlBody.contains("IBAN GB29NWBK60161331926819"))
    } else if (iossNumber == "IM9007230000") {
      Assert.assertTrue(htmlBody.contains("Trading names added an amended current trading name"))
      Assert.assertTrue(htmlBody.contains("a new current trading name"))
      Assert.assertTrue(htmlBody.contains("Trading names removed tradingName1"))
      Assert.assertTrue(htmlBody.contains("tradingName2"))
      Assert.assertTrue(htmlBody.contains("Registered for tax in EU countries No"))
      Assert.assertTrue(htmlBody.contains("EU tax details removed Germany"))
    }
  }

}

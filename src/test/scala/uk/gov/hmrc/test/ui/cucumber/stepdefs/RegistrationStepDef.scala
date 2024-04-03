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
    clickContinue()
    checkUrl("successful")
  }

  Then("""^the user selects the register button$""") { () =>
    clickContinue()
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

}

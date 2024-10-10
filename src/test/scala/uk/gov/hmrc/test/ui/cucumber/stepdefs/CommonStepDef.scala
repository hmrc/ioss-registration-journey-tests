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

import io.cucumber.datatable.DataTable
import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.CommonPage.clickBackButton
import uk.gov.hmrc.test.ui.pages._

class CommonStepDef extends BaseStepDef {

  When("""^the user answers (yes|no) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectAnswer(data)
  }

  When("^the user continues through the (.*) page$") { (url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.clickContinue()
  }

  Then("""^the user is on the (.*) page$""") { (url: String) =>
    CommonPage.checkUrl(url)
    if (url == "successful-rejoin") {
      val htmlBody = driver.findElement(By.tagName("body")).getText
      Assert.assertTrue(htmlBody.contains("Your new IOSS number is"))
      Assert.assertTrue(htmlBody.contains("-NEW"))
    }
  }

  When("""^the user adds (.*) on the (first|second|third|new) (.*) page$""") {
    (data: String, index: String, url: String) =>
      index match {
        case "first"  => CommonPage.checkUrl(url + "/1")
        case "second" => CommonPage.checkUrl(url + "/2")
        case "third"  => CommonPage.checkUrl(url + "/3")
        case "new"    => CommonPage.checkUrl(url)
        case _        => throw new Exception("Index doesn't exist")
      }
      CommonPage.enterData(data)
  }

  When("""^the user amends data to (.*) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.clearData()
    CommonPage.enterData(data)
  }

  When("""^the user manually navigates to the (.*) page$""") { (page: String) =>
    CommonPage.goToPage(page)
  }

  When("""^the user (completes|amends) details on the (.*) page$""") {
    (mode: String, url: String, dataTable: DataTable) =>
      CommonPage.checkUrl(url)
      CommonPage.completeForm(dataTable)
  }

  Then("""^the user clicks on the (.*) (link|button)$""") { (link: String, element: String) =>
    link match {
      case "BTA"                                       =>
        driver.findElement(By.id("back-to-your-account")).click()
      case "continue to complete your registration"    =>
        driver.findElement(By.cssSelector("a#continueToYourReturn")).click()
      case "sign out and come back later"              =>
        driver.findElement(By.id("signOut")).click()
      case "cancel"                                    =>
        driver.findElement(By.id("cancel")).click()
      case "Back to your account"                      =>
        driver.findElement(By.id("backToYourAccount")).click()
      case "save and come back later"                  =>
        driver.findElement(By.id("saveProgress")).click()
      case "View or change your previous registration" =>
        driver.findElement(By.id("change-previous-registrations")).click()
      case _                                           =>
        throw new Exception("Link doesn't exist")
    }
  }

  Then("""^the user is redirected to the returns dashboard$""") { () =>
    CommonPage.checkReturnsDashboard()
  }

  Then("""^the user is presented with the problem page$""") { () =>
    val htmlHeader = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlHeader.equals("Sorry, there is a problem with the service"))
  }

  When("""^the user amends answer to (.*)$""") { (answer: String) =>
    driver.findElement(By.id("value")).clear()
    CommonPage.enterData(answer)
  }

  Then("""^the user clicks back on the browser$""") { () =>
    clickBackButton()
  }

  When("^the user submits their (amended|rejoin) registration$") { (submissionType: String) =>
    CommonPage.clickSubmit()
  }

}

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
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class RegistrationStepDef extends BaseStepDef {

  Given("the user accesses the IOSS Registration service") { () =>
    CommonPage.goToRegistrationJourney()
  }

  Given(
    "^the user signs into authority wizard as an (Organisation|Agent) Admin (with|without) (VAT|IOSS and VAT) enrolment (.*)$"
  ) { (role: String, withStatus: String, enrolment: String, vrn: String) =>
    AuthPage.loginUsingAuthorityWizard(false, "registration", role, withStatus, enrolment, vrn, "default")
  }

  Given(
    "^the user signs in as an Organisation (Admin|Non-Admin) (with|without) VAT enrolment (.*)$"
  ) { (admin: String, vatEnrolment: String, vrn: String) =>
    AuthPage.loginUsingScpStub("Organisation", admin, vatEnrolment, vrn)
    AuthPage.selectMfaSuccess()
  }

  Given("""the user accesses the authority wizard""") { () =>
    AuthPage.goToAuthStub()
  }

  Given("the user is at the beginning of the signed in IOSS Registration journey") { () =>
    CommonPage.checkJourneyUrl()
  }

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
  }

  When("""^the user chooses (Yes|Yes, details incorrect|No, different business) on the (.*) page$""") {
    (data: String, url: String) =>
      CommonPage.checkUrl(url)
      CommonPage.selectChoice(data)
      CommonPage.clickContinue()
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

  Then(
    """^the user selects the (list|CYA|list within CYA|list within amend|additional tax details list within CYA|amend|additional tax details list within amend) change link for (first|second|third|page) (.*) from (.*)$"""
  ) { (route: String, index: String, toPage: String, fromPage: String) =>
    val changeIndex = index match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case _        => "no index required"
    }
    if (route == "list") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage")
    } else if (route == "list within CYA") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Ccheck-your-answers")
    } else if (route == "list within amend") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Cchange-your-registration")
    } else if (route == "CYA") {
      CommonPage.selectLink(s"$toPage\\?waypoints\\=$fromPage")
    } else if (route == "amend") {
      CommonPage.selectLink(s"$toPage\\?waypoints\\=$fromPage")
    } else if (route == "additional tax details list within CYA") {
      CommonPage.selectLink(
        s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Cchange-add-tax-details\\%2Ccheck-your-answers"
      )
    } else if (route == "additional tax details list within amend") {
      CommonPage.selectLink(
        s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Cchange-add-tax-details\\%2Cchange-your-registration"
      )
    }
  }

  Then(
    """^the user clicks remove via (list|CYA route|overviewLoop|amend route) for (first|second|third) (.*)$"""
  ) { (route: String, index: String, page: String) =>
    val removeIndex = index match {
      case "first"  => "1"
      case "second" => "2"
      case "third"  => "3"
      case _        => throw new Exception("Index doesn't exist")
    }
    if (route == "CYA route") {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex\\?waypoints\\=check-your-answers")
    } else if (route == "amend route") {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex\\?waypoints\\=change-your-registration")
    } else if (route == "overviewLoop") {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex\\?waypoints\\=change-previous-schemes-overview")
    } else {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex")
    }
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
      CommonPage.checkUrl(s"$url/$pageIndex?waypoints=check-your-answers")
    } else {
      CommonPage.checkUrl(s"$url/$pageIndex")
    }
    CommonPage.selectValueAutocomplete(country)
  }

  When(
    """^the user picks (oss|ioss|fixed establishment|dispatch warehouse|vat number|tax id number) on the (.*) page$"""
  ) { (answer: String, url: String) =>
    val radioButtonToSelect = answer match {
      case "oss" | "fixed establishment" | "vat number"    => "1"
      case "ioss" | "dispatch warehouse" | "tax id number" => "2"
      case _                                               =>
        throw new Exception("Selection doesn't exist")
    }
    CommonPage.checkUrl(url)
    CommonPage.selectRadioButton(radioButtonToSelect)
  }

  And("""^the user completes the (registration|change answers|amend registration) email verification process""") {
    (mode: String) =>
      val journeyId = driver.getCurrentUrl.split("/")(5)
      CommonPage.goToEmailVerificationPasscodeGeneratorUrl()

      val passcode = mode match {
        case "registration" | "amend registration" =>
          driver.findElement(By.tagName("body")).getText.split(">")(3).dropRight(3)
        case "change answers"                      =>
          driver.findElement(By.tagName("body")).getText.split("test@newtestemail.com,")(1).dropRight(42)
        case _                                     =>
          throw new Exception("mode doesn't exist")
      }
      CommonPage.goToEmailVerificationUrl(journeyId, mode)
      CommonPage.enterPasscode(passcode)

      if (mode == "change answers") {
        CommonPage.goToPage("check-your-answers")
      } else if (mode == "amend registration") {
        CommonPage.goToPage("change-your-registration")
      } else {
        CommonPage.goToPage("bank-details")
      }
  }

  Then("""^the user clicks on the (.*) (link|button)$""") { (link: String, element: String) =>
    link match {
      case "BTA"                                    =>
        driver.findElement(By.id("back-to-your-account")).click()
      case "continue to complete your registration" =>
        driver.findElement(By.cssSelector("a#continueToYourReturn")).click()
      case "sign out and come back later"           =>
        driver.findElement(By.id("signOut")).click()
      case "cancel"                                 =>
        driver.findElement(By.id("cancel")).click()
      case "Back to your account"                   =>
        driver.findElement(By.id("backToYourAccount")).click()
      case "save and come back later"               =>
        driver.findElement(By.id("saveProgress")).click()
      case _                                        =>
        throw new Exception("Link doesn't exist")
    }
  }

  Then("""^the user is directed to the BTA service$""") { () =>
    CommonPage.checkBTA()
  }

  Then("""^the user submits their registration$""") { () =>
    CommonPage.clickContinue()
    CommonPage.checkUrl("successful")
  }

  Then("""^the user selects the register button$""") { () =>
    CommonPage.clickContinue()
  }

  Then("""^the user is redirected to the returns dashboard$""") { () =>
    CommonPage.checkReturnsDashboard()
  }

  Then("""^the user is presented with the technical difficulties page$""") { () =>
    val htmlHeader = driver.findElement(By.tagName("h1")).getText
    Assert.assertTrue(htmlHeader.equals("Sorry, we’re experiencing technical difficulties"))
  }

  When("""^a user with VRN (.*) and IOSS Number (.*) accesses the amend registration journey""") {
    (vrn: String, iossNumber: String) =>
      AuthPage.loginUsingAuthorityWizard(false, "amend", "organisation", "with", "IOSS and VAT", vrn, iossNumber)
  }

  When("""^the user amends answer to (.*)$""") { (answer: String) =>
    driver.findElement(By.id("value")).clear()
    CommonPage.enterData(answer)
  }

  Then("^the user accesses the continue on sign in url$") { () =>
    driver.manage().deleteAllCookies()
    CommonPage.goToContinueOnSignInPage()
  }

  When("""^the user picks (Yes|No,delete my answers and start again) on the continue-registration page$""") {
    (data: String) =>
      CommonPage.checkUrl("continue-registration")
      CommonPage.selectContinueRegistration(data)
  }

  When("""^a user with VRN (.*) and no IOSS enrolment accesses the amend registration journey""") { (vrn: String) =>
    AuthPage.loginUsingAuthorityWizard(false, "amend", "organisation", "with", "VAT", vrn, "None")
  }

  Given(
    "^the user signs into authority wizard with a Cred ID and VRN (.*) to (start registration|retrieve saved registration)$"
  ) { (vrn: String, journey: String) =>
    val whichJourney = journey match {
      case "start registration"          => "registration"
      case "retrieve saved registration" => "saved"
      case _                             => throw new Exception("Journey doesn't exist")
    }
    AuthPage.loginUsingAuthorityWizard(true, whichJourney, "Organisation", "with", "VAT", vrn, "default")
  }

}

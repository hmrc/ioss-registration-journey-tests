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

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.EmailVerificationPage._
import uk.gov.hmrc.test.ui.pages._

class EmailVerificationStepDef extends BaseStepDef {

  And(
    """^the user completes the (registration|change answers|amend registration|amend previous registration|second amend previous registration|rejoin registration) email verification process"""
  ) { (mode: String) =>
    val journeyId = driver.getCurrentUrl.split("/")(5)
    goToEmailVerificationPasscodeGeneratorUrl()

    val passcode = mode match {
      case "registration" | "amend registration" | "amend previous registration" | "rejoin registration" =>
        driver.findElement(By.tagName("body")).getText.split(">")(3).dropRight(3)
      case "change answers"                                                                              =>
        driver.findElement(By.tagName("body")).getText.split("test@newtestemail.com,")(1).dropRight(42)
      case "second amend previous registration"                                                          =>
        driver.findElement(By.tagName("body")).getText.split("email@test.com,")(1).dropRight(42)
      case _                                                                                             =>
        throw new Exception("mode doesn't exist")
    }
    goToEmailVerificationUrl(journeyId, mode)
    enterPasscode(passcode)
  }

  Then("""^the user is redirected to the email intercept page$""") { () =>
    checkInterceptPage()
  }

  Then("""^the user clicks the Confirm email address button$""") { () =>
    CommonPage.clickContinue()
  }

  Then("""^the user is redirected to the Business contact details page within Change your registration$""") { () =>
    checkBusinessContactDetails()
  }
}

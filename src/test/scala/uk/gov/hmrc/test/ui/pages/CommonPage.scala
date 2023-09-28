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

package uk.gov.hmrc.test.ui.pages

import io.cucumber.datatable.DataTable
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.test.ui.conf.TestConfiguration

import scala.jdk.CollectionConverters._

object CommonPage extends BasePage {
  val registrationUrl: String = TestConfiguration.url("ioss-registration-frontend")

  def goToRegistrationJourney(): Unit =
    driver.navigate().to(registrationUrl)

  def checkJourneyUrl(): Unit =
    driver.getCurrentUrl shouldBe registrationUrl + "/ioss-registered"

  def checkUrl(url: String): Unit =
    driver.getCurrentUrl should startWith(s"${TestConfiguration.url("ioss-registration-frontend")}/$url")

  def selectAnswer(data: String): Unit = {
    data match {
      case "yes" => driver.findElement(By.id("value")).click()
      case "no"  => driver.findElement(By.id("value-no")).click()
      case _     => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def clickContinue(): Unit =
    driver.findElement(By.id("continue")).click()

  def selectChoice(data: String): Unit =
    data match {
      case "Yes"                    => driver.findElement(By.id("value_0")).click()
      case "Yes, details incorrect" => driver.findElement(By.id("value_1")).click()
      case "No, different business" => driver.findElement(By.id("value_2")).click()
    }

  def enterData(data: String, inputId: String = "value"): Unit = {
    driver.findElement(By.id(inputId)).sendKeys(data)
    CommonPage.clickContinue()
  }
  def selectLink(link: String): Unit   =
    driver.findElement(By.cssSelector(s"a[href*=$link]")).click()

  def clearData(): Unit =
    driver.findElement(By.id("value")).clear()

  def goToPage(url: String): Unit =
    driver.navigate().to(s"$registrationUrl/$url")

  def completeForm(dataTable: DataTable): Unit = {
    dataTable.asMaps[String, String](classOf[String], classOf[String]).asScala.foreach { row =>
      driver.findElement(By.id(row.get("fieldId"))).clear()
      driver.findElement(By.id(row.get("fieldId"))).sendKeys(row.get("data"))
    }
    clickContinue()
  }

  def waitForElement(by: By) =
    new FluentWait(driver).until(ExpectedConditions.presenceOfElementLocated(by))
  def selectValueAutocomplete(country: String): Unit = {
    val inputId = "value"
    driver.findElement(By.id(inputId)).sendKeys(country)
    waitForElement(By.id(inputId))
    driver.findElement(By.cssSelector("li#value__option--0")).click()
    CommonPage.clickContinue()
  }
  def selectScheme(scheme: String): Unit = {
    scheme match {
      case "oss"  => driver.findElement(By.id("value_0")).click()
      case "ioss" => driver.findElement(By.id("value_1")).click()
      case _      => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }
}

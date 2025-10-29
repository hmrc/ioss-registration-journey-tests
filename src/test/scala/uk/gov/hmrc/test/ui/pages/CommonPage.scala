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

package uk.gov.hmrc.test.ui.pages

import io.cucumber.datatable.DataTable
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.test.ui.conf.TestConfiguration
import uk.gov.hmrc.test.ui.pages.AuthPage.fluentWait

import scala.jdk.CollectionConverters._
import scala.util.Random

object CommonPage extends BasePage {
  val registrationUrl: String = TestConfiguration.url("ioss-registration-frontend")
  var credId: String          = "1234123412341234"

  def checkUrl(url: String): Unit = {
    val urlToCheck = s"${TestConfiguration.url("ioss-registration-frontend")}/$url"
    fluentWait.until(ExpectedConditions.urlContains(urlToCheck))
    getCurrentUrl should startWith(urlToCheck)
  }

  def selectAnswer(data: String): Unit = {
    data match {
      case "yes" => click(By.id("value"))
      case "no"  => click(By.id("value-no"))
      case _     => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def clickContinue(): Unit =
    click(By.id("continue"))

  def clickSubmit(): Unit =
    click(By.id("submit"))

  def enterData(data: String, inputId: String = "value"): Unit = {
    sendKeys(By.id(inputId), data)
    CommonPage.clickContinue()
  }

  def selectLink(link: String): Unit =
    click(By.cssSelector(s"a[href*=$link]"))

  def goToPage(url: String): Unit =
    get(s"$registrationUrl/$url")

  def completeForm(dataTable: DataTable): Unit = {
    dataTable.asMaps[String, String](classOf[String], classOf[String]).asScala.foreach { row =>
      Driver.instance.findElement(By.id(row.get("fieldId"))).clear()
      Driver.instance.findElement(By.id(row.get("fieldId"))).sendKeys(row.get("data"))
    }
    clickContinue()
  }

  def waitForElement(by: By) =
    fluentWait.until(ExpectedConditions.presenceOfElementLocated(by))

  def selectValueAutocomplete(country: String): Unit = {
    val inputId = "value"
    sendKeys(By.id(inputId), country)
    waitForElement(By.id(inputId))
    click(By.cssSelector("li#value__option--0"))
    clickContinue()
  }

  def selectRadioButton(scheme: String): Unit = {
    scheme match {
      case "1" => click(By.id("value_0"))
      case "2" => click(By.id("value_1"))
      case _   => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def checkReturnsDashboard(): Unit =
    getCurrentUrl should startWith(TestConfiguration.url("ioss-returns-frontend") + "/your-account")

  def retrieveCredId(): String =
    credId

  def generateCredId(): Unit =
    credId = Random.between(1000000000000000L, 9000000000000000L).toString

  def clickBackButton(): Unit =
    Driver.instance
      .navigate()
      .back()

}

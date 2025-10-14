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

import uk.gov.hmrc.test.ui.pages._

class ChangeAndRemoveStepDef extends BaseStepDef {

  Then(
    """^the user selects the (list|CYA|list within CYA|list within amend|list within rejoin|additional tax details list within CYA|amend|additional tax details list within amend|additional tax details list within rejoin|rejoin) (change|add) link for (first|second|third|page) (.*) from (.*)$"""
  ) { (route: String, link: String, index: String, toPage: String, fromPage: String) =>
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
    } else if (route == "list within rejoin") {
      CommonPage.selectLink(s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Crejoin-registration")
    } else if (route == "CYA") {
      CommonPage.selectLink(s"$toPage\\?waypoints\\=$fromPage")
    } else if (route == "amend" || route == "rejoin") {
      CommonPage.selectLink(s"$toPage\\?waypoints\\=$fromPage")
    } else if (route == "additional tax details list within CYA") {
      CommonPage.selectLink(
        s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Cchange-add-tax-details\\%2Ccheck-your-answers"
      )
    } else if (route == "additional tax details list within amend") {
      CommonPage.selectLink(
        s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Cchange-add-tax-details\\%2Cchange-your-registration"
      )
    } else if (route == "additional tax details list within rejoin") {
      CommonPage.selectLink(
        s"$toPage\\/$changeIndex\\?waypoints\\=$fromPage\\%2Cchange-add-tax-details\\%2Crejoin-registration"
      )
    }
  }

  Then(
    """^the user clicks remove via (list|CYA route|overviewLoop|amend route|rejoin route) for (first|second|third) (.*)$"""
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
    } else if (route == "rejoin route") {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex\\?waypoints\\=rejoin-registration")
    } else if (route == "overviewLoop") {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex\\?waypoints\\=change-previous-schemes-overview")
    } else {
      CommonPage.selectLink(s"remove-$page\\/$removeIndex")
    }
  }
}

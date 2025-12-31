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

package uk.gov.hmrc.ui.specs.ChangeTests

import uk.gov.hmrc.ui.pages.{Auth, EmailVerification, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class ChangeAndRemoveSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("Change and remove answers for registrations in progress") {

    Scenario("Remove answers via list pages during registration for NI Trader") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      And("the user adds trading names")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("Alternative trading")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("789 & trading")
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the user removes the trading names they just added")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/2"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user enters previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Hungary")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/1/1")
      registration.enterAnswer("HU11122233")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-scheme/1/2")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-ioss-number/1/2")
      registration.enterIossScheme("IM3487777777")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Austria")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EU123456788")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")

      And("the user removes the previous schemes they have added")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "remove-registration\\/2"
      )
      registration.checkJourneyUrl("remove-registration/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "previous-scheme-answers\\/1\\?waypoints\\=change-previous-schemes-overview"
      )
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.selectChangeOrRemoveLink(
        "remove-previous-scheme\\/1\\/2\\?waypoints\\=change-previous-schemes-overview"
      )
      registration.checkJourneyUrl("remove-previous-scheme/1/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.selectChangeOrRemoveLink(
        "remove-previous-scheme\\/1\\/1\\?waypoints\\=change-previous-schemes-overview"
      )
      registration.checkJourneyUrl("remove-previous-scheme/1/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user enters other registration details")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Bulgaria")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("BG123455555")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Bulgarian Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("1 Street Name", "", "A Town", "", "")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/2")
      registration.selectCountry("Greece")
      registration.checkJourneyUrl("eu-fixed-establishment/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/2")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/2")
      registration.enterAnswer("Greece123456")
      registration.checkJourneyUrl("eu-trading-name/2")
      registration.enterAnswer("Grecian Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/2")
      registration.enterFixedEstablishmentAddress("1 Street Name", "Suburb", "A Town", "", "G23566")
      registration.checkJourneyUrl("check-tax-details/2")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")

      Then("the user removes the other registration details they have just added")
      registration.selectChangeOrRemoveLink(
        "remove-tax-details\\/2"
      )
      registration.checkJourneyUrl("remove-tax-details/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-tax-details")
      registration.selectChangeOrRemoveLink(
        "remove-tax-details\\/1"
      )
      registration.checkJourneyUrl("remove-tax-details/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      Then("the user adds websites")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.1st-website.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("https://website2.eu")
      registration.checkJourneyUrl("add-website-address")

      And("the user removes the websites they just added")
      registration.selectChangeOrRemoveLink(
        "remove-website-address\\/2"
      )
      registration.checkJourneyUrl("remove-website-address/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-website-address")
      registration.selectChangeOrRemoveLink(
        "remove-website-address\\/1"
      )
      registration.checkJourneyUrl("remove-website-address/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.new1st-website.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      Then("the user enters contact information on the business-contact-details page")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Trader Full-Name", "012012365214", "another-test@testemail.com")

      Then("the user completes the email verification process")
      email.completeEmailVerification("registration")

      Then("the user enters bank or building society account details on bank-account-details page")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Trader Full-Name", "ABCDEF2A", "GB33BUKB20201555555555")

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful")
    }

    Scenario("Change answers via list pages during registration for NI Trader") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      And("the user adds trading names")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("A trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("2nd name!")
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the user amends and removes the trading names they just added")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/2\\?waypoints\\=change-add-uk-trading-name"
      )
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("Different trading name")

      registration.checkJourneyUrl("add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user enters previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Belgium")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/1/1")
      registration.enterAnswer("BE0123456777")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-scheme/1/2")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-ioss-number/1/2")
      registration.enterIossScheme("IM0563332221")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Estonia")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EE111111111")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")

      And("the user amends the previous schemes they have added")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "previous-scheme-answers\\/1\\?waypoints\\=change-previous-schemes-overview"
      )
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-scheme/1/3")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/1/3")
      registration.enterAnswer("EU111555999")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.continue()
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "previous-scheme-answers\\/2\\?waypoints\\=change-previous-schemes-overview"
      )
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-scheme/2/2")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/2/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-ioss-number/2/2")
      registration.enterIossScheme("IM2339876543")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user enters other registration details")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Italy")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("IT01234567899")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Italian Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("1 Street Name", "", "A Town", "", "")
      registration.checkJourneyUrl("check-tax-details/1")

      Then("the user amends the other registration details they just added")
      registration.selectChangeOrRemoveLink(
        "eu-vat-number\\/1\\?waypoints\\=check-tax-details-1"
      )
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("IT01234567888")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.selectChangeOrRemoveLink(
        "eu-fixed-establishment-address\\/1\\?waypoints\\=check-tax-details-1"
      )
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.updateField("line2", "Suburb")
      registration.continue()
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/2")
      registration.selectCountry("Denmark")
      registration.checkJourneyUrl("eu-fixed-establishment/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/2")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/2")
      registration.enterAnswer("DK123456")
      registration.checkJourneyUrl("eu-trading-name/2")
      registration.enterAnswer("DK Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/2")
      registration.enterFixedEstablishmentAddress("1 Street Name", "Suburb", "A Town", "", "DK3566")
      registration.checkJourneyUrl("check-tax-details/2")
      registration.selectChangeOrRemoveLink(
        "registration-tax-type\\/2\\?waypoints\\=check-tax-details-2"
      )
      registration.checkJourneyUrl("registration-tax-type/2")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/2")
      registration.enterAnswer("DK12345678")
      registration.checkJourneyUrl("check-tax-details/2")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")

      Then("the user adds websites")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.1st-website.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("https://website2.eu")
      registration.checkJourneyUrl("add-website-address")

      And("the user amends the websites they just added")
      registration.selectChangeOrRemoveLink(
        "website-address\\/1\\?waypoints\\=change-add-website-address"
      )
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("http://www.1st-website.com")
      registration.checkJourneyUrl("add-website-address")
      registration.selectChangeOrRemoveLink(
        "website-address\\/2\\?waypoints\\=change-add-website-address"
      )
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("2ndwebsite-amend.eu")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/3")
      registration.enterAnswer("www.finalwebsite.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      Then("the user enters contact information on the business-contact-details page")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("First Second Last", "12541256321", "email@company-website.eu")

      Then("the user completes the email verification process")
      email.completeEmailVerification("registration")

      Then("the user enters bank or building society account details on bank-account-details page")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("First Second Last", "ABCDEF2A", "GB33BUKB20201555555555")

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful")
    }
  }
}

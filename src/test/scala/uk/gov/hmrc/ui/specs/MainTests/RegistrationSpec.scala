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

package uk.gov.hmrc.ui.specs.MainTests

import uk.gov.hmrc.ui.pages.{Auth, EmailVerification, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class RegistrationSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("Registration journeys") {

    Scenario("Full IOSS Registration journey for NI Trader") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user selects no on the ioss-registered page")
      registration.answerRadioButton("no")

      And("the user selects yes on the selling-goods-outside-single-market page")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")

      And("the user selects yes on the goods-value page")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")

      And("the user selects yes on the registered-for-vat-in-uk page")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("yes")

      And("the user selects yes on the ni-based page")
      registration.checkJourneyUrl("ni-based")
      registration.answerRadioButton("yes")

      Then("the user presses continue on the register-to-use-service page")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()

      Then("the user selects yes on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      And("the user selects yes on the have-uk-trading-name page")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")

      And("the user adds the first trading name")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("A trading name")

      And("the user selects yes on the add-uk-trading-name page")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")

      And("the user adds the second trading name")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("2nd name!")

      And("the user selects yes on the add-uk-trading-name page")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")

      And("the user adds the second trading name")
      registration.checkJourneyUrl("uk-trading-name/3")
      registration.enterAnswer("Number 3")

      And("the user selects no on the add-uk-trading-name page")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user selects on yes on previous-oss page")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")

      Then("the user selects which country was it registered in on previous eu country page")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Hungary")

      When("the user selects OSS on the first previous-scheme page for Hungary")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("OSS")

      Then(
        "the user enters the scheme number"
      )
      registration.checkJourneyUrl("previous-oss-scheme-number/1/1")
      registration.enterAnswer("HU11122233")

      And("the user selects yes to add another registration on previous-scheme-answers page")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("yes")

      When("the user selects OSS on the second previous-scheme page for Hungary")
      registration.checkJourneyUrl("previous-scheme/1/2")
      registration.answerSchemeType("OSS")

      Then(
        "the user enters the scheme number"
      )
      registration.checkJourneyUrl("previous-oss-scheme-number/1/2")
      registration.enterAnswer("EU111222333")

      And("the user selects yes to add another registration on previous-scheme-answers page")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("yes")

      When("the user selects IOSS on the third previous-scheme page for Hungary")
      registration.checkJourneyUrl("previous-scheme/1/3")
      registration.answerSchemeType("IOSS")

      Then("the user answers yes to their client using an intermediary")
      registration.checkJourneyUrl("previous-ioss-scheme/1/3")
      registration.answerRadioButton("yes")

      And("the user adds an IOSS scheme number")
      registration.checkJourneyUrl("previous-ioss-number/1/3")
      registration.enterIossScheme("IM3487777777")

      And("the user presses continue on the previous-scheme-answers page")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.continue()

      And("the user selects yes on the previous-schemes-overview page")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")

      Then("the user selects which country was it registered in on previous eu country page")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Austria")

      When("the user selects OSS on the first previous-scheme page for Hungary")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")

      Then(
        "the user enters the scheme number"
      )
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EU123456788")

      And("the user selects no to add another registration on previous-scheme-answers page")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")

      And("the user selects no on the previous-schemes-overview page")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user selects yes on the tax-in-eu page")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")

      And(
        "the user selects a country on the eu-fixed-establishment-country page"
      )
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Romania")

      And("the user answers yes on the eu-fixed-establishment/1 page")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")

      And("the user selects the VAT Number registration type on the registration-tax-type page")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")

      And("the user enters the VAT number on the eu-vat-number page")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("RO1234567890")

      And("the user enters the trading name on the eu-trading-name/1 page")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Romanian Trading")

      And("the user enters the fixed establishment address on the eu-fixed-establishment-address/1 page")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("1 Street Name", "", "A Town", "", "")

      And("the user continues through the check-tax-details page")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()

      Then("the user selects yes on the add-tax-details page")
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")

      And(
        "the user selects another country on the eu-fixed-establishment-country page"
      )
      registration.checkJourneyUrl("eu-tax/2")
      registration.selectCountry("Estonia")

      And("the user answers yes on the eu-fixed-establishment/2 page")
      registration.checkJourneyUrl("eu-fixed-establishment/2")
      registration.answerRadioButton("yes")

      And("the user selects the Tax ID registration type on the registration-tax-type page")
      registration.checkJourneyUrl("registration-tax-type/2")
      registration.selectRegistrationType("tax id number")

      And("the user enters the Tax ID on the eu-tax-identification-number page")
      registration.checkJourneyUrl("eu-tax-identification-number/2")
      registration.enterAnswer("EST123987369")

      And("the user enters the trading name on the eu-trading-name/2 page")
      registration.checkJourneyUrl("eu-trading-name/2")
      registration.enterAnswer("Estonian Goods")

      And("the user enters the fixed establishment address on the eu-fixed-establishment-address/2 page")
      registration.checkJourneyUrl("eu-fixed-establishment-address/2")
      registration.enterFixedEstablishmentAddress("1 Street Name", "Suburb", "A Town", "", "ES23566")

      And("the user continues through the check-tax-details page")
      registration.checkJourneyUrl("check-tax-details/2")
      registration.continue()

      Then("the user selects no on the add-tax-details page")
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")

      Then("the user adds the first website address")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.first-website.com")

      Then("the user answers yes to add another website address")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")

      Then("the user adds the second website address")
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("http://www.241goods.eu")

      Then("the user answers yes to add another website address")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")

      Then("the user adds the third website address")
      registration.checkJourneyUrl("website-address/3")
      registration.enterAnswer("mywebsite.co.uk")

      Then("the user answers no to add another website address")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      Then("the user enters contact information on the business-contact-details page")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Trader Name", "07771117771", "test@testemail.com")

      Then("the user completes the email verification process")
      email.completeEmailVerification("registration")

      Then("the user enters bank or building society account details on bank-account-details page")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Trader Name ", "ABCDEF2A", "GB33BUKB20201555555555")

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful")

    }

    Scenario("IOSS Registration journey for Norwegian Trader") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("444555555", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.checkJourneyUrl("ioss-registered")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("ni-based")
      registration.answerRadioButton("no")

      And("the user selects yes on the norway-based page")
      registration.checkJourneyUrl("norway-based")
      registration.answerRadioButton("yes")

      Then("the user presses continue on the register-to-use-service page")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()

      Then("the user selects yes on the confirm-vat-details page")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      And("the user enters an additional trading name")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("Norwegian trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user adds previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")

      Then("the user selects which country was it registered in on previous eu country page")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Cyprus")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM1962223333")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Finland")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EU222456788")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user enters fixed establishment details")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Spain")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/1")
      registration.enterAnswer("1236ES34x")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Spanish-Trading Name")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress(
        "5201 Spanish Plaza",
        "Spanish Area",
        "Barcelona",
        "Catalonia",
        "ES 56201"
      )
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/2")
      registration.selectCountry("Germany")
      registration.checkJourneyUrl("eu-fixed-establishment/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/2")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/2")
      registration.enterAnswer("DE999555111")
      registration.checkJourneyUrl("eu-trading-name/2")
      registration.enterAnswer("German Food Wholesalers Ltd")
      registration.checkJourneyUrl("eu-fixed-establishment-address/2")
      registration.enterFixedEstablishmentAddress("63 German Street", "", "Munich", "", "")
      registration.checkJourneyUrl("check-tax-details/2")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")

      Then("the user adds websites")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("a-norwegian-website.no")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("www.2nd-norwegian-website.no")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      Then("the user enters contact information on the business-contact-details page")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Trader Name", "07771117771", "test@testemail.com")
      email.completeEmailVerification("registration")

      Then("the user enters bank or building society account details on bank-account-details page")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Trader Name", "ABCDEF2A", "GB33BUKB20201555555555")

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()
      registration.checkJourneyUrl("successful")
    }

    Scenario("Minimal IOSS Registration journey for NI Trader") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")
      registration.checkJourneyUrl("ioss-registered")

      When("the user answers all of the filter questions")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("selling-goods-outside-single-market")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("goods-value")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registered-for-vat-in-uk")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("ni-based")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("register-to-use-service")
      registration.continue()

      Then("the user only adds minimal details to their registration")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("https://www.onlywebsite.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("business-contact-details")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")
      email.completeEmailVerification("registration")
      registration.checkJourneyUrl("bank-account-details")
      registration.fillBankAccountDetails("Another Trader Name", "", "GB29NWBK60161331926819")

      And("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()
      registration.checkJourneyUrl("successful")

    }
  }
}

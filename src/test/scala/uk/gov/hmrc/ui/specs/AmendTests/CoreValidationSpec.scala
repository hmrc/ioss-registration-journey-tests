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

package uk.gov.hmrc.ui.specs.AmendTests

import uk.gov.hmrc.ui.pages.{Auth, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class CoreValidationSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth

  Feature("Core Validation Scenarios") {

    Scenario("Trader with existing EU registration is not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("333333333", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user is on the already-registered-other-country?countryCode=EE page")
      registration.checkJourneyUrl("already-registered-other-country?countryCode=EE")
    }

    Scenario("Trader with existing EU quarantine is not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("333333334", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user is on the other-country-excluded-and-quarantined?countryCode=EE&exclusionDate= page")
      registration.checkJourneyUrl("other-country-excluded-and-quarantined?countryCode=EE&exclusionDate=")
    }

    Scenario("Trader with active OSS scheme - previous registration - can register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user can enter an active OSS scheme")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Slovenia")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/1/1")
      registration.enterAnswer("SI11223344")

      And("the user can progress the registration")
      registration.checkJourneyUrl("previous-scheme-answers/1")
    }

    Scenario("Trader with active IOSS scheme - previous registration - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters an active IOSS scheme")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Slovenia")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM7051122334")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("scheme-still-active?countryCode=SI")
    }

    Scenario("Trader with quarantined OSS scheme - previous registration - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters a quarantined OSS scheme")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Latvia")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/1/1")
      registration.enterAnswer("LV11111222222")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("scheme-quarantined")
    }

    Scenario("Trader with quarantined IOSS scheme - previous registration - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters a quarantined IOSS scheme")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Latvia")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM4281122334")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("scheme-quarantined")
    }

    Scenario("Trader with active scheme - EU details - VRN - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters an active registration with EU VRN")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Portugal")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("PT111222333")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("fixed-establishment-vrn-already-registered?countryCode=PT")
    }

    Scenario("Trader with active scheme - EU details - Tax ID - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters an active registration with Tax Reference")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Portugal")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/1")
      registration.enterAnswer("123LIS123")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("fixed-establishment-vrn-already-registered?countryCode=PT")
    }

    Scenario("Trader with quarantined scheme - EU details - VRN - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters a quarantined registration with EU VRN")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Lithuania")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("LT999888777")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("excluded-vrn")
    }

    Scenario("Trader with quarantined scheme - EU details - Tax ID - not able to register") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      When("the user answers the filter questions")
      registration.standardFilterQuestions()

      Then("the user enters a quarantined registration with Tax Reference")
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Lithuania")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/1")
      registration.enterAnswer("ABC123123")

      And("the user cannot progress the registration")
      registration.checkJourneyUrl("excluded-vrn")
    }
  }
}

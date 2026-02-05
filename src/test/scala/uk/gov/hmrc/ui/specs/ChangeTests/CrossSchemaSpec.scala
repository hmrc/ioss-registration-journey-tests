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

import uk.gov.hmrc.ui.pages.{Auth, CrossSchema, EmailVerification, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class CrossSchemaSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification
  private val crossSchema  = CrossSchema

  Feature("Cross Schema journeys") {

    Scenario("Registration for trader with an OSS registration - amends data") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("300000002", "Organisation", "crossSchemaOss", "registration")

      Then("the user completes the filter questions")
      registration.standardFilterQuestions()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      When("the user is on the trading names section")
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with an OSS registration")
      crossSchema.checkTradingNames("an OSS")
      crossSchema.checkWarningsForTradingNames("registration", true, "current OSS")

      And("the user removes existing trading name and adds a new one")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/2"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("New 2nd trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user selects no on previous-oss page")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user selects no on the tax-in-eu page")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      Then("the user adds a website address")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("https://www.onlywebsite.com")

      Then("the user answers no to add another website address")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      And("the correct warnings are displayed on the contact details for a trader with an existing OSS registration")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("registration", true, "an OSS", "contact")

      And("the user enters their contact details")
      registration.fillContactDetails("Trader Name", "07771117771", "test@testemail.com")

      Then("the user completes the email verification process")
      email.completeEmailVerification("registration")

      And("the correct warnings are displayed on the bank details for a trader with an existing OSS registration")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("registration", true, "an OSS", "bank")

      And("the user enters their bank details")
      registration.fillBankAccountDetails("Trader Name ", "ABCDEF2A", "GB33BUKB20201555555555")

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful")

      And(
        "the text on the registration confirmation page is displayed when the user has made changes and has an OSS registration"
      )
      crossSchema.checkConfirmation("registration", true, "an OSS")
    }

    Scenario(
      "Amend registration for trader with an OSS registration and multiple previous IOSS registrations - amends data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("300000002", "Organisation", "crossSchemaOssAndIoss", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with OSS and IOSS registrations")
      crossSchema.checkTradingNames("OSS and IOSS")
      crossSchema.checkWarningsForTradingNames("amend", true, "OSS and IOSS")

      And("the user amends an existing trading name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/1\\?waypoints\\=change-add-uk-trading-name\\%2Cchange-your-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("an amended cross schema trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-your-registration"
      )

      And("the correct warnings are displayed on the contact details for a trader with OSS and IOSS registrations")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", true, "OSS and IOSS", "contact")

      Then("the user amends some of their contact details")
      registration.updateField("telephoneNumber", "+17771117771")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-your-registration"
      )

      And("the correct warnings are displayed on the bank details for a trader with OSS and IOSS registrations")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", true, "OSS and IOSS", "bank")

      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("iban", "GB29NWBK60161331926819")
      registration.continue()

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the text on the amend confirmation page is displayed when the user has made changes and has OSS and IOSS registration"
      )
      crossSchema.checkConfirmation("amend", true, "OSS and IOSS")

      And("the correct details are shown as amended")
      crossSchema.checkAmendedAnswers("ossAndIoss")
    }

    Scenario("Amend registration for trader with multiple previous IOSS registrations - amends data") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "crossSchemaIoss", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with multiple IOSS registrations")
      crossSchema.checkTradingNames("multiple IOSS")
      crossSchema.checkWarningsForTradingNames("amend", true, "multiple IOSS")

      And("the user amends an existing trading name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/2\\?waypoints\\=change-add-uk-trading-name\\%2Cchange-your-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("another")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-your-registration"
      )

      And("the correct warnings are displayed on the contact details for a trader with multiple IOSS registrations")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", true, "multiple IOSS", "contact")

      Then("the user amends some of their contact details")
      registration.updateField("emailAddress", "amend-cross-schema-test@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-your-registration"
      )

      And("the correct warnings are displayed on the bank details for a trader with multipleIOSS registrations")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", true, "multiple IOSS", "bank")

      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Another Cross Schema Name")
      registration.continue()

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the text on the amend confirmation page is displayed when the user has made changes and has OSS and IOSS registration"
      )
      crossSchema.checkConfirmation("amend", true, "multiple IOSS")

      And("the correct details are shown as amended")
      crossSchema.checkAmendedAnswers("multipleIoss")
    }

    Scenario("Rejoin registration for trader with an OSS registration and multiple IOSS registrations - amends data") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("300000002", "Organisation", "rejoinCrossSchemaOssAndIoss", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with OSS and IOSS registrations")
      crossSchema.checkTradingNames("OSS and IOSS")
      crossSchema.checkWarningsForTradingNames("rejoin", true, "OSS and IOSS")

      And("the user removes existing trading name and adds a new one")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")

      And("the user amends an existing trading name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/1\\?waypoints\\=change-add-uk-trading-name\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("an amended cross schema trading name for rejoin")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("new 2nd name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=rejoin-registration"
      )

      And("the correct warnings are displayed on the contact details for a trader with OSS and IOSS registrations")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("rejoin", true, "OSS and IOSS", "contact")

      Then("the user amends some of their contact details")
      registration.updateField("emailAddress", "rejoin-cross-schema-test@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=rejoin-registration"
      )

      And("the correct warnings are displayed on the bank details for a trader with OSS and IOSS registrations")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("rejoin", true, "OSS and IOSS", "bank")

      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Another Cross Schema Name")
      registration.continue()

      When("the user submits the registration on the rejoin-registration page")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-rejoin")

      And(
        "the text on the rejoin confirmation page is displayed when the user has made changes and has OSS and IOSS registrations"
      )
      crossSchema.checkConfirmation("rejoin", true, "OSS and IOSS")
    }

    Scenario("Rejoin registration for trader with 1 previous IOSS registration - amends data") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "crossSchemaOneIoss", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with one IOSS registration")
      crossSchema.checkTradingNames("one previous IOSS")
      crossSchema.checkWarningsForTradingNames("rejoin", true, "one previous IOSS")

      And("the user removes existing trading name and adds a new one")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")

      And("the user amends an existing trading name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/1\\?waypoints\\=change-add-uk-trading-name\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("an amended cross schema trading name for rejoin")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("new 2nd name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=rejoin-registration"
      )

      And("the correct warnings are displayed on the contact details for a trader with one IOSS registration")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("rejoin", true, "one previous IOSS", "contact")

      Then("the user amends some of their contact details")
      registration.updateField("emailAddress", "rejoin-cross-schema-test@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=rejoin-registration"
      )

      And("the correct warnings are displayed on the bank details for a trader with one IOSS registration")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("rejoin", true, "one previous IOSS", "bank")

      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Another Cross Schema Name")
      registration.continue()

      When("the user submits the registration on the rejoin-registration page")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-rejoin")

      And(
        "the text on the rejoin confirmation page is displayed when the user has made changes and has one IOSS registration"
      )
      crossSchema.checkConfirmation("rejoin", true, "one previous IOSS")
    }

    Scenario("Rejoin registration for trader with multiple previous IOSS registrations - amends data") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "crossSchemaMultipleIoss", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with multiple IOSS registrations")
      crossSchema.checkTradingNames("multiple IOSS")
      crossSchema.checkWarningsForTradingNames("rejoin", true, "multiple IOSS")

      And("the user removes existing trading name and adds a new one")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")

      And("the user amends an existing trading name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/1\\?waypoints\\=change-add-uk-trading-name\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("an amended cross schema trading name for rejoin")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("new 2nd name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=rejoin-registration"
      )

      And("the correct warnings are displayed on the contact details for a trader with multiple IOSS registrations")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("rejoin", true, "multiple IOSS", "contact")

      Then("the user amends some of their contact details")
      registration.updateField("emailAddress", "rejoin-cross-schema-test@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=rejoin-registration"
      )

      And("the correct warnings are displayed on the bank details for a trader with multiple IOSS registrations")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("rejoin", true, "multiple IOSS", "bank")

      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Another Cross Schema Name")
      registration.continue()

      When("the user submits the registration on the rejoin-registration page")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-rejoin")

      And(
        "the text on the rejoin confirmation page is displayed when the user has made changes and has multiple IOSS registrations"
      )
      crossSchema.checkConfirmation("rejoin", true, "multiple IOSS")
    }

    Scenario("Registration for trader with other registrations who did not make changes to data") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("300000002", "Organisation", "crossSchemaOss", "registration")

      Then("the user completes the filter questions")
      registration.standardFilterQuestions()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      When("the user is on the trading names section")
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the correct number of existing trading names are displayed for a user with an OSS registration")
      crossSchema.checkTradingNames("an OSS")
      crossSchema.checkWarningsForTradingNames("registration", true, "current OSS")

      And("the user answers no on the add-uk-trading-name page without amending any details")
      registration.answerRadioButton("no")

      Then("the user selects no on previous-oss page")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user selects no on the tax-in-eu page")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      Then("the user adds a website address")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("https://www.onlywebsite.com")

      Then("the user answers no to add another website address")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      And("the correct warnings are displayed on the contact details for a trader with an existing OSS registration")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("registration", true, "an OSS", "contact")

      And("the user presses continue on the business-contact-details page without amending any details")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("registration")

      And("the correct warnings are displayed on the bank details for a trader with an existing OSS registration")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("registration", true, "an OSS", "bank")

      And("the user presses continue on the bank-account-details page without amending any details")
      registration.continue()

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful")

      And(
        "the text on the registration confirmation page is not displayed when the user has not made changes and has an OSS registration"
      )
      crossSchema.checkConfirmation("registration", false, "an OSS")
    }

    Scenario(
      "Amend registration for trader with other registrations who did not make changes to data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("300000002", "Organisation", "crossSchemaOssAndIoss", "amend")

      When("the user submits the registration on the change-your-registration page without amending any answers")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the text on the amend confirmation page is not displayed when the user has not made changes and has OSS and IOSS registration"
      )
      crossSchema.checkConfirmation("amend", false, "OSS and IOSS")

      And("the confirmation page shows no details have been amended")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }

    Scenario(
      "Amend registration for trader with multiple previous IOSS registrations who did not make changes to data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "crossSchemaIoss", "amend")

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the text on the amend confirmation page is not displayed when the user has not made changes and has OSS and IOSS registration"
      )
      crossSchema.checkConfirmation("amend", false, "multiple IOSS")

      And("the confirmation page shows no details have been amended")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }

    Scenario(
      "Rejoin registration for trader with multiple previous IOSS registrations who did not make changes to data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("300000002", "Organisation", "rejoinCrossSchemaOssAndIoss", "rejoin")

      When("the user submits the registration on the rejoin-registration page")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-rejoin")

      And(
        "the text on the rejoin confirmation page is not displayed when the user has not made changes and has OSS and IOSS registrations"
      )
      crossSchema.checkConfirmation("rejoin", false, "OSS and IOSS")
    }

    Scenario(
      "Registration for trader with no other registrations does not pre-populate details and does not show warnings"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "vatOnly", "registration")

      And("the user answers the filter questions")
      registration.standardFilterQuestions()
      registration.checkJourneyUrl("confirm-vat-details")
      registration.answerVatDetailsChoice("Yes")

      When("the user answers yes on the have-uk-trading-name page")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")

      And("the user adds the first trading name")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("No registration trader")

      Then("there are no warnings relating to cross schema registrations on the add-uk-trading-name page")
      registration.checkJourneyUrl("add-uk-trading-name")
      crossSchema.onlyNewTradingNameAdded()
      crossSchema.checkWarningsForTradingNames("registration", false, "no other registrations")
      registration.answerRadioButton("no")

      Then("the user selects on no on previous-oss page")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user selects no on the tax-in-eu page")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      Then("the user adds the first website address")
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.first-website.com")

      Then("the user answers no to add another website address")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")

      Then("there are no cross schema registration warnings on the business-contact-details page")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("registration", false, "no other registrations", "contact")
      registration.fillContactDetails("Another Trader", "+17771117771", "minimaltest@email.com")

      Then("the user completes the email verification process")
      email.completeEmailVerification("registration")

      Then("there are no cross schema registration warnings on the bank-account-details page")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("registration", false, "no other registrations", "bank")
      registration.fillBankAccountDetails("Another Trader Name ", "", "GB29NWBK60161331926819")

      When("the user submits the registration on the check-your-answers page")
      registration.checkJourneyUrl("check-your-answers")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful")

      And(
        "the text on the registration confirmation page is not displayed when the user has no other registrations"
      )
      crossSchema.checkConfirmation("registration", false, "no other registrations")
    }

    Scenario(
      "Amend registration for trader with no other registrations does not show warnings"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "amend", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the cross schema warnings are not displayed when the user has no other registrations")
      crossSchema.checkWarningsForTradingNames("amend", false, "no other registrations")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-your-registration"
      )

      Then("the cross schema warnings are not displayed when the user has no other registrations")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", false, "no other registrations", "contact")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-your-registration"
      )

      Then("the cross schema warnings are not displayed when the user has no other registrations")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", false, "no other registrations", "bank")
      registration.continue()

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the text on the amend confirmation page is not displayed when the user has no other registration"
      )
      crossSchema.checkConfirmation("amend", false, "no other registrations")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }

    Scenario(
      "Amend registration for trader with 1 previous IOSS registration - amends data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "crossSchemaOneIoss", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-your-registration"
      )

      Then("the cross schema warnings are not displayed for a user who only has one previous IOSS registration")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", false, "one previous IOSS", "contact")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-your-registration"
      )

      Then("the cross schema warnings are not displayed for a user who only has one previous IOSS registration")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", false, "one previous IOSS", "bank")
      registration.continue()

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the cross schema text on the amend confirmation page is not displayed"
      )
      crossSchema.checkConfirmation("amend", false, "one previous IOSS")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }

    Scenario(
      "Amend registration for trader with 1 current IOSS registration - amends data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "amend", "amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=change-your-registration"
      )
      registration.checkJourneyUrl("add-uk-trading-name")

      Then("the cross schema warnings are not displayed for a user who only has one current IOSS registration")
      crossSchema.checkWarningsForTradingNames("amend", false, "one current IOSS")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=change-your-registration"
      )

      Then("the cross schema warnings are not displayed for a user who only has one current IOSS registration")
      registration.checkJourneyUrl("business-contact-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", false, "one current IOSS", "contact")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("amend")
      registration.checkJourneyUrl("change-your-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=change-your-registration"
      )

      Then("the cross schema warnings are not displayed for a user who only has one current IOSS registration")
      registration.checkJourneyUrl("bank-account-details")
      crossSchema.checkWarningsForBankAndContactDetails("amend", false, "one current IOSS", "bank")
      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("iban", "GB29NWBK60161331926819")
      registration.continue()

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the cross schema text on the amend confirmation page is not displayed"
      )
      crossSchema.checkConfirmation("amend", false, "one current IOSS")
      crossSchema.checkAmendedAnswers("updatedIban")
    }

    Scenario(
      "Amend registration for trader with 1 previous IOSS registration - does not amend data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "crossSchemaOneIoss", "amend")

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the cross schema text on the amend confirmation page is not displayed"
      )
      crossSchema.checkConfirmation("amend", false, "one previous IOSS")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }

    Scenario(
      "Amend registration for trader with 1 current IOSS registration - does not amend data"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100005555", "Organisation", "amend", "amend")

      When("the user submits the registration on the change-your-registration page")
      registration.checkJourneyUrl("change-your-registration")
      registration.submit()

      Then("the user is on the successful submission page")
      registration.checkJourneyUrl("successful-amend")

      And(
        "the cross schema text on the amend confirmation page is not displayed"
      )
      crossSchema.checkConfirmation("amend", false, "one current IOSS")
      registration.checkAmendedAnswers("noAmendedAnswers")
    }
  }
}

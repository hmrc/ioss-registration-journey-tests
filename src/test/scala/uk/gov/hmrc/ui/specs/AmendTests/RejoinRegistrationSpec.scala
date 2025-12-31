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

import uk.gov.hmrc.ui.pages.{Auth, EmailVerification, Registration}
import uk.gov.hmrc.ui.specs.BaseSpec

class RejoinRegistrationSpec extends BaseSpec {

  private val registration = Registration
  private val auth         = Auth
  private val email        = EmailVerification

  Feature("Rejoin Registration journeys") {

    Scenario("A trader with a future exclusion effective date is not able to access the rejoin registration journey") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "excludedFuture", "rejoin")

      Then("the user is on the cannot-rejoin page")
      registration.checkJourneyUrl("cannot-rejoin")
    }

    Scenario("A quarantined trader is not able to access the rejoin registration journey") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "quarantined", "rejoin")

      Then("the user is on the cannot-rejoin page")
      registration.checkJourneyUrl("cannot-rejoin")
    }

    Scenario("A currently registered IOSS trader who is not excluded cannot access the rejoin registration journey") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "notExcluded", "rejoin")

      Then("the user is on the cannot-rejoin page")
      registration.checkJourneyUrl("cannot-rejoin")
    }

    Scenario(
      "A user who gets not found during a rejoin from ETMP for an IOSS registration receives the sorry there is a problem page"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "etmpNotFound", "rejoin")
      registration.checkJourneyUrl("start-rejoin-journey")

      Then("the user is on the problem page")
      registration.checkProblemPage()
    }

    Scenario("An IOSS registered user receives an ETMP failure on submission of a rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "rejoinFailure", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user submits the rejoin")
      registration.submit()

      Then("the user is on the error-submitting-rejoin page")
      registration.checkJourneyUrl("error-submitting-rejoin")
    }

    Scenario(
      "A trader with an expired quarantine period can submit a rejoin registration without amending any details"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "quarantinedRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user submits the rejoin")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("An excluded trader with an effective date 6 years ago can access and submit a rejoin registration") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "excludedSixYears", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user submits the rejoin")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("A trader with minimal details in their original registration can amend and rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "minimalRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for have-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "have-uk-trading-name\\?waypoints\\=rejoin-registration"
      )

      And("the user enters a trading name")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("A new trading name in rejoin journey")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user clicks change for previous-oss")
      registration.checkJourneyUrl("rejoin-registration")
      registration.selectChangeOrRemoveLink(
        "previous-oss\\?waypoints\\=rejoin-registration"
      )

      Then("the user enters previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Portugal")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM6207777777")
      registration.checkJourneyUrl("previous-scheme-answers/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/2")
      registration.selectCountry("Estonia")
      registration.checkJourneyUrl("previous-scheme/2/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/1")
      registration.enterAnswer("EU123456789")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")

      Then("the user clicks change for tax-in-eu")
      registration.checkJourneyUrl("rejoin-registration")
      registration.selectChangeOrRemoveLink(
        "tax-in-eu\\?waypoints\\=rejoin-registration"
      )

      Then("the user enters other registration details")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("France")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("FRXX123456789")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("French Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("1 Street Name", "", "A Town", "", "")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")

      When("the user submits the rejoin")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("A trader with optional answers in original registration set to yes can change to no and rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for Have UK trading name")
      registration.selectChangeOrRemoveLink(
        "have-uk-trading-name\\?waypoints\\=rejoin-registration"
      )

      Then("the user amends the answer to no")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")

      And("the user answers yes on the remove-all-trading-names page")
      registration.checkJourneyUrl("remove-all-trading-names")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for Tax in EU")
      registration.selectChangeOrRemoveLink(
        "tax-in-eu\\?waypoints\\=rejoin-registration"
      )

      Then("the user amends the answer to no")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      And("the user answers yes on the remove-all-tax-details page")
      registration.checkJourneyUrl("remove-all-tax-details")
      registration.answerRadioButton("yes")

      When("the user submits the rejoin")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario(
      "A trader can add details for sections during rejoin that were previously answered no and then remove them"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "minimalRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for have-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "have-uk-trading-name\\?waypoints\\=rejoin-registration"
      )

      And("the user enters a trading name")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("A new trading name in amend journey")
      registration.checkJourneyUrl("add-uk-trading-name")

      When("the user removes the trading name they just added")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("have-uk-trading-name")
      registration.answerRadioButton("no")

      Then("the user clicks change for previous-oss")
      registration.checkJourneyUrl("rejoin-registration")
      registration.selectChangeOrRemoveLink(
        "previous-oss\\?waypoints\\=rejoin-registration"
      )

      Then("the user enters previous scheme details")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/1")
      registration.selectCountry("Cyprus")
      registration.checkJourneyUrl("previous-scheme/1/1")
      registration.answerSchemeType("IOSS")
      registration.checkJourneyUrl("previous-ioss-scheme/1/1")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-ioss-number/1/1")
      registration.enterIossScheme("IM1962223333")
      registration.checkJourneyUrl("previous-scheme-answers/1")

      Then("the user removes the scheme they just added")
      registration.selectChangeOrRemoveLink(
        "remove-previous-scheme\\/1\\/1\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("remove-previous-scheme/1/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-oss")
      registration.answerRadioButton("no")

      Then("the user clicks change for tax-in-eu")
      registration.checkJourneyUrl("rejoin-registration")
      registration.selectChangeOrRemoveLink(
        "tax-in-eu\\?waypoints\\=rejoin-registration"
      )

      Then("the user enters other registration details")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Romania")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("RO1234567890")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Romanian Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("1 Street Name", "", "A Town", "", "")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")

      Then("the user removes the registration they just added")
      registration.selectChangeOrRemoveLink(
        "remove-tax-details\\/1\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("remove-tax-details/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("no")

      When("the user submits the rejoin")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("A trader can amend non-mandatory answers during rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "add-uk-trading-name\\?waypoints\\=rejoin-registration"
      )

      Then("the user removes, amends and adds answers within the trading name section")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "remove-uk-trading-name\\/1\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("remove-uk-trading-name/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.selectChangeOrRemoveLink(
        "uk-trading-name\\/1\\?waypoints\\=change-add-uk-trading-name\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("uk-trading-name/1")
      registration.enterAnswer("an amended trading name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("uk-trading-name/2")
      registration.enterAnswer("new 2nd name")
      registration.checkJourneyUrl("add-uk-trading-name")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "previous-schemes-overview\\?waypoints\\=rejoin-registration"
      )

      Then("the user adds answers within the previous schemes section")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.selectChangeOrRemoveLink(
        "previous-scheme-answers\\/2\\?waypoints\\=change-previous-schemes-overview\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("yes")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/2/2")
      registration.enterAnswer("CY12345678X")
      registration.checkJourneyUrl("previous-scheme-answers/2")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("previous-country/3")
      registration.selectCountry("Finland")
      registration.checkJourneyUrl("previous-scheme/3/1")
      registration.answerSchemeType("OSS")
      registration.checkJourneyUrl("previous-oss-scheme-number/3/1")
      registration.enterAnswer("EU222456788")
      registration.checkJourneyUrl("previous-scheme-answers/3")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("previous-schemes-overview")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-tax-details")
      registration.selectChangeOrRemoveLink(
        "add-tax-details\\?waypoints\\=rejoin-registration"
      )

      Then("the user adds and amends answers within the other registrations section")
      registration.checkJourneyUrl("add-tax-details")
      registration.selectChangeOrRemoveLink(
        "check-tax-details\\/1\\?waypoints\\=change-add-tax-details\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("check-tax-details/1")
      registration.selectChangeOrRemoveLink(
        "registration-tax-type\\/1\\?waypoints\\=check-tax-details-1\\%2Cchange-add-tax-details\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/1")
      registration.enterAnswer("TAXID12345A")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.selectChangeOrRemoveLink(
        "eu-fixed-establishment-address\\/1\\?waypoints\\=check-tax-details-1\\%2Cchange-add-tax-details\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("Line 1", "Suburb", "Town", "Region", "AB12 3CD")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/2")
      registration.selectCountry("Estonia")
      registration.checkJourneyUrl("eu-fixed-establishment/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/2")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/2")
      registration.enterAnswer("EE123456789")
      registration.checkJourneyUrl("eu-trading-name/2")
      registration.enterAnswer("Estonian Wholesalers Ltd")
      registration.checkJourneyUrl("eu-fixed-establishment-address/2")
      registration.enterFixedEstablishmentAddress("111 Estonian Street", "", "Tallinn", "", "TAL 1234A")
      registration.checkJourneyUrl("check-tax-details/2")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/3")
      registration.selectCountry("Portugal")
      registration.checkJourneyUrl("eu-fixed-establishment/3")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/3")
      registration.selectRegistrationType("tax id number")
      registration.checkJourneyUrl("eu-tax-identification-number/3")
      registration.enterAnswer("PT12345A")
      registration.checkJourneyUrl("eu-trading-name/3")
      registration.enterAnswer("Portuguese Trading")
      registration.checkJourneyUrl("eu-fixed-establishment-address/3")
      registration.enterFixedEstablishmentAddress("111 Porto Street", "", "Porto", "", "")
      registration.checkJourneyUrl("check-tax-details/3")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.selectChangeOrRemoveLink(
        "check-tax-details\\/3\\?waypoints\\=change-add-tax-details\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("check-tax-details/3")
      registration.selectChangeOrRemoveLink(
        "registration-tax-type\\/3\\?waypoints\\=check-tax-details-3\\%2Cchange-add-tax-details\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("registration-tax-type/3")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/3")
      registration.enterAnswer("PT123456789")
      registration.checkJourneyUrl("check-tax-details/3")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")

      When("the user submits the rejoin")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("A trader amends their email address during rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=rejoin-registration"
      )

      Then("the user amends their email address")
      registration.checkJourneyUrl("business-contact-details")
      registration.updateField("emailAddress", "rejoin-test@email.com")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("rejoin")

      When("the user submits the rejoin")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("A trader removes some registration answers and amends mandatory answers during rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-tax-details")
      registration.selectChangeOrRemoveLink(
        "add-tax-details\\?waypoints\\=rejoin-registration"
      )

      Then("the user removes details from the other registrations section")
      registration.checkJourneyUrl("add-tax-details")
      registration.selectChangeOrRemoveLink(
        "remove-tax-details\\/1\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("remove-tax-details/1")
      registration.answerRadioButton("yes")

      Then("the user adds new details to the other registrations section")
      registration.checkJourneyUrl("tax-in-eu")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("eu-tax/1")
      registration.selectCountry("Estonia")
      registration.checkJourneyUrl("eu-fixed-establishment/1")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("registration-tax-type/1")
      registration.selectRegistrationType("vat number")
      registration.checkJourneyUrl("eu-vat-number/1")
      registration.enterAnswer("EE123456789")
      registration.checkJourneyUrl("eu-trading-name/1")
      registration.enterAnswer("Estonian Wholesalers Ltd")
      registration.checkJourneyUrl("eu-fixed-establishment-address/1")
      registration.enterFixedEstablishmentAddress("111 Estonian Street", "", "Tallinn", "", "")
      registration.checkJourneyUrl("check-tax-details/1")
      registration.continue()
      registration.checkJourneyUrl("add-tax-details")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for add-website-address")
      registration.selectChangeOrRemoveLink(
        "add-website-address\\?waypoints\\=rejoin-registration"
      )

      Then("the user removes and amends the website section")
      registration.checkJourneyUrl("add-website-address")
      registration.selectChangeOrRemoveLink(
        "remove-website-address\\/2\\?waypoints\\=rejoin-registration"
      )
      registration.checkJourneyUrl("remove-website-address/2")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("add-website-address")
      registration.selectChangeOrRemoveLink(
        "website-address\\/1\\?waypoints\\=change-add-website-address\\%2Crejoin-registration"
      )
      registration.checkJourneyUrl("website-address/1")
      registration.enterAnswer("www.amended-website-name.com")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("yes")
      registration.checkJourneyUrl("website-address/2")
      registration.enterAnswer("https://www.2nd-website.eu")
      registration.checkJourneyUrl("add-website-address")
      registration.answerRadioButton("no")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for business-contact-details")
      registration.selectChangeOrRemoveLink(
        "business-contact-details\\?waypoints\\=rejoin-registration"
      )

      Then("the user amends some of their contact details")
      registration.checkJourneyUrl("business-contact-details")
      registration.updateField("fullName", "Another Trader")
      registration.updateField("telephoneNumber", "+17771117771")
      registration.continue()

      Then("the user completes the email verification process")
      email.completeEmailVerification("rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When("the user clicks change for bank-account-details")
      registration.selectChangeOrRemoveLink(
        "bank-account-details\\?waypoints\\=rejoin-registration"
      )

      Then("the user amends some of their bank details")
      registration.checkJourneyUrl("bank-account-details")
      registration.updateField("accountName", "Another Trader Name")
      registration.updateField("iban", "GB29NWBK60161331926819")
      registration.continue()

      When("the user submits the rejoin")
      registration.checkJourneyUrl("rejoin-registration")
      registration.submit()

      Then("the user is on the successful-rejoin page")
      registration.checkJourneyUrl("successful-rejoin")
    }

    Scenario("A trader cannot remove existing previous registrations retrieved from ETMP when they rejoin") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When(
        "the user manually navigates to the remove-all-previous-registrations?waypoints=rejoin-registration page"
      )
      registration.goToPage("remove-all-previous-registrations?waypoints=rejoin-registration")

      Then("the user is on the problem page")
      registration.checkJourneyUrl("remove-all-previous-registrations?waypoints=rejoin-registration")
      registration.checkProblemPage()
    }

    Scenario(
      "A trader cannot remove cannot remove an individual previous scheme if they were retrieved from the ETMP registration during rejoin"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When(
        "the user manually navigates to the remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview%2Crejoin-registration page"
      )
      registration.goToPage(
        "remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview%2Crejoin-registration"
      )

      Then("the user is on the cannot-delete-previous-registrations page")
      registration.checkJourneyUrl("cannot-delete-previous-registrations")
    }

    Scenario(
      "A trader cannot remove previous schemes for a country if it was retrieved from the ETMP registration during rejoin"
    ) {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("100000001", "Organisation", "fullRejoin", "rejoin")
      registration.checkJourneyUrl("rejoin-registration")

      When(
        "the user manually navigates to the remove-registration/1?waypoints=rejoin-registration page"
      )
      registration.goToPage(
        "remove-registration/1?waypoints=rejoin-registration"
      )

      Then("the user is on the cannot-delete-previous-registrations page")
      registration.checkJourneyUrl("cannot-delete-previous-registrations")
    }

    Scenario("A user that has now deregistered from VAT cannot re-register for the IOSS scheme") {

      Given("the trader accesses the IOSS Registration Service")
      auth.goToAuthorityWizard()
      auth.loginUsingAuthorityWizard("600000001", "Organisation", "deregisteredVat", "rejoin")

      Then("the user is on the cannot-rejoin page")
      registration.checkJourneyUrl("cannot-rejoin")
    }
  }
}

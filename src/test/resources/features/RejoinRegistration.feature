@Registration @Accessibility

Feature: Rejoin Registration journeys

  @ZAP
  Scenario: An IOSS registered user rejoins registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page have-uk-trading-name from rejoin-registration
    And the user answers no on the have-uk-trading-name page
    And the user answers yes on the remove-all-trading-names page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers no on the tax-in-eu page
    Then the user answers yes on the remove-all-tax-details page
    Then the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page
#
  Scenario: An IOSS registered user cannot remove all previous registrations if they were retrieved from the ETMP registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    And the user manually navigates to the remove-all-previous-registrations?waypoints=rejoin-registration page
    Then the user is presented with the technical difficulties page

  Scenario: An IOSS registered cannot rejoin if there is not a valid exclusion attached to their registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999997 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: An IOSS registered user cannot use remove all previous registrations if they were entered during the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Republic of Cyprus on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers no on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId              |
      | IM1962223333 | previousSchemeNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user is on the rejoin-registration page
    And the user manually navigates to the remove-all-previous-registrations?waypoints=rejoin-registration page
    Then the user is presented with the technical difficulties page

  Scenario: An IOSS registered user cannot remove an individual previous scheme if they were retrieved from the ETMP registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    When the user is on the rejoin-registration page
    And the user manually navigates to the remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview%2Crejoin-registration page
    Then the user is on the cannot-delete-previous-registrations page
#
  Scenario: An IOSS registered user cannot remove previous schemes for a country if it was retrieved from the ETMP registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    When the user is on the rejoin-registration page
    And the user manually navigates to the remove-deregistration/1?waypoints=rejoin-registration page
    Then the user is on the cannot-delete-previous-registrations page

  Scenario: An IOSS registered user can add details for sections that were previously answered no
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page have-uk-trading-name from rejoin-registration
    And the user answers yes on the have-uk-trading-name page
    And the user adds A new trading name in rejoin journey on the first uk-trading-name page
    And the user answers no on the add-uk-trading-name page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Republic of Cyprus on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers no on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId              |
      | IM1962223333 | previousSchemeNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Romania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds RO1234567890 on the first eu-vat-number page
    And the user adds Romanian Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers no on the add-tax-details page
    Then the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user can add details for sections that were previously answered no and remove them straight away again
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page have-uk-trading-name from rejoin-registration
    And the user answers yes on the have-uk-trading-name page
    And the user adds A new trading name in rejoin journey on the first uk-trading-name page
    Then the user clicks remove via rejoin route for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    And the user answers no on the have-uk-trading-name page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin add link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Republic of Cyprus on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers no on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId              |
      | IM1962223333 | previousSchemeNumber |
    Then the user is on the previous-scheme-answers/1 page
    Then the user clicks remove via rejoin route for first previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/1 page
    And the user answers no on the previous-oss page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Romania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds RO1234567890 on the first eu-vat-number page
    And the user adds Romanian Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user clicks remove via rejoin route for first tax-details
    And the user answers yes on the remove-tax-details/1 page
    Then the user answers no on the tax-in-eu page
    Then the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user rejoins non-mandatory registration answers
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page add-uk-trading-name from rejoin-registration
    Then the user clicks remove via rejoin route for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user selects the list within rejoin change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends answer to an amended trading name
    Then the user answers yes on the add-uk-trading-name page
    And the user adds new 2nd name on the second uk-trading-name page
    Then the user answers no on the add-uk-trading-name page
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page previous-schemes-overview from rejoin-registration
    Then the user selects the list within rejoin change link for second previous-scheme-answers from change-previous-schemes-overview
    Then the user answers yes on the previous-scheme-answers/2 page
    And the user picks oss on the previous-scheme/2/2 page
    And the user adds CY12345678X on the second previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the third previous-country page
    And the user picks oss on the previous-scheme/3/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/3 page
    Then the user answers no on the previous-scheme-answers/3 page
    And the user answers no on the previous-schemes-overview page
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page add-tax-details from rejoin-registration
    Then the user selects the list within rejoin change link for first check-tax-details from change-add-tax-details
    Then the user selects the additional tax details list within rejoin change link for first registration-type from check-tax-details-1
    And the user picks tax id number on the registration-type/1 page
    And the user adds TAXID12345A on the first eu-tax-number page
    Then the user selects the additional tax details list within rejoin change link for first eu-fixed-establishment-address from check-tax-details-1
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data   | fieldId |
      | Suburb | line2   |
    Then the user continues through the check-tax-details/1 page
    And the user answers yes on the add-tax-details page
    And the user selects Estonia on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks vat number on the registration-type/2 page
    And the user adds EE123456789 on the second eu-vat-number page
    And the user adds Estonian Wholesalers Ltd on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data                | fieldId    |
      | 111 Estonian Street | line1      |
      | Tallinn             | townOrCity |
      | TAL 1234A           | postCode   |
    And the user continues through the check-tax-details/2 page
    And the user answers yes on the add-tax-details page
    And the user selects Portugal on the third eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/3 page
    And the user picks tax id number on the registration-type/3 page
    And the user adds PT12345A on the third eu-tax-number page
    And the user adds Portuguese Trading on the third eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/3 page
      | data             | fieldId    |
      | 111 Porto Street | line1      |
      | Porto            | townOrCity |
    And the user continues through the check-tax-details/3 page
    Then the user selects the list within rejoin change link for third check-tax-details from change-add-tax-details
    Then the user selects the additional tax details list within rejoin change link for third registration-type from check-tax-details-3
    And the user picks vat number on the registration-type/3 page
    And the user adds PT123456789 on the third eu-vat-number page
    And the user continues through the check-tax-details/3 page
    And the user answers no on the add-tax-details page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user removes some registration answers and rejoins mandatory answers
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page add-tax-details from rejoin-registration
    Then the user clicks remove via rejoin route for first tax-details
    And the user answers yes on the remove-tax-details/1 page
    Then the user continues through the tax-in-eu page
    And the user selects Estonia on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds EE123456789 on the first eu-vat-number page
    And the user adds Estonian Wholesalers Ltd on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data                | fieldId    |
      | 111 Estonian Street | line1      |
      | Tallinn             | townOrCity |
    And the user continues through the check-tax-details/1 page
    And the user answers no on the add-tax-details page
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page add-website-address from rejoin-registration
    Then the user clicks remove via rejoin route for second website-address
    And the user answers yes on the remove-website-address/2 page
    And the user selects the list within rejoin change link for first website-address from change-add-website-address
    And the user amends data to www.rejoined-website-name.com on the website-address/1 page
    Then the user answers yes on the add-website-address page
    And the user adds https://www.2nd-website.eu on the second website-address page
    Then the user answers no on the add-website-address page
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page business-contact-details from rejoin-registration
    And the user completes details on the business-contact-details page
      | data           | fieldId         |
      | Another Trader | fullName        |
      | +17771117771   | telephoneNumber |
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page bank-details from rejoin-registration
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user rejoins their email address
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page business-contact-details from rejoin-registration
    And the user completes details on the business-contact-details page
      | data                 | fieldId      |
      | rejoin-test@email.com | emailAddress |
    And the user completes the rejoin registration email verification process
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A user who gets not found from ETMP for an IOSS registration receives the technical difficulties page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999999 accesses the rejoin registration journey
    Then the user is presented with the technical difficulties page

  Scenario: An IOSS registered user receives an ETMP failure on submission of an rejoinment
    Given the user accesses the authority wizard
    And a user with VRN 600000022 and IOSS Number IM9029999997 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the error-submitting-rejoin page

  Scenario: An IOSS registered user can add quarantined/already active in another EU country registrations in rejoin previous registrations
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin add link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Slovenia on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers yes on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId                    |
      | IM7051122334 | previousSchemeNumber       |
      | IN7051122334 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    Then the user selects Latvia on the second previous-country page
    And the user picks ioss on the previous-scheme/2/1 page
    And the user answers yes on the previous-ioss-scheme/2/1 page
    And the user completes details on the previous-ioss-number/2/1 page
      | data         | fieldId                    |
      | IM4281122334 | previousSchemeNumber       |
      | IN4281122334 | previousIntermediaryNumber |
    Then the user answers yes on the previous-scheme-answers/2 page
    And the user picks oss on the previous-scheme/2/2 page
    And the user adds LV11111222222 on the second previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user can add quarantined/IOSS already active in another EU country registrations in rejoin EU tax details - VAT Number
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds LT999888777 on the first eu-vat-number page
    And the user adds Lithuanian Goods on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data            | fieldId    |
      | 168 Town Square | line1      |
      | Vilnius         | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Portugal on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks vat number on the registration-type/2 page
    And the user adds PT111222333 on the second eu-vat-number page
    And the user adds Portugal Goods on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data           | fieldId    |
      | 1b Town Square | line1      |
      | Lisbon         | townOrCity |
    And the user continues through the check-tax-details/2 page
    Then the user answers no on the add-tax-details page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user can add quarantined/already active in another EU country registrations in rejoin EU tax details - Tax ID
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds ABC123123 on the first eu-tax-number page
    And the user adds Lithuanian Goods on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data            | fieldId    |
      | 168 Town Square | line1      |
      | Vilnius         | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Portugal on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks tax id number on the registration-type/2 page
    And the user adds 123LIS123 on the second eu-tax-number page
    And the user adds Portugal Goods on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data           | fieldId    |
      | 1b Town Square | line1      |
      | Lisbon         | townOrCity |
    And the user continues through the check-tax-details/2 page
    Then the user answers no on the add-tax-details page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A user without an IOSS enrolment cannot access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and no IOSS enrolment accesses the rejoin registration journey
    Then the user is on the cannot-use-not-registered page

  Scenario: An IOSS registered user with existing EU registration is able to access the rejoin journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number 333333333 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An IOSS registered user with existing EU quarantine is able to access the rejoin journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number 333333334 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

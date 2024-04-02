@Registration @All

Feature: Rejoin registration journeys

  @Accessibility
  Scenario: A trader with a future exclusion effective date is not able to access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999997 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A quarantined trader is not able to access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999993 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A currently registered IOSS trader who is not excluded cannot access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A currently registered IOSS trader who is not excluded cannot access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    When the user manually navigates to the rejoin-registration page
    Then the user is on the cannot-rejoin page

  Scenario: A user who gets not found during a rejoin from ETMP for an IOSS registration receives the technical difficulties page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999999 accesses the rejoin registration journey
    Then the user is presented with the technical difficulties page
  @Accessibility
  Scenario: An IOSS registered user receives an ETMP failure on submission of a rejoin
    Given the user accesses the authority wizard
    And a user with VRN 600000022 and IOSS Number IM9029999997 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the error-submitting-rejoin page

  Scenario: A trader with an expired quarantine period can submit a rejoin registration without amending any details
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9002999993 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: An excluded trader with an effective date 6 years ago can access and submit a rejoin registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999994 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A trader with minimal details in their original registration can amend and rejoin
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
    And the user selects Portugal on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers no on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId              |
      | IM6207777777 | previousSchemeNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Estonia on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU123456789 on the first previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects France on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds FRXX123456789 on the first eu-vat-number page
    And the user adds French Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers no on the add-tax-details page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A trader with optional answers in original registration set to yes can change to no and rejoin
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
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A trader can add details for sections during rejoin that were previously answered no and then remove them
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
    And the user answers yes on the how-do-you-operate/1 page
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
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page
  @ZAP
  Scenario: A trader can amend non-mandatory answers during rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page add-uk-trading-name from rejoin-registration
    Then the user clicks remove via rejoin route for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user selects the list within rejoin change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends answer to an amended trading name for rejoin
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
    And the user answers yes on the how-do-you-operate/2 page
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
    And the user answers yes on the how-do-you-operate/3 page
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
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A trader amends their email address during rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page business-contact-details from rejoin-registration
    And the user completes details on the business-contact-details page
      | data                 | fieldId      |
      | rejoin-test@email.com | emailAddress |
    And the user completes the rejoin registration email verification process
  When the user continues through the rejoin-registration page
  Then the user is on the successful-rejoin page

  Scenario: A trader removes some registration answers and amends mandatory answers during rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    Then the user selects the rejoin change link for page add-tax-details from rejoin-registration
    Then the user clicks remove via rejoin route for first tax-details
    And the user answers yes on the remove-tax-details/1 page
    Then the user continues through the tax-in-eu page
    And the user selects Estonia on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
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
    And the user amends data to www.amended-website-name.com on the website-address/1 page
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
  When the user continues through the rejoin-registration page
  Then the user is on the successful-rejoin page

  @Accessibility
  Scenario: A trader with a future exclusion effective date is not able to access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999997 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A quarantined trader is not able to access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999993 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A currently registered IOSS trader who is not excluded cannot access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A trader cannot remove existing previous registrations retrieved from ETMP when they rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    And the user manually navigates to the remove-all-previous-registrations?waypoints=rejoin-registration page
    Then the user is presented with the technical difficulties page

  Scenario: A trader cannot remove cannot remove an individual previous scheme if they were retrieved from the ETMP registration during rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    And the user manually navigates to the remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview%2Crejoin-registration page
    Then the user is on the cannot-delete-previous-registrations page

  Scenario: A trader cannot remove cannot remove cannot remove previous schemes for a country if it was retrieved from the ETMP registration during rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    And the user manually navigates to the remove-deregistration/1?waypoints=rejoin-registration page
    Then the user is on the cannot-delete-previous-registrations page


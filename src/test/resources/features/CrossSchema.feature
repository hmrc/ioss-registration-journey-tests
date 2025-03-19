@CrossSchema

Feature: Cross Schema journeys

  Scenario: Registration for trader with an OSS registration - amends data
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with OSS and VAT enrolment 300000002
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user chooses Yes on the confirm-vat-details page
    Then the user is on the add-uk-trading-name page
    And the correct number of existing trading names are displayed for a trader with an OSS registration
    And the trading name warnings are displayed for a trader with an OSS registration
    Then the user clicks remove via list for second uk-trading-name
    And the user answers yes on the remove-uk-trading-name/2 page
    Then the user answers yes on the add-uk-trading-name page
    And the user adds New 2nd trading name on the second uk-trading-name page
    Then the user answers no on the add-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.onlywebsite.com on the first website-address page
    And the user answers no on the add-website-address page
    And the contact details warnings are displayed for a trader with an OSS registration
    And the user completes details on the business-contact-details page
      | data                        | fieldId         |
      | +17771117771                | telephoneNumber |
      | cross-schema-test@email.com | emailAddress    |
    And the user completes the registration email verification process
    And the bank details warnings are displayed for a trader with an OSS registration
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | GB29NWBK60161331926819 | iban        |
    Then the user is on the check-your-answers page
    Then the user submits their registration
#    Check details

  Scenario: Amend registration for trader with an OSS registration and multiple previous IOSS registrations - amends data
    Given the user accesses the authority wizard
    And a user registered on OSS with VRN 300000002 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    check required pages and make amends
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
#    Check amends and text on confirmation page

  Scenario: Amend registration for trader with 1 previous IOSS registration - amends data
    Given the user accesses the authority wizard
    And a user with VRN 100000002 and IOSS Number IM9019999997 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    check required pages and make amends
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
#    Check amends and text on confirmation page

  Scenario: Amend registration for trader with multiple previous IOSS registrations - amends data
    Given the user accesses the authority wizard
    And a user with VRN 100000002 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    check required pages and make amends
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
#    Check amends and text on confirmation page

  Scenario: Rejoin registration for trader with an OSS registration and multiple IOSS registrations - amends data
    Given the user accesses the authority wizard
    And a user registered on OSS with VRN 300000002 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
#    check required pages and make amends
    When the user submits their rejoin registration
    Then the user is on the successful-rejoin page
#    check text on confirmation page

  Scenario: Rejoin registration for trader with 1 previous IOSS registration - amends data
    Given the user accesses the authority wizard
    And a user with VRN 100000002 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    #    check required pages and make amends
    When the user submits their rejoin registration
    Then the user is on the successful-rejoin page
#    check text on confirmation page

  Scenario: Rejoin registration for trader with multiple previous IOSS registrations - amends data
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007230000 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    #    check required pages and make amends
    When the user submits their rejoin registration
    Then the user is on the successful-rejoin page
#    check text on confirmation page

  Scenario: Registration for trader with other registrations who did not make changes to data
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with OSS and VAT enrolment 300000002
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user chooses Yes on the confirm-vat-details page
    Then the user is on the add-uk-trading-name page
    And the correct number of existing trading names are displayed for a trader with an OSS registration
    And the trading name warnings are displayed for a trader with an OSS registration
    Then the user answers no on the add-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.onlywebsite.com on the first website-address page
    And the user answers no on the add-website-address page
    And the user is on the business-contact-details page
    And the contact details warnings are displayed for a trader with an OSS registration
    And the user continues through the business-contact-details page
    And the user completes the registration email verification process
    And the user is on the bank-account-details page
    And the bank details warnings are displayed for a trader with an OSS registration
    And the user continues through the bank-account-details page
    Then the user is on the check-your-answers page
    Then the user submits their registration
#    check acknowledgement page

  Scenario: Amend registration for trader with other registrations who did not make changes to data
    Given the user accesses the authority wizard
    And a user registered on OSS with VRN 300000002 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    check required pages and don't make amends
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
#    check acknowledgement page

  Scenario: Rejoin registration for trader with other registrations who did not make changes to data
    Given the user accesses the authority wizard
    And a user registered on OSS with VRN 300000002 and IOSS Number IM9019999997 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
#    check required pages and don't make amends
    When the user submits their rejoin registration
    Then the user is on the successful-rejoin page
#    check acknowledgement page

  Scenario: Registration for trader with no other registrations does not pre-populate details and does not show warnings
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers yes on the have-uk-trading-name page
    And the user adds No registration trader on the first uk-trading-name page
    Then the user is on the add-uk-trading-name page
    And the new trading name is the only trading name where there are no previous registrations
    And the trading name warnings are not displayed for a trader with an OSS registration
    And the user answers no on the add-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.onlywebsite.com on the first website-address page
    And the user answers no on the add-website-address page
    And the contact details warnings are not displayed for a trader with an OSS registration
    And the contact details are blank
    And the user completes details on the business-contact-details page
      | data                  | fieldId         |
      | Another Trader        | fullName        |
      | +17771117771          | telephoneNumber |
      | minimaltest@email.com | emailAddress    |
    And the user completes the registration email verification process
    And the bank details warnings are not displayed for a trader with an OSS registration
    And the bank details are blank
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    Then the user is on the check-your-answers page
    Then the user submits their registration
#    Check acknowledgement

  Scenario: Amend registration for trader with no other registrations does not show warnings
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
#    Needs checks on pages and acknowledgement

  Scenario: Rejoin registration for trader with no other registrations does not show warnings
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user submits their rejoin registration
    Then the user is on the successful-rejoin page
    #    Needs checks on pages and acknowledgement


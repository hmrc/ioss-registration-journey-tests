@CrossSchema

Feature: Cross Schema journeys

  Scenario: Registration for trader with an OSS registration - amends data
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with OSS and VAT enrolment 100000002
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user chooses Yes on the confirm-vat-details page
#    Needs amendments
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.onlywebsite.com on the first website-address page
    And the user answers no on the add-website-address page
#    Needs amendments
    And the user completes details on the business-contact-details page
      | data                  | fieldId         |
      | Another Trader        | fullName        |
      | +17771117771          | telephoneNumber |
      | minimaltest@email.com | emailAddress    |
    And the user completes the registration email verification process
#    Needs amendments
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    Then the user is on the check-your-answers page
    Then the user submits their registration
#    Check details

  Scenario: Amend registration for trader with an OSS registration and multiple previous IOSS registrations - amends data
    Given the user accesses the authority wizard
    And a user registered on OSS with VRN 100000002 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    check required pages and make amends
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
#    Check amends and text on confirmation page

  Scenario: Amend registration for trader with 1 previous IOSS registration - amends data
    Given the user accesses the authority wizard
    And a user with VRN 100000002 and IOSS Number IM9019999998 accesses the amend registration journey
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
    And a user registered on OSS with VRN 100000002 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
#    check required pages and make amends
    When the user submits their rejoin registration
    Then the user is on the successful-rejoin page
#    check text on confirmation page

  Scenario: Rejoin registration for trader with 1 previous IOSS registration - amends data
    Given the user accesses the authority wizard
    And a user with VRN 100000002 and IOSS Number IM9019999998 accesses the rejoin registration journey
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
#    check acknowledgement page

  Scenario: Amend registration for trader with other registrations who did not make changes to data
#    check acknowledgement page

  Scenario: Rejoin registration for trader with other registrations who did not make changes to data
#    check acknowledgement page

  Scenario: Registration for trader with no other registrations does not pre-populate details and does not show warnings

  Scenario: Amend registration for trader with no other registrations does not show warnings

  Scenario: Rejoin registration for trader with no other registrations does not show warnings


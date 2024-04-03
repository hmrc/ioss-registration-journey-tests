@All

Feature: Rejoin - Core Validation Scenarios

  Scenario: Trader with existing EU registration is not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 333333333 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the already-registered-other-country?countryCode=EE page

  Scenario: Trader with existing EU quarantine is not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 333333334 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the other-country-excluded-and-quarantined?countryCode=EE&exclusionDate=2023-01-01 page

  Scenario: Trader with active OSS scheme - previous registration - can rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Slovenia on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds SI11223344 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page

  Scenario: Trader with active OSS scheme retrieved from ETMP registration - previous registration - can rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999992 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: Trader with active OSS non-union scheme retrieved from ETMP registration - previous registration - can rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999996 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: Trader with active IOSS scheme - previous registration - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Slovenia on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers yes on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId                    |
      | IM7051122334 | previousSchemeNumber       |
      | IN7051122334 | previousIntermediaryNumber |
    Then the user is on the scheme-still-active?countryCode=SI page

  Scenario: Trader with active IOSS scheme retrieved from ETMP - previous registration - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999994 accesses the rejoin registration journey
    Then the user is on the scheme-still-active?countryCode=IE page

  Scenario: Trader with quarantined OSS scheme - previous registration - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Latvia on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds LV11111222222 on the first previous-oss-scheme-number/1 page
    Then the user is on the scheme-quarantined page

  Scenario: Trader with quarantined OSS scheme retrieved from ETMP registration - previous registration - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999991 accesses the rejoin registration journey
    Then the user is on the scheme-quarantined page

  Scenario: Trader with quarantined OSS non-union scheme retrieved from ETMP registration - previous registration - can rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999996 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: Trader with quarantined IOSS scheme - previous registration - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page previous-oss from rejoin-registration
    Then the user answers yes on the previous-oss page
    And the user selects Latvia on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers yes on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId                    |
      | IM4281122334 | previousSchemeNumber       |
      | IN4281122334 | previousIntermediaryNumber |
    Then the user is on the scheme-quarantined page

  Scenario: Trader with quarantined IOSS scheme retrieved from ETMP - previous registration - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9005999993 accesses the rejoin registration journey
    Then the user is on the scheme-quarantined page

  Scenario: Trader with active scheme - EU details - VRN - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Portugal on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds PT111222333 on the first eu-vat-number page
    Then the user is on the fixed-establishment-vrn-already-registered?waypoints=rejoin-registration&countryCode=PT page

  Scenario: Trader with active scheme retrieved from ETMP registration - EU details - VRN - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9004999994 accesses the rejoin registration journey
    Then the user is on the fixed-establishment-vrn-already-registered?waypoints=rejoin-registration&countryCode=IE page

  Scenario: Trader with active scheme - EU details - Tax ID - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Portugal on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds 123LIS123 on the first eu-tax-number page
    Then the user is on the fixed-establishment-vrn-already-registered?waypoints=rejoin-registration&countryCode=PT page

  Scenario: Trader with active scheme retrieved from ETMP registration - EU details - Tax ID - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9004999992 accesses the rejoin registration journey
    Then the user is on the fixed-establishment-vrn-already-registered?waypoints=rejoin-registration&countryCode=IE page

  Scenario: Trader with quarantined scheme - EU details - VRN - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds LT999888777 on the first eu-vat-number page
    Then the user is on the excluded-vrn page

  Scenario: Trader with quarantined scheme - EU details - VRN - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9004999993 accesses the rejoin registration journey
    Then the user is on the excluded-vrn page

  Scenario: Trader with quarantined scheme - EU details - Tax ID - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user selects the rejoin change link for page tax-in-eu from rejoin-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds ABC123123 on the first eu-tax-number page
    Then the user is on the excluded-vrn page

  Scenario: Trader with quarantined scheme retrieved from ETMP registration - EU details - Tax ID - not able to rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9004999991 accesses the rejoin registration journey
    Then the user is on the excluded-vrn page



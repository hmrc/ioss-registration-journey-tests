@Registration

Feature: Core Validation Scenarios
  @Accessibility
  Scenario: Trader with existing EU registration is not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user signs in as an Organisation Admin with VAT enrolment 333333333
    Then the user is on the already-registered-other-country?countryCode=EE page
  @Accessibility
  Scenario: Trader with existing EU quarantine is not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user signs in as an Organisation Admin with VAT enrolment 333333334
    Then the user is on the other-country-excluded-and-quarantined?countryCode=EE&exclusionDate=2023-01-01 page

  Scenario: Trader with active OSS scheme - previous registration - can register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Slovenia on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds SI11223344 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
  @Accessibility
  Scenario: Trader with active IOSS scheme - previous registration - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Slovenia on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers yes on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId                    |
      | IM7051122334 | previousSchemeNumber       |
      | IN7051122334 | previousIntermediaryNumber |
    Then the user is on the scheme-still-active?countryCode=SI page
  @Accessibility
  Scenario: Trader with quarantined OSS scheme - previous registration - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Latvia on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds LV11111222222 on the first previous-oss-scheme-number/1 page
    Then the user is on the scheme-quarantined page

  Scenario: Trader with quarantined IOSS scheme - previous registration - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Latvia on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers yes on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId                    |
      | IM4281122334 | previousSchemeNumber       |
      | IN4281122334 | previousIntermediaryNumber |
    Then the user is on the scheme-quarantined page
  @Accessibility
  Scenario: Trader with active scheme - EU details - VRN - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects Portugal on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds PT111222333 on the first eu-vat-number page
    Then the user is on the fixed-establishment-vrn-already-registered/1 page

  Scenario: Trader with active scheme - EU details - Tax ID - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects Portugal on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds 123LIS123 on the first eu-tax-number page
    Then the user is on the fixed-establishment-vrn-already-registered/1 page
  @Accessibility
  Scenario: Trader with quarantined scheme - EU details - VRN - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds LT999888777 on the first eu-vat-number page
    Then the user is on the excluded-vrn page

  Scenario: Trader with quarantined scheme - EU details - Tax ID - not able to register
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user answers yes on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds ABC123123 on the first eu-tax-number page
    Then the user is on the excluded-vrn page




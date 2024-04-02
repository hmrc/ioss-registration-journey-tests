@Registration @Accessibility @All

Feature: Northern Ireland and Norway Kickout journeys

  Scenario: Kickout when the user answered yes to ni-based but does not have Single Market Indicator
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 500000001
    And the user is on the cannot-register-no-ni-protocol page

  Scenario: Kickout when the user answered yes to norway-based but does not have an address in Norway
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers no on the ni-based page
    And the user answers yes on the norway-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 500000001
    And the user is on the cannot-register-not-norwegian-based-business page



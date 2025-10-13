@Registration

Feature: NETP scenarios

  Background:
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page

  Scenario: Trader with Single Market Indicator set to true and NETP set to false can progress through registration
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 444666661
    And the user is on the confirm-vat-details page

  Scenario: Trader with Single Market Indicator set to true and NETP set to true cannot progress through registration
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 444666662
    And the user is on the cannot-register-non-established-taxable-person page

  Scenario: Trader with Single Market Indicator set to false and NETP set to false cannot progress through registration but hits NI Protocol rejection before NETP
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 444666663
    And the user is on the cannot-register-no-ni-protocol page

  Scenario: Trader with Single Market Indicator set to false and NETP set to false and has a Norway address can progress through registration
    And the user answers no on the ni-based page
    And the user answers yes on the norway-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 444666664
    And the user is on the confirm-vat-details page

  Scenario: Trader with Single Market Indicator set to false and NETP set to true and has a Norway address can progress through registration
    And the user answers no on the ni-based page
    And the user answers yes on the norway-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 444666665
    And the user is on the confirm-vat-details page


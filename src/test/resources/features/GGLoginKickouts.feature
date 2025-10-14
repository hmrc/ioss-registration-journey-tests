@Registration

Feature: Government Gateway Login Kickout journeys

  Scenario: GG kickout when the user is not registered for VAT
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin without VAT enrolment 100000001
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user is on the credential-unsupported page

  Scenario: GG kickout when the user is an agent
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Agent Admin with VAT enrolment 100000001
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user is on the cannot-use-agent page





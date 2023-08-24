@Registration @Accessibility

Feature: Get VAT Details kickout and alternative journeys

  Background:
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page

  Scenario: VAT details kickout when business is incorrect
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user is on the confirm-vat-details page
    And the user chooses No, different business on the confirm-vat-details page
    Then the user is on the register-different-business page

  Scenario: VAT details kickout when VAT details are incorrect
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user is on the confirm-vat-details page
    And the user chooses Yes, details incorrect on the confirm-vat-details page
    Then the user is on the update-vat-details page

  Scenario: VAT details kickout when VAT details not found
    Then the user signs in as an Organisation Admin with VAT enrolment 900000001
    And the user is on the registration-service-error page

  Scenario: VAT details kickout when there is an Internal Server Error
    Then the user signs in as an Organisation Admin with VAT enrolment 800000001
    And the user is on the registration-service-error page

  Scenario: VAT details kickout when there are missing VAT details
    Then the user signs in as an Organisation Admin with VAT enrolment 700000001
    And the user is on the registration-service-error page

  Scenario: VAT details kickout when the VAT number for this account is no longer valid
    Then the user signs in as an Organisation Admin with VAT enrolment 600000001
    And the user is on the expired-vrn-date page

  Scenario: IOSS Registration journey for NI Trader who is not part of a VAT group
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 777777779
    And the user is on the confirm-vat-details page

  Scenario: IOSS Registration journey for NI Trader who is registered as an individual
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 777777778
    And the user is on the confirm-vat-details page







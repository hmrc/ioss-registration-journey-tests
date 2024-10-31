@Registration

Feature: OSS Exclusion journeys

  Scenario: Kickout in the registration journey when the user is quarantined on the One Stop Shop service
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with OSS and VAT enrolment 100000025
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user is on the cannot-register-quarantined-trader page

  Scenario: User can access the registration journey when a quarantine on the One Stop Shop service has expired
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with OSS and VAT enrolment 600000014
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user is on the confirm-vat-details page

  Scenario: Kickout when the user returns to a saved registration but is now quarantined on the One Stop Shop service
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000026
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers no on the tax-in-eu page
    And the user clicks on the save and come back later button
    And the user clicks on the sign out and come back later link
    When the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with OSS and VAT enrolment 100000026
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user is on the cannot-register-quarantined-trader page

  Scenario: Kickout when the user attempts to rejoin the service but is quarantined on the One Stop Shop service
    Given the user accesses the authority wizard
    When a user quarantined on OSS with VRN 100000025 and IOSS Number IM9002999993 accesses the rejoin registration journey
    Then the user is on the cannot-register-quarantined-trader page

  Scenario: User can access the amend registration journey when quarantined on the One Stop Shop service
    Given the user accesses the authority wizard
    When a user quarantined on OSS with VRN 100000025 and IOSS Number IM9001234567 accesses the amend registration journey
    Then the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
    And the confirmation of no answers changed is displayed


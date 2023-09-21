@Registration @Accessibility

Feature: Change and remove answers for registrations in progress

  Scenario: Remove answers via list pages during registration for NI Trader
#    Given the user accesses the IOSS Registration service
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 100000001
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
#    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers yes on the have-uk-trading-name page
    And the user adds Alternative trading on the first uk-trading-name page
    And the user answers yes on the add-uk-trading-name page
    And the user adds 789 & trading on the second uk-trading-name page
    Then the user clicks remove via list for second uk-trading-name
    And the user answers yes on the remove-uk-trading-name/2 page
    Then the user clicks remove via list for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user answers no on the have-uk-trading-name page

  Scenario: Change answers via list pages during registration for NI Trader
#    Given the user accesses the IOSS Registration service
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 100000001
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
#    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers yes on the have-uk-trading-name page
    And the user adds A trading name on the first uk-trading-name page
    And the user answers yes on the add-uk-trading-name page
    And the user adds 2nd name! on the second uk-trading-name page
    Then the user selects the list change link for second uk-trading-name from change-add-uk-trading-name
    And the user amends data to Different trading name on the uk-trading-name/2?waypoints=change-add-uk-trading-name page
    Then the user clicks remove via list for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user answers no on the add-uk-trading-name page

  Scenario: Change and remove answers via list pages during registration for Norwegian Trader
#    Given the user accesses the IOSS Registration service
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 444555555
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers no on the ni-based page
    And the user answers yes on the norway-based page
    And the user continues through the register-to-use-service page
#    Then the user signs in as an Organisation Admin with VAT enrolment 444555555
    And the user chooses Yes on the confirm-vat-details page
    And the user answers yes on the have-uk-trading-name page
    And the user adds A Norwegian trading name on the first uk-trading-name page
    And the user answers yes on the add-uk-trading-name page
    And the user adds 2nd Norwegian name! on the second uk-trading-name page
    Then the user selects the list change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends data to Different Norwegian trading name on the uk-trading-name/1?waypoints=change-add-uk-trading-name page
    Then the user clicks remove via list for second uk-trading-name
    And the user answers yes on the remove-uk-trading-name/2 page
    Then the user answers yes on the add-uk-trading-name page
    And the user adds New 2nd Norwegian trading name on the second uk-trading-name page
    Then the user answers no on the add-uk-trading-name page



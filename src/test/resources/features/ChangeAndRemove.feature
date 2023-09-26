@Registration @Accessibility

Feature: Change and remove answers for registrations in progress

  Scenario: Remove answers via list pages during registration for NI Trader
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
    And the user adds Alternative trading on the first uk-trading-name page
    And the user answers yes on the add-uk-trading-name page
    And the user adds 789 & trading on the second uk-trading-name page
    Then the user clicks remove via list for second uk-trading-name
    And the user answers yes on the remove-uk-trading-name/2 page
    Then the user clicks remove via list for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user answers no on the have-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks ioss on the previous-scheme/1/2 page
    And the user answers yes on the previous-ioss-scheme/1/2 page
    And the user completes details on the previous-ioss-number/1/2 page
      | data         | fieldId                    |
      | IM3487777777 | previousSchemeNumber       |
      | IN3487777777 | previousIntermediaryNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Austria on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU123456788 on the first previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    Then the user is on the previous-schemes-overview page
    Then the user clicks remove via list for second deregistration
    And the user answers yes on the remove-deregistration/2 page
    Then the user selects the list change link for first previous-scheme-answers from change-previous-schemes-overview
    Then the user clicks remove via overviewLoop for second previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/2?waypoints=change-previous-schemes-overview page
    Then the user clicks remove via overviewExtraLoop for first previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/1?waypoints=previous-schemes-overview%2Cchange-previous-schemes-overview page
    Then the user answers no on the previous-oss?waypoints=previous-schemes-overview%2Cchange-previous-schemes-overview page

  Scenario: Change answers via list pages during registration for NI Trader
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
    And the user adds A trading name on the first uk-trading-name page
    And the user answers yes on the add-uk-trading-name page
    And the user adds 2nd name! on the second uk-trading-name page
    Then the user selects the list change link for second uk-trading-name from change-add-uk-trading-name
    And the user amends data to Different trading name on the uk-trading-name/2?waypoints=change-add-uk-trading-name page
    Then the user clicks remove via list for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user answers no on the add-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Belgium on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds BE0123456777 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks ioss on the previous-scheme/1/2 page
    And the user answers yes on the previous-ioss-scheme/1/2 page
    And the user completes details on the previous-ioss-number/1/2 page
      | data         | fieldId                    |
      | IM0563332221 | previousSchemeNumber       |
      | IN0563332221 | previousIntermediaryNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Estonia on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EE111111111 on the first previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    Then the user selects the list change link for first previous-scheme-answers from change-previous-schemes-overview
    And the user answers yes on the previous-scheme-answers/1?waypoints=change-previous-schemes-overview page
    And the user picks oss on the previous-scheme/1/3?waypoints=change-previous-schemes-overview page
    And the user adds EU111555999 on the new previous-oss-scheme-number/1/3?waypoints=change-previous-schemes-overview page
    Then the user continues through the previous-scheme-answers/1?waypoints=previous-schemes-overview%2Cchange-previous-schemes-overview page
    Then the user selects the list change link for second previous-scheme-answers from change-previous-schemes-overview
    And the user answers yes on the previous-scheme-answers/2?waypoints=change-previous-schemes-overview page
    And the user picks ioss on the previous-scheme/2/2?waypoints=change-previous-schemes-overview page
    And the user answers no on the previous-ioss-scheme/2/2?waypoints=change-previous-schemes-overview page
    And the user completes details on the previous-ioss-number/2/2?waypoints=change-previous-schemes-overview page
      | data         | fieldId                    |
      | IM2339876543 | previousSchemeNumber       |
    Then the user is on the previous-scheme-answers/2?waypoints=previous-schemes-overview%2Cchange-previous-schemes-overview page
    And the user answers no on the previous-scheme-answers/2?waypoints=previous-schemes-overview%2Cchange-previous-schemes-overview page
    And the user answers no on the previous-schemes-overview page

  Scenario: Change and remove answers via list pages during registration for Norwegian Trader
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers no on the ni-based page
    And the user answers yes on the norway-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 444555555
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
    Then the user answers yes on the previous-oss page
    And the user selects Croatia on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HR01234567888 on the first previous-oss-scheme-number/1 page
    Then the user answers no on the previous-scheme-answers/1 page
    Then the user selects the list change link for first previous-scheme-answers from change-previous-schemes-overview
    Then the user clicks remove via list for first previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/1?waypoints=change-previous-schemes-overview page
    Then the user answers yes on the previous-oss?waypoints=change-previous-schemes-overview page
    And the user selects France on the new-first-previous-scheme previous-country page
    And the user picks ioss on the previous-scheme/1/1?waypoints=change-previous-schemes-overview page
    And the user answers yes on the previous-ioss-scheme/1/1?waypoints=change-previous-schemes-overview page
    And the user completes details on the previous-ioss-number/1/1?waypoints=change-previous-schemes-overview page
      | data         | fieldId                    |
      | IM2506544561 | previousSchemeNumber       |
      | IN2507412365 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/1?waypoints=previous-schemes-overview%2Cchange-previous-schemes-overview page
    And the user answers no on the previous-schemes-overview page



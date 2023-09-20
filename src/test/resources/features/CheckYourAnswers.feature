@Registration @Accessibility

Feature: Change answers for registrations via Check Your Answers

  Scenario: Change answers via Check Your Answers for NI Trader registration
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
    And the user answers yes on the add-uk-trading-name page
    And the user adds Number 3 on the third uk-trading-name page
    And the user answers no on the add-uk-trading-name page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    #   Awaiting further implementation
    And the user manually navigates to the check-your-answers page
    Then the user selects the CYA change link for page add-uk-trading-name from check-your-answers
    Then the user selects the list within CYA change link for second uk-trading-name from change-add-uk-trading-name
    And the user amends data to CYA trading name on the uk-trading-name/2?waypoints=change-add-uk-trading-name%2Ccheck-your-answers page
    Then the user clicks remove via CYA route for third uk-trading-name
    And the user answers yes on the remove-uk-trading-name/3?waypoints=check-your-answers page
    Then the user answers no on the add-uk-trading-name?waypoints=check-your-answers page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page business-contact-details from check-your-answers
    And the user amends details on the business-contact-details?waypoints=check-your-answers page
      | data          | fieldId         |
      | 5555555555555 | telephoneNumber |
    #    Awaiting further implementation to redirect
#    And the user is on the check-your-answers page

  Scenario: Change from yes to no via Check Your Answers for NI Trader registration
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
    And the user answers no on the add-uk-trading-name page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    #   Awaiting further implementation
    And the user manually navigates to the check-your-answers page
    Then the user selects the CYA change link for page have-uk-trading-name from check-your-answers
    And the user answers no on the have-uk-trading-name?waypoints=check-your-answers page
    Then the user is on the check-your-answers page

  Scenario: Change answers via Check Your Answers for Norwegian Trader registration
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
    And the user adds A trading name on the first uk-trading-name page
    And the user answers no on the add-uk-trading-name page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    #   Awaiting further implementation
    And the user manually navigates to the check-your-answers page
    Then the user selects the CYA change link for page add-uk-trading-name from check-your-answers
    Then the user selects the list within CYA change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends data to CYA Norwegian trading name on the uk-trading-name/1?waypoints=change-add-uk-trading-name%2Ccheck-your-answers page
    Then the user answers no on the add-uk-trading-name?waypoints=check-your-answers page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page business-contact-details from check-your-answers
    And the user amends details on the business-contact-details?waypoints=check-your-answers page
      | data                 | fieldId  |
      | Norway Trader Update | fullName |
    #    Awaiting further implementation to redirect
#    And the user is on the check-your-answers page




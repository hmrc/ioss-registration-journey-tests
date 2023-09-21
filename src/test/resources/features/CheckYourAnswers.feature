@Registration @Accessibility

Feature: Change answers for registrations via Check Your Answers

  Scenario: Change answers via Check Your Answers for NI Trader registration
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
    And the user answers yes on the add-uk-trading-name page
    And the user adds Number 3 on the third uk-trading-name page
    And the user answers no on the add-uk-trading-name page
            #   Awaiting navigation fix
    And the user manually navigates to the previous-oss page
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Austria on the second previous-country page
    And the user picks ioss on the previous-scheme/2/1 page
    And the user answers yes on the previous-ioss-scheme/2/1 page
    And the user completes details on the previous-ioss-scheme/2/1 page
      | data         | fieldId                    |
      | IM0401234567 | previousSchemeNumber       |
      | IN0407654321 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    #    Awaiting email verification
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Trader Name            | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    Then the user is on the check-your-answers page
    And the user manually navigates to the check-your-answers page
    Then the user selects the CYA change link for page add-uk-trading-name from check-your-answers
    Then the user selects the list within CYA change link for second uk-trading-name from change-add-uk-trading-name
    And the user amends data to CYA trading name on the uk-trading-name/2?waypoints=change-add-uk-trading-name%2Ccheck-your-answers page
    Then the user clicks remove via CYA route for third uk-trading-name
    And the user answers yes on the remove-uk-trading-name/3?waypoints=check-your-answers page
    Then the user answers no on the add-uk-trading-name?waypoints=check-your-answers page
    And the user is on the check-your-answers page
#    Needs all the waypoints added
    Then the user selects the CYA change link for page previous-schemes-overview from check-your-answers
    Then the user clicks remove via CYA route for second remove-deregistration
    And the user answers yes on the remove-deregistration/2 page
    Then the user selects the CYA change link for first previous-scheme-answers from previous-schemes-overview
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks ioss on the previous-scheme/1/2 page
    And the user answers yes on the previous-ioss-scheme/1/2 page
    And the user completes details on the previous-ioss-scheme/1/2 page
      | data         | fieldId                    |
      | IM3487777777 | previousSchemeNumber       |
      | IN3487777777 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/1 page
    And the user answers no on the previous-schemes-overview page
    Then the user is on the check-your-answers page
    Then the user selects the CYA change link for page business-contact-details from check-your-answers
    And the user amends details on the business-contact-details?waypoints=check-your-answers page
      | data          | fieldId         |
      | 5555555555555 | telephoneNumber |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page bank-details from check-your-answers
    And the user amends details on the bank-details?waypoints=check-your-answers page
      | data                   | fieldId     |
      | Trader Name NI         | accountName |
      | ABCDEF2A               | bic         |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the check-your-answers page

  Scenario: Change from yes to no via Check Your Answers for NI Trader registration
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
    And the user answers no on the add-uk-trading-name page
        #   Awaiting navigation fix
    And the user manually navigates to the previous-oss page
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Austria on the second previous-country page
    And the user picks ioss on the previous-scheme/2/1 page
    And the user answers yes on the previous-ioss-scheme/2/1 page
    And the user completes details on the previous-ioss-scheme/2/1 page
      | data         | fieldId                    |
      | IM0401234567 | previousSchemeNumber       |
      | IN0407654321 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    #    Awaiting email verification
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Trader Name            | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page have-uk-trading-name from check-your-answers
    And the user answers no on the have-uk-trading-name?waypoints=check-your-answers page
    And the user answers yes on the remove-all-trading-names?waypoints=check-your-answers page
    Then the user is on the check-your-answers page
    Then the user selects the CYA change link for page previous-oss from check-your-answers
    And the user answers no on the previous-oss?waypoints=check-your-answers page
    Then the user answers yes on the check-remove-all-previous-registrations?waypoints=check-your-answers page
#    There is a bug preventing user from going back to CYA
    Then the user is on the check-your-answers page

  Scenario: Change answers via Check Your Answers for Norwegian Trader registration
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
    And the user adds A trading name on the first uk-trading-name page
    And the user answers no on the add-uk-trading-name page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
        #    Awaiting email verification
    And the user completes details on the bank-details page
      | data               | fieldId     |
      | Trader Name Norway | accountName |
      | NORWNOK1XXX        | bic         |
      | NO9386011117947    | iban        |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page add-uk-trading-name from check-your-answers
    Then the user selects the list within CYA change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends data to CYA Norwegian trading name on the uk-trading-name/1?waypoints=change-add-uk-trading-name%2Ccheck-your-answers page
    Then the user answers no on the add-uk-trading-name?waypoints=check-your-answers page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page business-contact-details from check-your-answers
    And the user amends details on the business-contact-details?waypoints=check-your-answers page
      | data                 | fieldId  |
      | Norway Trader Update | fullName |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page bank-details from check-your-answers
    And the user amends details on the bank-details?waypoints=check-your-answers page
      | data                  | fieldId     |
      | Different Name Norway | accountName |
    And the user is on the check-your-answers page




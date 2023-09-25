@Registration @Accessibility

Feature: Registration journeys

  @ZAP
  Scenario: Full IOSS Registration journey for NI Trader
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
    #   Awaiting fix to allow 3 schemes to be added
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks oss on the previous-scheme/1/2 page
    And the user adds EU111222333 on the second previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks ioss on the previous-scheme/1/3 page
    And the user answers yes on the previous-ioss-scheme/1/3 page
    And the user completes details on the previous-ioss-number/1/3 page
      | data         | fieldId                    |
      | IM3487777777 | previousSchemeNumber       |
      | IN3487777777 | previousIntermediaryNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user continues through the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Austria on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU123456788 on the first previous-oss-scheme-number/2 page
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

  Scenario: IOSS Registration journey for Norwegian Trader
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
    And the user adds Norwegian trading name on the first uk-trading-name page
    And the user answers no on the add-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Republic of Cyprus on the first previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers no on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId              |
      | IM1962223333 | previousSchemeNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the second previous-country page
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data                 | fieldId         |
      | Norway Trader        | fullName        |
      | 01111111111          | telephoneNumber |
      | test@norwayemail.com | emailAddress    |
    #    Awaiting email verification
    And the user completes details on the bank-details page
      | data               | fieldId     |
      | Trader Name Norway | accountName |
      | NORWNOK1XXX        | bic         |
      | NO9386011117947    | iban        |
    Then the user is on the check-your-answers page

  Scenario: Minimal IOSS Registration journey for NI Trader
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
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data                  | fieldId         |
      | Another Trader        | fullName        |
      | +17771117771          | telephoneNumber |
      | minimaltest@email.com | emailAddress    |
    #    Awaiting email verification
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    Then the user is on the check-your-answers page



@Registration @Accessibility

Feature: Registration journeys

  @ZAP
  Scenario: Full IOSS Registration journey for NI Trader
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


  Scenario: IOSS Registration journey for Norwegian Trader
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
    And the user adds Norwegian trading name on the first uk-trading-name page
    And the user answers no on the add-uk-trading-name page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data                 | fieldId         |
      | Norway Trader        | fullName        |
      | 01111111111          | telephoneNumber |
      | test@norwayemail.com | emailAddress    |

  Scenario: Minimal IOSS Registration journey for NI Trader
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    #   Awaiting further implementation
    And the user manually navigates to the business-contact-details page
    And the user completes details on the business-contact-details page
      | data                  | fieldId         |
      | Another Trader        | fullName        |
      | +17771117771          | telephoneNumber |
      | minimaltest@email.com | emailAddress    |





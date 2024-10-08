@Registration @Accessibility

Feature: Other Journey Kickout pages

  Scenario: Kickout when the user does not have a Fixed Establishment
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
    And the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects France on the first eu-tax page
    And the user answers no on the eu-fixed-establishment/1 page
    Then the user is on the cannot-register-need-to-operate-as-fe/1 page
    When the user continues through the cannot-register-need-to-operate-as-fe/1 page
    Then the user is on the tax-in-eu page

  Scenario: Can resume registration journey for an already entered country with a Fixed Establishment following the Fixed Establishment kickout page
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
    And the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects Ireland on the first eu-tax page
    And the user answers yes on the eu-fixed-establishment/1 page
    And the user picks vat number on the registration-tax-type/1 page
    And the user adds IE1234777WI on the first eu-vat-number page
    And the user adds Irish Goods on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data              | fieldId    |
      | 168 George Square | line1      |
      | Dublin            | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Belgium on the second eu-tax page
    And the user answers no on the eu-fixed-establishment/2 page
    Then the user is on the cannot-register-need-to-operate-as-fe/2 page
    When the user continues through the cannot-register-need-to-operate-as-fe/2 page
    Then the user is on the add-tax-details page
    And the user answers no on the add-tax-details page
    And the user adds www.first-website.com on the first website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Trader Name            | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    Then the user is on the check-your-answers page
    Then the user submits their registration




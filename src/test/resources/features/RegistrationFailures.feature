@Registration

Feature: Failures and kickouts relating to user's registration
  @Accessibility
  Scenario: User is already registered for the IOSS service
    Given the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with IOSS and VAT enrolment 100000001
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user is on the already-registered page
    When the user clicks on the Back to your account button
    Then the user is redirected to the returns dashboard

  @Accessibility
  Scenario: Error creating enrolment on submission of registration
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 222222233
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.website.com on the first website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data                  | fieldId         |
      | Another Trader        | fullName        |
      | +17771117771          | telephoneNumber |
      | minimaltest@email.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the check-your-answers page
    And the user selects the register button
    Then the user is on the error-submitting-registration page

  Scenario: Error from ETMP when submitting registration
    Given the user accesses the authority wizard
    Then the user signs into authority wizard with a Cred ID and VRN 666000001 to start registration
    And the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.website.com on the first website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data                  | fieldId         |
      | Another Trader        | fullName        |
      | +17771117771          | telephoneNumber |
      | minimaltest@email.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the check-your-answers page
    And the user selects the register button
    Then the user is on the error-submitting-registration page
    When the user accesses the authority wizard
    Then the user signs into authority wizard with a Cred ID and VRN 666000001 to retrieve saved registration
    Then the user is on the continue-registration page
    And the user picks Yes on the continue-registration page
    Then the user is on the check-your-answers page


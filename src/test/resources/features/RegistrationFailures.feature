@Registration @Accessibility

Feature: Failures and kickouts relating to user's registration

#  Requires further implementation in VEIOSS-112
#  Scenario: User is already registered for the IOSS service
#    Given the user accesses the IOSS Registration service
#    Then the user answers no on the ioss-registered page
#    And the user answers yes on the selling-goods-outside-single-market page
#    And the user answers yes on the goods-value page
#    And the user answers yes on the registered-for-vat-in-uk page
#    And the user answers yes on the ni-based page
#    And the user continues through the register-to-use-service page
#    And the user signs into authority wizard as an Organisation Admin with IOSS and VAT enrolment ADD VRN FROM STUB
#    Then the user is on the already-registered page

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
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the check-your-answers page
    And the user selects the register button
    Then the user is on the error-submitting-registration page

  Scenario: Error from ETMP when submitting registration
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 666000001
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
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Another Trader Name    | accountName |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the check-your-answers page
    And the user selects the register button
    Then the user is on the error-submitting-registration page

    #  Requires further implementation in VEIOSS-112
#  Scenario: User already has an IOSS enrolment but no registration saved in ETMP
#    Given the user accesses the IOSS Registration service
#    Then the user answers no on the ioss-registered page
#    And the user answers yes on the selling-goods-outside-single-market page
#    And the user answers yes on the goods-value page
#    And the user answers yes on the registered-for-vat-in-uk page
#    And the user answers yes on the ni-based page
#    And the user continues through the register-to-use-service page
#    And the user signs into authority wizard as an Organisation Admin with IOSS and VAT enrolment 100000001
#    Then the user is on the account-restore-error page



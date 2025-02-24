@Accessibility @SaveForLater

Feature: Save For Later Feature

  @Registration
  Scenario: A user can save their progress and return to the last page they were on
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
    And the user adds A trading name to save on the first uk-trading-name page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user answers yes on the add-uk-trading-name page
    And the user adds 2nd name! on the second uk-trading-name page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user answers no on the add-uk-trading-name page
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers yes on the previous-scheme-answers/1 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user picks oss on the previous-scheme/1/2 page
    And the user adds EU111222333 on the second previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks ioss on the previous-scheme/1/3 page
    And the user answers yes on the previous-ioss-scheme/1/3 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user completes details on the previous-ioss-number/1/3 page
      | data         | fieldId                    |
      | IM3487777777 | previousSchemeNumber       |
      | IN3487777777 | previousIntermediaryNumber |
    Then the user is on the previous-scheme-answers/1 page
    And the user continues through the previous-scheme-answers/1 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Austria on the second previous-country page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user picks oss on the previous-scheme/2/1 page
    And the user adds EU123456788 on the first previous-oss-scheme-number/2 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers yes on the tax-in-eu page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user selects Romania on the first eu-tax page
    And the user answers yes on the eu-fixed-establishment/1 page
    And the user picks vat number on the registration-tax-type/1 page
    And the user adds RO1234567890 on the first eu-vat-number page
    And the user adds Romanian Trading on the first eu-trading-name page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    Then the user answers yes on the add-tax-details page
    And the user selects Estonia on the second eu-tax page
    And the user answers yes on the eu-fixed-establishment/2 page
    And the user picks tax id number on the registration-tax-type/2 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user adds EST123987369 on the second eu-tax-identification-number page
    And the user adds Estonian Goods on the second eu-trading-name page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | ES23566       | postCode   |
    And the user continues through the check-tax-details/2 page
    And the user answers no on the add-tax-details page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user adds www.first-website.com on the first website-address page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user answers yes on the add-website-address page
    And the user adds http://www.241goods.eu on the second website-address page
    And the user answers yes on the add-website-address page
    And the user adds mywebsite.co.uk on the third website-address page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user answers no on the add-website-address page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    And the user completes the registration email verification process
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Trader Name            | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    Then the user is on the check-your-answers page
    Then the user submits their registration

  Scenario: A user can access their saved registration from government gateway login and complete it
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
    Then the user answers yes on the previous-oss page
    And the user selects Hungary on the first previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds HU11122233 on the first previous-oss-scheme-number/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers yes on the tax-in-eu page
    And the user selects Romania on the first eu-tax page
    And the user answers yes on the eu-fixed-establishment/1 page
    And the user picks vat number on the registration-tax-type/1 page
    And the user clicks on the save and come back later button
    And the user clicks on the sign out and come back later link
    And the user accesses the continue on sign in url
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user picks Yes on the continue-registration page
    And the user adds RO1234567890 on the first eu-vat-number page
    And the user adds Romanian Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Estonia on the second eu-tax page
    And the user answers yes on the eu-fixed-establishment/2 page
    And the user picks tax id number on the registration-tax-type/2 page
    And the user adds EST123987369 on the second eu-tax-identification-number page
    And the user adds Estonian Goods on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | ES23566       | postCode   |
    And the user continues through the check-tax-details/2 page
    And the user answers no on the add-tax-details page
    And the user adds www.first-website.com on the first website-address page
    And the user answers yes on the add-website-address page
    And the user adds http://www.241goods.eu on the second website-address page
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

  Scenario: A user can delete an in progress registration and start again
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
    And the user adds A first saved trading name on the first uk-trading-name page
    And the user answers yes on the add-uk-trading-name page
    And the user adds 2nd saved name! on the second uk-trading-name page
    And the user answers no on the add-uk-trading-name page
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
    And the user clicks on the save and come back later button
    And the user clicks on the sign out and come back later link
    And the user accesses the continue on sign in url
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    And the user picks No,delete my answers and start again on the continue-registration page
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    And the user chooses Yes on the confirm-vat-details page
    And the user answers no on the have-uk-trading-name page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.onlywebsite.com on the first website-address page
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
    Then the user is on the check-your-answers page
    Then the user submits their registration

  Scenario: A user can save their progress after changing answers and return to the last page they were on
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
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
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
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    Then the user clicks remove via list for second registration
    And the user answers yes on the remove-registration/2 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    Then the user selects the list change link for first previous-scheme-answers from change-previous-schemes-overview
    Then the user clicks remove via overviewLoop for second previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/2 page
    Then the user clicks remove via overviewLoop for first previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/1 page
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds www.1st-website.com on the first website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data                       | fieldId         |
      | Trader Full-Name           | fullName        |
      | 012012365214               | telephoneNumber |
      | another-test@testemail.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-account-details page
      | data                   | fieldId     |
      | Trader Full-Name       | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    Then the user is on the check-your-answers page
    Then the user selects the CYA change link for page tax-in-eu from check-your-answers
    And the user answers yes on the tax-in-eu page
    And the user selects Czech Republic on the first eu-tax page
    And the user answers yes on the eu-fixed-establishment/1 page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user picks tax id number on the registration-tax-type/1 page
    And the user adds 65410CZabc on the first eu-tax-identification-number page
    And the user adds Czech Goods on the first eu-trading-name page
    And the user clicks on the save and come back later button
    Then the user clicks on the continue to complete your registration link
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data           | fieldId    |
      | 123 The Street | line1      |
      | Prague         | townOrCity |
    And the user continues through the check-tax-details/1 page
    And the user answers no on the add-tax-details page
    And the user is on the check-your-answers page
    Then the user submits their registration

  Scenario: A user with no saved registration accessing the saved registration journey
    Given the user accesses the continue on sign in url
    When the user signs in as an Organisation Admin with VAT enrolment 100000001
    Then the user is on the no-saved-registration page



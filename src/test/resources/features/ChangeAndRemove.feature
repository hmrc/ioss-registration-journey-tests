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
    And the user answers yes on the remove-previous-scheme/1/2 page
    Then the user clicks remove via overviewExtraLoop for first previous-scheme\/1
    And the user answers yes on the remove-previous-scheme/1/1 page
    Then the user answers no on the previous-oss page
    Then the user answers yes on the tax-in-eu page
    And the user selects Bulgaria on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds BG123455555 on the first eu-vat-number page
    And the user adds Bulgarian Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Greece on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks tax id number on the registration-type/2 page
    And the user adds Greece123456 on the second eu-tax-number page
    And the user adds Grecian Trading on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | G23566        | postCode   |
    And the user continues through the check-tax-details/2 page
    Then the user clicks remove via list for second tax-details
    And the user answers yes on the remove-tax-details/2 page
    Then the user clicks remove via list for first tax-details
    And the user answers yes on the remove-tax-details/1 page
    Then the user answers no on the tax-in-eu page

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
    And the user amends data to Different trading name on the uk-trading-name/2 page
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
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks oss on the previous-scheme/1/3 page
    And the user adds EU111555999 on the new previous-oss-scheme-number/1/3 page
    Then the user continues through the previous-scheme-answers/1 page
    Then the user selects the list change link for second previous-scheme-answers from change-previous-schemes-overview
    And the user answers yes on the previous-scheme-answers/2 page
    And the user picks ioss on the previous-scheme/2/2 page
    And the user answers no on the previous-ioss-scheme/2/2 page
    And the user completes details on the previous-ioss-number/2/2 page
      | data         | fieldId              |
      | IM2339876543 | previousSchemeNumber |
    Then the user is on the previous-scheme-answers/2 page
    And the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers yes on the tax-in-eu page
    And the user selects Italy on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds IT01234567899 on the first eu-vat-number page
    And the user adds Italian Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    Then the user selects the list change link for first eu-vat-number from check-tax-details-1
    And the user amends data to IT01234567888 on the eu-vat-number/1 page
    Then the user selects the list change link for first eu-fixed-establishment-address from check-tax-details-1
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data   | fieldId |
      | Suburb | line2   |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Denmark on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks tax id number on the registration-type/2 page
    And the user adds DK123456 on the second eu-tax-number page
    And the user adds DK Trading on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | DK3566        | postCode   |
    Then the user selects the list change link for second registration-type from check-tax-details-2
    And the user picks vat number on the registration-type/2 page
    And the user adds DK12345678 on the second eu-vat-number page
    And the user continues through the check-tax-details/2 page

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
    And the user amends data to Different Norwegian trading name on the uk-trading-name/1 page
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
    And the user answers yes on the remove-previous-scheme/1/1 page
    Then the user answers yes on the previous-oss page
    And the user selects France on the new-first-previous-scheme previous-country page
    And the user picks ioss on the previous-scheme/1/1 page
    And the user answers yes on the previous-ioss-scheme/1/1 page
    And the user completes details on the previous-ioss-number/1/1 page
      | data         | fieldId                    |
      | IM2506544561 | previousSchemeNumber       |
      | IN2507412365 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/1 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers yes on the tax-in-eu page
    And the user selects Romania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds RO1234567890 on the first eu-vat-number page
    And the user adds Romanian Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Estonia on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks tax id number on the registration-type/2 page
    And the user adds EST123987369 on the second eu-tax-number page
    And the user adds Estonian Goods on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | ES23566       | postCode   |
    And the user continues through the check-tax-details/2 page
    Then the user selects the list change link for first check-tax-details from change-add-tax-details
    Then the user selects the list change link for first registration-type from check-tax-details-1
    And the user picks tax id number on the registration-type/1 page
    And the user adds ROM12345R on the first eu-tax-number page
    And the user continues through the check-tax-details/1 page
    Then the user clicks remove via list for second tax-details
    And the user answers yes on the remove-tax-details/2 page
    And the user answers no on the add-tax-details page



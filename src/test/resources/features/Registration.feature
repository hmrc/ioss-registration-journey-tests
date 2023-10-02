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
    And the user answers no on the add-tax-details page
    And the user adds www.first-website.com on the first website-address page
    And the user answers yes on the add-website-address page
    And the user adds http://www.241goods.eu on the second website-address page
    And the user answers yes on the add-website-address page
    And the user adds mywebsite.co.uk on the third website-address page
    And the user answers no on the add-website-address page
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
    Then the user answers yes on the tax-in-eu page
    And the user selects Spain on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds 1236ES34x on the first eu-tax-number page
    And the user adds Spanish-Trading Name on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data               | fieldId       |
      | 5201 Spanish Plaza | line1         |
      | Spanish Area       | line2         |
      | Barcelona          | townOrCity    |
      | Catalonia          | stateOrRegion |
      | ES 56201           | postCode      |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Germany on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks vat number on the registration-type/2 page
    And the user adds DE999555111 on the second eu-vat-number page
    And the user adds German Food Wholesalers Ltd on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data             | fieldId    |
      | 63 German Street | line1      |
      | Munich           | townOrCity |
    And the user continues through the check-tax-details/2 page
    And the user answers no on the add-tax-details page
    And the user adds a-norwegian-website.no on the first website-address page
    And the user answers yes on the add-website-address page
    And the user adds www.3rd-norwegian-website.no on the second website-address page
    And the user answers no on the add-website-address page
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
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds https://www.onlywebsite.com on the first website-address page
    And the user answers no on the add-website-address page
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



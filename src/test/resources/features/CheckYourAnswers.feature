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
    And the user completes details on the previous-ioss-number/2/1 page
      | data         | fieldId                    |
      | IM0401234567 | previousSchemeNumber       |
      | IN0407654321 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers yes on the tax-in-eu page
    And the user selects Austria on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds ATU11119876 on the first eu-vat-number page
    And the user adds Austrian-Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Sweden on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks tax id number on the registration-type/2 page
    And the user adds SW654321PLC on the second eu-tax-number page
    And the user adds Swedish Goods on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | S120 AB655    | postCode   |
    And the user continues through the check-tax-details/2 page
    And the user answers no on the add-tax-details page
    And the user adds www.websiteone.com on the first website-address page
    And the user answers yes on the add-website-address page
    And the user adds www.websitetwo.com on the second website-address page
    And the user answers yes on the add-website-address page
    And the user adds www.websitethree.com on the third website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Trader Name            | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    Then the user is on the check-your-answers page
    Then the user selects the CYA change link for page add-uk-trading-name from check-your-answers
    Then the user selects the list within CYA change link for second uk-trading-name from change-add-uk-trading-name
    And the user amends data to CYA trading name on the uk-trading-name/2 page
    Then the user clicks remove via CYA route for third uk-trading-name
    And the user answers yes on the remove-uk-trading-name/3 page
    Then the user answers no on the add-uk-trading-name page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page previous-schemes-overview from check-your-answers
    Then the user clicks remove via CYA route for second deregistration
    And the user answers yes on the remove-deregistration/2 page
    Then the user selects the list within CYA change link for first previous-scheme-answers from change-previous-schemes-overview
    And the user answers yes on the previous-scheme-answers/1 page
    And the user picks ioss on the previous-scheme/1/2 page
    And the user answers yes on the previous-ioss-scheme/1/2 page
    And the user completes details on the previous-ioss-number/1/2 page
      | data         | fieldId                    |
      | IM3487777777 | previousSchemeNumber       |
      | IN3487777777 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/1 page
    And the user answers no on the previous-schemes-overview page
#    Will need further navigation added
#    Then the user is on the check-your-answers page
    Then the user manually navigates to the check-your-answers page
    Then the user selects the CYA change link for page add-tax-details from check-your-answers
    Then the user clicks remove via CYA route for second tax-details
    And the user answers yes on the remove-tax-details/2 page
    Then the user selects the list within CYA change link for first check-tax-details from change-add-tax-details
    Then the user selects the additional tax details list within CYA change link for first eu-trading-name from check-tax-details-1
    And the user amends data to CYA trading name on the eu-trading-name/1 page
    And the user continues through the check-tax-details page
    And the user answers yes on the add-tax-details page
    And the user selects Luxembourg on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks vat number on the registration-type/2 page
    And the user adds LU88776655 on the second eu-vat-number page
    And the user adds Lux Trading on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/2 page
    Then the user answers no on the add-tax-details page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page add-website-address from check-your-answers
    Then the user clicks remove via CYA route for second website-address
    And the user answers yes on the remove-website-address/2 page
    Then the user answers yes on the add-website-address page
    And the user adds www.newwebsitethree.com on the third website-address page
    Then the user selects the list within CYA change link for second website-address from change-add-website-address
    And the user amends data to www.websitetwo.com on the website-address/2 page
    Then the user answers no on the add-website-address page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page business-contact-details from check-your-answers
    And the user amends details on the business-contact-details page
      | data          | fieldId         |
      | 5555555555555 | telephoneNumber |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page bank-details from check-your-answers
    And the user amends details on the bank-details page
      | data                   | fieldId     |
      | Trader Name NI         | accountName |
      | ABCDEF2A               | bic         |
      | GB29NWBK60161331926819 | iban        |
    And the user is on the check-your-answers page
    Then the user submits their registration

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
    And the user completes details on the previous-ioss-number/2/1 page
      | data         | fieldId                    |
      | IM0401234567 | previousSchemeNumber       |
      | IN0407654321 | previousIntermediaryNumber |
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers no on the previous-schemes-overview page
    Then the user answers yes on the tax-in-eu page
    And the user selects Slovakia on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds SK1234567890 on the first eu-vat-number page
    And the user adds Slovakia Trading on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | A Town        | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Malta on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks tax id number on the registration-type/2 page
    And the user adds 12385411556 on the second eu-tax-number page
    And the user adds Maltese Goods on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data          | fieldId    |
      | 1 Street Name | line1      |
      | Suburb        | line2      |
      | A Town        | townOrCity |
      | MT23566       | postCode   |
    And the user continues through the check-tax-details/2 page
    And the user answers no on the add-tax-details page
    And the user adds www.1website.com on the first website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-details page
      | data                   | fieldId     |
      | Trader Name            | accountName |
      | ABCDEF2A               | bic         |
      | GB33BUKB20201555555555 | iban        |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page have-uk-trading-name from check-your-answers
    And the user answers no on the have-uk-trading-name page
    And the user answers yes on the remove-all-trading-names page
    Then the user is on the check-your-answers page
    Then the user selects the CYA change link for page previous-oss from check-your-answers
    And the user answers no on the previous-oss page
    Then the user answers yes on the remove-all-previous-registrations page
    Then the user is on the check-your-answers page
    Then the user selects the CYA change link for page tax-in-eu from check-your-answers
    And the user answers no on the tax-in-eu page
    Then the user answers yes on the remove-all-tax-details page
    Then the user is on the check-your-answers page
    Then the user submits their registration


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
    Then the user answers no on the previous-oss page
    Then the user answers no on the tax-in-eu page
    And the user adds www.1st-norwegian-website.no on the first website-address page
    And the user answers yes on the add-website-address page
    And the user adds https://otherwebsite.eu on the second website-address page
    And the user answers no on the add-website-address page
    And the user completes details on the business-contact-details page
      | data               | fieldId         |
      | Trader Name        | fullName        |
      | 07771117771        | telephoneNumber |
      | test@testemail.com | emailAddress    |
    And the user completes the registration email verification process
    And the user completes details on the bank-details page
      | data               | fieldId     |
      | Trader Name Norway | accountName |
      | NORWNOK1XXX        | bic         |
      | NO9386011117947    | iban        |
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page add-uk-trading-name from check-your-answers
    Then the user selects the list within CYA change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends data to CYA Norwegian trading name on the uk-trading-name/1 page
    Then the user answers no on the add-uk-trading-name page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page previous-oss from check-your-answers
    And the user answers yes on the previous-oss page
    Then the user selects Germany on the cya-new-first-previous-scheme previous-country page
    And the user picks oss on the previous-scheme/1/1 page
    And the user adds DE987654321 on the new previous-oss-scheme-number/1/1 page
    Then the user is on the previous-scheme-answers/1 page
    And the user answers no on the previous-scheme-answers/1 page
    And the user answers no on the previous-schemes-overview page
#    Requires further navigation changes
    And the user manually navigates to the check-your-answers page
    Then the user selects the CYA change link for page tax-in-eu from check-your-answers
    And the user answers yes on the tax-in-eu page
    And the user selects Czech Republic on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds 65410CZabc on the first eu-tax-number page
    And the user adds Czech Goods on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data           | fieldId    |
      | 123 The Street | line1      |
      | Prague         | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers yes on the add-tax-details page
    And the user selects Denmark on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks vat number on the registration-type/2 page
    And the user adds DK12344321 on the second eu-vat-number page
    And the user adds Danish Trading on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data              | fieldId    |
      | 635 Danish Street | line1      |
      | Copenhagen        | townOrCity |
    And the user continues through the check-tax-details/2 page
    And the user answers no on the add-tax-details page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page add-website-address from check-your-answers
    Then the user selects the list within CYA change link for first website-address from change-add-website-address
    And the user amends data to www.1stNorwegianCYAwebsite.no on the website-address/1 page
    Then the user answers no on the add-website-address page
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page business-contact-details from check-your-answers
    And the user amends details on the business-contact-details page
      | data                  | fieldId      |
      | Norway Trader Update  | fullName     |
      | test@newtestemail.com | emailAddress |
    And the user completes the change answers email verification process
    And the user is on the check-your-answers page
    Then the user selects the CYA change link for page bank-details from check-your-answers
    And the user amends details on the bank-details page
      | data                  | fieldId     |
      | Different Name Norway | accountName |
    And the user is on the check-your-answers page
    Then the user submits their registration



@Registration @Accessibility

Feature: Amend Registration journeys

  @ZAP
  Scenario: An IOSS registered user amends registration answers from yes to no for optional sections
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    Needs VEIOSS-219
#    Then the user selects the amend change link for page have-uk-trading-name from change-your-registration
#    And the user answers no on the have-uk-trading-name page
#    And the user answers yes on the remove-all-trading-names page
#    Then the user is on the change-your-registration page
#    Needs VEIOSS-225
#    Then the user selects the amend change link for page previous-oss from change-your-registration
#    And the user answers no on the previous-oss page
#    Then the user answers yes on the remove-all-previous-registrations page
#    Then the user is on the change-your-registration page
    Then the user selects the amend change link for page tax-in-eu from change-your-registration
    And the user answers no on the tax-in-eu page
    Then the user answers yes on the remove-all-tax-details page
    Then the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

  Scenario: An IOSS registered user amends non-mandatory registration answers
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page add-uk-trading-name from change-your-registration
    Then the user clicks remove via amend route for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user selects the list within amend change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends answer to an amended trading name
    Then the user answers yes on the add-uk-trading-name page
    And the user adds new 2nd name on the second uk-trading-name page
    Then the user answers no on the add-uk-trading-name page
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page previous-schemes-overview from change-your-registration
    Then the user selects the list within amend change link for second previous-scheme-answers from change-previous-schemes-overview
    Then the user answers yes on the previous-scheme-answers/2 page
    And the user picks oss on the previous-scheme/2/2 page
    And the user adds CY12345678X on the second previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the third previous-country page
    And the user picks oss on the previous-scheme/3/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/3 page
    Then the user answers no on the previous-scheme-answers/3 page
  #    Page got skipped during navigation
#    And the user answers no on the previous-schemes-overview page
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page add-tax-details from change-your-registration
    Then the user selects the list within amend change link for first check-tax-details from change-add-tax-details
    Then the user selects the additional tax details list within amend change link for first registration-type from check-tax-details-1
    And the user picks tax id number on the registration-type/1 page
#    Needs navigation to go to tax id number
    Then the user selects the additional tax details list within amend change link for first eu-fixed-establishment-address from check-tax-details-1
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data   | fieldId |
      | Suburb | line2   |
    Then the user continues through the check-tax-details/1 page
    And the user answers yes on the add-tax-details page
    And the user selects Estonia on the second eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/2 page
    And the user picks vat number on the registration-type/2 page
    And the user adds EE123456789 on the second eu-vat-number page
    And the user adds Estonian Wholesalers Ltd on the second eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/2 page
      | data                | fieldId    |
      | 111 Estonian Street | line1      |
      | Tallinn             | townOrCity |
      | TAL 1234A           | postCode   |
    And the user continues through the check-tax-details/2 page
    And the user answers yes on the add-tax-details page
    And the user selects Portugal on the third eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/3 page
    And the user picks tax id number on the registration-type/3 page
    And the user adds PT12345A on the third eu-tax-number page
    And the user adds Portuguese Trading on the third eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/3 page
      | data             | fieldId    |
      | 111 Porto Street | line1      |
      | Porto            | townOrCity |
    And the user continues through the check-tax-details/3 page
    Then the user selects the list within amend change link for third check-tax-details from change-add-tax-details
    Then the user selects the additional tax details list within amend change link for third registration-type from check-tax-details-3
    And the user picks vat number on the registration-type/3 page
    And the user adds PT123456789 on the third eu-vat-number page
    And the user continues through the check-tax-details/3 page
    And the user answers no on the add-tax-details page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

  Scenario: An IOSS registered user removes some registration answers and amends mandatory answers
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page add-tax-details from change-your-registration
    Then the user clicks remove via amend route for first tax-details
    And the user answers yes on the remove-tax-details/1 page
    Then the user continues through the tax-in-eu page
    And the user selects Estonia on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds EE123456789 on the first eu-vat-number page
    And the user adds Estonian Wholesalers Ltd on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data                | fieldId    |
      | 111 Estonian Street | line1      |
      | Tallinn             | townOrCity |
    And the user continues through the check-tax-details/1 page
    And the user answers no on the add-tax-details page
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page add-website-address from change-your-registration
    Then the user clicks remove via amend route for second website-address
    And the user answers yes on the remove-website-address/2 page
    And the user selects the list within amend change link for first website-address from change-add-website-address
    And the user amends data to www.amended-website-name.com on the website-address/1 page
    Then the user answers no on the add-website-address page
    And the user is on the change-your-registration page
#    Then the user selects the amend change link for page business-contact-details from change-your-registration
#    And the user completes details on the business-contact-details page
#      | data                  | fieldId         |
#      | Another Trader        | fullName        |
#      | +17771117771          | telephoneNumber |
#    Currently email verification is being triggered even if email address didn't change
#  Then when going through that to finish journey - i'm getting cannot-register-already-registered
#    And the user is on the change-your-registration page
#    Then the user selects the amend change link for page bank-details from change-your-registration
#    And the user completes details on the bank-details page
#      | data                   | fieldId     |
#      | Another Trader Name    | accountName |
#      | GB29NWBK60161331926819 | iban        |
#    After submitting this page, also got the cannot-register-already-registered page
#  And the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

  Scenario: An IOSS registered user amends their email address
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page business-contact-details from change-your-registration
    And the user completes details on the business-contact-details page
      | data                 | fieldId      |
      | amend-test@email.com | emailAddress |
    And the user completes the amend registration email verification process
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

  Scenario: A user can cancel the amendments to their registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user clicks on the cancel link
    And the user answers yes on the cancel-amend-registration page
#    Will need the returns backend changes merged for this to work
#    Then the user is redirected to the returns dashboard

  Scenario: A user who gets not found from ETMP for an IOSS registration receives the technical difficulties page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999999 accesses the amend registration journey
    Then the user is presented with the technical difficulties page

  Scenario: An IOSS registered user receives an ETMP failure on submission of an amendment
    Given the user accesses the authority wizard
    And a user with VRN 600000022 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the error-submitting-amendment page

  Scenario: An IOSS registered user can add quarantined/excluded registrations in amend EU tax details - VAT Number
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999998 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page tax-in-eu from change-your-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks vat number on the registration-type/1 page
    And the user adds LT999888777 on the first eu-vat-number page
    And the user adds Lithuanian Goods on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data            | fieldId    |
      | 168 Town Square | line1      |
      | Vilnius         | townOrCity |
    And the user continues through the check-tax-details/1 page
    Then the user answers no on the add-tax-details page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

  Scenario: An IOSS registered user can add quarantined/excluded registrations in amend EU tax details - Tax ID
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999998 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page tax-in-eu from change-your-registration
    And the user answers yes on the tax-in-eu page
    And the user selects Lithuania on the first eu-tax page
    And the user picks fixed establishment on the how-do-you-operate/1 page
    And the user picks tax id number on the registration-type/1 page
    And the user adds ABC123123 on the first eu-tax-number page
    And the user adds Lithuanian Goods on the first eu-trading-name page
    And the user completes details on the eu-fixed-establishment-address/1 page
      | data            | fieldId    |
      | 168 Town Square | line1      |
      | Vilnius         | townOrCity |
      And the user continues through the check-tax-details/1 page
      Then the user answers no on the add-tax-details page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

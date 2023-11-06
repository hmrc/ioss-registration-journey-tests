@Registration @Accessibility

Feature: Amend Registration journeys

  @ZAP
  Scenario: An IOSS registered user amends registration answers from yes to no for optional sections
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    Needs VEIOSS-219
#    Then the user selects the amend change link for page have-uk-trading-name from amend-your-answers
#    And the user answers no on the have-uk-trading-name page
#    And the user answers yes on the remove-all-trading-names page
#    Then the user is on the change-your-registration page
#    Needs VEIOSS-225
#    Then the user selects the amend change link for page previous-oss from amend-your-answers
#    And the user answers no on the previous-oss page
#    Then the user answers yes on the remove-all-previous-registrations page
#    Then the user is on the change-your-registration page
#    Needs VEIOSS-220
#    Then the user selects the amend change link for page tax-in-eu from amend-your-answers
#    And the user answers no on the tax-in-eu page
#    Then the user answers yes on the remove-all-tax-details page
#    Then the user is on the change-your-registration page
    And the user continues through the change-your-registration page
#    Needs VEIOSS-230
#    Then the user is on the successful-amend page

  Scenario: An IOSS registered user amends non-mandatory registration answers
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page add-uk-trading-name from amend-your-answers
    Then the user clicks remove via amend route for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user selects the list within amend change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends answer to an amended trading name
    Then the user answers yes on the add-uk-trading-name page
    And the user adds new 2nd name on the second uk-trading-name page
    Then the user answers no on the add-uk-trading-name page
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page previous-schemes-overview from amend-your-answers
    Then the user selects the list within amend change link for second previous-scheme-answers from change-previous-schemes-overview
    Then the user answers yes on the previous-scheme-answers/2 page
    And the user picks oss on the previous-scheme/2/2 page
    And the user adds CY12345678X on the second previous-oss-scheme-number/2 page
#    This page got skipped during navigation so needs adding
#    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the third previous-country page
    And the user picks oss on the previous-scheme/3/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/3 page
#    Both of these pages got skipped during navigation
#    Then the user answers no on the previous-scheme-answers/2 page
#    And the user answers no on the previous-schemes-overview page
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page add-tax-details from amend-your-answers
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
#    Needs VEIOSS-230
#    Then the user is on the successful-amend page
@wip
  Scenario: An IOSS registered user removes some non-mandatory registration answers and amends mandatory answers
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page


#  Email verification


  Scenario: A user can cancel the amendments to their registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    And the user is on the change-your-registration page
    Then the user clicks on the cancel link
    And the user answers yes on the cancel-amend-registration page
    Then the user is redirected to the returns dashboard

  Scenario: A user who gets not found from ETMP for an IOSS registration receives the technical difficulties page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999999 accesses the amend registration journey
    Then the user is presented with the technical difficulties page


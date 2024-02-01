@Registration

Feature: Rejoin registration journeys

#Reminders
#  @ZAP and @Accessibility on full rejoin journeys
#  6 years after exclusion effective date? view and amend
#  rejoin with excluded trader - minimal reg - amend validation etc
#  rejoin with excluded trader - full reg - amend validation, yes to nos etc
#  rejoin with trader in same period that exclusion took effect and input dofs prior to exclusion effective date - should be offered reversal of original exclusion
@wip
  Scenario: A trader with minimal details in their original registration can amend and rejoin
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9019999998 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
#    amends
  Then the user selects the amend change link for page have-uk-trading-name from rejoin-registration
  And the user answers yes on the have-uk-trading-name page
  And the user adds A new trading name in amend journey on the first uk-trading-name page
  And the user answers no on the add-uk-trading-name page
  Then the user is on the change-your-registration page
  Then the user selects the amend change link for page previous-oss from change-your-registration
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
  Then the user is on the change-your-registration page
  Then the user selects the amend change link for page tax-in-eu from change-your-registration
  And the user answers yes on the tax-in-eu page
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
  Then the user answers no on the add-tax-details page
#    amends end
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  Scenario: A trader with an expired quarantine period can submit a rejoin registration without amending any details
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9002999993 accesses the rejoin registration journey
    Then the user is on the rejoin-registration page
    When the user continues through the rejoin-registration page
    Then the user is on the successful-rejoin page

  @Accessibility
  Scenario: A trader with a future exclusion effective date is not able to access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999997 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A quarantined trader is not able to access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999993 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A currently registered IOSS trader who is not excluded cannot access the rejoin registration journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the rejoin registration journey
    Then the user is on the cannot-rejoin page

  Scenario: A user who gets not found from ETMP for an IOSS registration receives the technical difficulties page
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9009999999 accesses the rejoin registration journey
    Then the user is presented with the technical difficulties page
  @Accessibility
  Scenario: An IOSS registered user receives an ETMP failure on submission of a rejoin
    Given the user accesses the authority wizard
    And a user with VRN 600000022 and IOSS Number IM9029999997 accesses the rejoin registration journey
    And the user is on the rejoin-registration page
    And the user continues through the rejoin-registration page
    Then the user is on the error-submitting-rejoin page


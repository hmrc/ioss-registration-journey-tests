@Registration @Accessibility

Feature: Kickout journeys

  Scenario: Trader already registered for the IOSS scheme in another EU country - filter questions
    Given the user accesses the IOSS Registration service
    Then the user answers yes on the ioss-registered page
    Then the user is on the cannot-register-already-registered page

#    TODO: Need a BTA version of this to test link when developed

  Scenario: Trader does not sell goods from countries outside the EU to consumers in the EU/NI - filter questions
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers no on the selling-goods-outside-single-market page
    Then the user is on the cannot-register-ioss page

  Scenario: Trader does not sell goods with a consignment value of £135 or less - filter questions
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers no on the goods-value page
    Then the user is on the do-not-need-to-account-for-eu-vat page

  Scenario: Trader is not registered for VAT in the UK - filter questions
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers no on the registered-for-vat-in-uk page
    Then the user is on the cannot-register-no-vat-in-uk page

  Scenario: Trader is not a Northern Irish or Norwegian business - filter questions
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers no on the ni-based page
    And the user answers no on the norway-based page
    Then the user is on the cannot-register-no-ni-or-norway-business page



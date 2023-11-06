@Registration @Accessibility

Feature: Amend Registration journeys

  @ZAP
  Scenario: An IOSS registered user accesses the amend journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9001234567 accesses the amend registration journey
    Then the user is on the change-your-registration page

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


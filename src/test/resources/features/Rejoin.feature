@Registration

Feature: Rejoin registration journeys

#Reminders
#  @ZAP and @Accessibility on full rejoin journeys
#  rejoins should be given new IOSS numbers
#  6 years after exclusion effective date? view and amend
#  rejoin with excluded trader - minimal reg - amend validation etc
#  rejoin with excluded trader - full reg - amend validation etc
#  rejoin with trader in same period that exclusion took effect and input dofs prior to exclusion effective date - should be offered reversal of original exclusion

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


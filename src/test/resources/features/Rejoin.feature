@Registration

Feature: Rejoin registration journeys

#Reminders
#  @ZAP and @Accessibility on full rejoin journeys
#  rejoins should be given new IOSS numbers
#  6 years after exclusion effective date? view and amend
#  rejoin with excluded trader - minimal reg - amend validation etc
#  rejoin with excluded trader - full reg - amend validation etc
#  rejoin with trader outside of quarantine period
#  rejoin with trader in same period that exclusion took effect and input dofs prior to exclusion effective date - should be offered reversal of original exclusion
#  ETMP failures on retrieving reg and on submission

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


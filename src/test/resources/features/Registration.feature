@Registration

Feature: Registration journeys

  @ZAP @Accessibility
  Scenario: User can login and access the IOSS Registration Service
    Given the user accesses the IOSS Registration service
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001 and strong credentials
    And the user is at the beginning of the signed in IOSS Registration journey


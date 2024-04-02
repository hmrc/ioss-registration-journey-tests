@Registration @All

Feature: Entering the Registration service via BTA

#  Scenario: A user registers via BTA and then has the correct BTA link on the registration submission page
#  No BTA link yet

  Scenario: The user enters the service via BTA and is directed back to BTA via the Already EU Registered kickout page
    Given the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 100000001
    Then the user manually navigates to the test-only/from-external page
    And the user answers yes on the ioss-registered page
    Then the user is on the cannot-register-already-registered page
    Then the user clicks on the BTA link
    And the user is directed to the BTA service

  Scenario: A Welsh user enters the service via BTA and is first directed to the Welsh transition page before continuing
    Given the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 100000001
    Then the user manually navigates to the test-only/from-external?lang=cy page
    And the user is on the no-welsh-service page
    Then the user continues through the no-welsh-service page
    And the user is on the ioss-registered page

  Scenario: A user enters the service via BTA using the en parameter and is directed straight to the registration service
    Given the user accesses the authority wizard
    And the user signs into authority wizard as an Organisation Admin with VAT enrolment 100000001
    Then the user manually navigates to the test-only/from-external?lang=en page
    And the user is on the ioss-registered page



@Registration @Accessibility

Feature: Government Gateway Login Kickout journeys

  Background:
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page

  Scenario: GG kickout when the user is not registered for VAT
    Then the user signs in as an Organisation Admin without VAT enrolment 100000001
    And the user is on the credential-unsupported page
#    Content should be https://import-one-stop-shop-prototype.herokuapp.com/1-0/registration/kickout-no-vat

  Scenario: GG kickout when the user is an agent
    Then the user accesses the authority wizard
    And the user signs into authority wizard as an Agent Admin with VAT enrolment 100000001
    And the user is on the cannot-use-agent page

  Scenario: GG kickout when the user is not an admin user
    Then the user signs in as an Organisation Non-Admin without VAT enrolment 100000001
    And the user is on the role-unsupported page




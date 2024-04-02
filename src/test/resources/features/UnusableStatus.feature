@Registration @Accessibility @All

Feature: Unusable Status Email Journey

  Scenario: Intercept page for when a traders email address has unusable status
    Given the user accesses the authority wizard
    When the user logs into the returns service
    Then the user is redirected to the email intercept page
    And the user clicks the Confirm email address button
    Then the user is redirected to the Business contact details page within Change your registration
    And the user amends details on the business-contact-details page
      | data                     | fieldId      |
      | different-email@test.com | emailAddress |
    And the user completes the amend registration email verification process
    Then the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page



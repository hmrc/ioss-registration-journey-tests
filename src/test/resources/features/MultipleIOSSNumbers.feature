@Registration @Accessibility

Feature: Multiple IOSS Number Registration journeys

  Scenario: An IOSS registered user with one previous registration accesses the amend journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
#    Checking if this number should be there on the current page or not
    Then the correct IOSS number IM9007230000 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user answers no on the change-your-previous-registrations?waypoints=change-your-registration page
    Then the user is on the change-your-registration page
#    Checking if this number should be there on the current page or not
    Then the correct IOSS number IM9007230000 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user answers yes on the change-your-previous-registrations?waypoints=change-your-registration page
    Then the correct IOSS number IM9006230000 is displayed on the page
#  Links are showing for change/add on all sections instead of just contact details/bank details - needs fixing
#  Also could do manual checks on urls to make sure I can't amend them:
#have-uk-trading-name?waypoints=change-your-registration
#add-uk-trading-name?waypoints=change-your-registration
#tax-in-eu?waypoints=change-your-registration
#previous-schemes-overview?waypoints=change-your-registration
#add-tax-details?waypoints=change-your-registration
#add-website-address?waypoints=change-your-registration
    Then the user selects the amend change link for page business-contact-details from change-your-registration
    And the user completes details on the business-contact-details page
      | data                                 | fieldId         |
      | Previous Registration Trader         | fullName        |
      | +17771117771                         | telephoneNumber |
      | previous-registration-test@email.com | emailAddress    |
    And the user completes the amend registration email verification process
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page bank-details from change-your-registration
    And the user completes details on the bank-details page
      | data                              | fieldId     |
      | Previous Registration Trader Name | accountName |
      | GB29NWBK60161331926819            | iban        |
    And the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page
    When the user manually navigates to the start-amend-journey page
    Then the user is on the change-your-registration page
    #    Checking if this number should be there on the current page or not
    And the correct IOSS number IM9007230000 is displayed on the page
    When the user selects the amend change link for page add-uk-trading-name from change-your-registration
    Then the user clicks remove via amend route for first uk-trading-name
    And the user answers yes on the remove-uk-trading-name/1 page
    Then the user selects the list within amend change link for first uk-trading-name from change-add-uk-trading-name
    And the user amends answer to an amended current trading name
    Then the user answers yes on the add-uk-trading-name page
    And the user adds a new current trading name on the second uk-trading-name page
    Then the user answers no on the add-uk-trading-name page
    Then the user is on the change-your-registration page
    Then the user selects the amend change link for page tax-in-eu from change-your-registration
    And the user answers no on the tax-in-eu page
    Then the user answers yes on the remove-all-tax-details page
    Then the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page

  Scenario: An IOSS registered user with multiple previous registrations accesses the amend journey
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007230003 accesses the amend registration journey
    Then the user is on the change-your-registration page
    #    Checking if this number should be there on the current page or not
    Then the correct IOSS number IM9007230003 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user picks IM9007230001 on the change-your-previous-registrations?waypoints=change-your-registration page
    Then the correct IOSS number IM9007230001 is displayed on the page
#    Do checks on amend options that shouldn't be there
    Then the user selects the amend change link for page business-contact-details from change-your-registration
    And the user completes details on the business-contact-details page
      | data                                          | fieldId         |
      | Previous Multiple Registration Trader         | fullName        |
      | +17771117771                                  | telephoneNumber |
      | previous-registration-test-multiple@email.com | emailAddress    |
    And the user completes the amend registration email verification process
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page bank-details from change-your-registration
    And the user completes details on the bank-details page
      | data                                       | fieldId     |
      | Previous Multiple Registration Trader Name | accountName |
      | GB29NWBK60161331926819                     | iban        |
    And the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page
    When the user manually navigates to the start-amend-journey page
    Then the user is on the change-your-registration page
    #    Checking if this number should be there on the current page or not
    And the correct IOSS number IM9007230003 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user picks IM9007230002 on the change-your-previous-registrations?waypoints=change-your-registration page
    Then the correct IOSS number IM9007230002 is displayed on the page
#    Do checks on amend options that shouldn't be there
    Then the user selects the amend change link for page business-contact-details from change-your-registration
    And the user completes details on the business-contact-details page
      | data                                    | fieldId  |
      | Previous Multiple Registration Trader 2 | fullName |
    And the user completes the amend registration email verification process
    And the user is on the change-your-registration page
    Then the user selects the amend change link for page bank-details from change-your-registration
    And the user completes details on the bank-details page
      | data                                         | fieldId     |
      | Previous Multiple Registration Trader Name 2 | accountName |
    And the user is on the change-your-registration page
    And the user continues through the change-your-registration page
    Then the user is on the successful-amend page
    When the user manually navigates to the start-amend-journey page
    Then the user is on the change-your-registration page
    #    Checking if this number should be there on the current page or not
    And the correct IOSS number IM9007230003 is displayed on the page
#    add other amends that weren't done in first current reg



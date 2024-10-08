@Accessibility @MultipleIOSS

Feature: Multiple IOSS Number Registration journeys

  Scenario: An IOSS registered user with one previous registration can amend the correct sections of each registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
    Then the correct IOSS number IM9007230000 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user answers no on the change-your-previous-registration?waypoints=change-your-registration page
    Then the user is on the change-your-registration page
    Then the correct IOSS number IM9007230000 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user answers yes on the change-your-previous-registration?waypoints=change-your-registration page
    Then the user is on the change-a-previous-registration page
    Then the correct IOSS number IM9006230000 is displayed on the page
    Then the user selects the amend change link for page business-contact-details from change-a-previous-registration
    And the user completes details on the business-contact-details page
      | data                                        | fieldId         |
      | Previous Single Registration Trader         | fullName        |
      | 01234567891                                 | telephoneNumber |
      | previous-single-registration-test@email.com | emailAddress    |
    And the user completes the amend previous registration email verification process
    And the user is on the change-a-previous-registration page
    Then the user selects the amend change link for page bank-account-details from change-a-previous-registration
    And the user completes details on the bank-account-details page
      | data                                     | fieldId     |
      | Previous Single Registration Trader Name | accountName |
      | GB29NWBK60161331926819                   | iban        |
    And the user is on the change-a-previous-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
    And all of the amended answers for previous registration IM9006230000 are displayed on the confirmation
    When the user manually navigates to the start-amend-journey page
    Then the user is on the change-your-registration page
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
    And the user submits their amended registration
    Then the user is on the successful-amend page
    And all of the amended answers for previous registration IM9007230000 are displayed on the confirmation

  Scenario: An IOSS registered user with multiple previous registrations can amend the correct sections of each registration
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007230003 accesses the amend registration journey
    Then the user is on the change-your-registration page
    Then the correct IOSS number IM9007230003 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user picks IM9007230001 on the change-your-previous-registrations?waypoints=change-your-registration page
    Then the user is on the change-a-previous-registration page
    Then the correct IOSS number IM9007230001 is displayed on the page
    Then the user selects the amend change link for page business-contact-details from change-a-previous-registration
    And the user completes details on the business-contact-details page
      | data                                          | fieldId         |
      | Previous Multiple Registration Trader         | fullName        |
      | +17771117771                                  | telephoneNumber |
      | previous-registration-test-multiple@email.com | emailAddress    |
    And the user completes the amend previous registration email verification process
    And the user is on the change-a-previous-registration page
    Then the user selects the amend change link for page bank-account-details from change-a-previous-registration
    And the user completes details on the bank-account-details page
      | data                                       | fieldId     |
      | Previous Multiple Registration Trader Name | accountName |
      | GB29NWBK60161331926819                     | iban        |
    And the user is on the change-a-previous-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
    And all of the amended answers for previous registration IM9007230001 are displayed on the confirmation
    When the user manually navigates to the start-amend-journey page
    Then the user is on the change-your-registration page
    And the correct IOSS number IM9007230003 is displayed on the page
    When the user clicks on the View or change your previous registration link
    And the user picks IM9007230002 on the change-your-previous-registrations?waypoints=change-your-registration page
    Then the user is on the change-a-previous-registration page
    Then the correct IOSS number IM9007230002 is displayed on the page
    Then the user selects the amend change link for page business-contact-details from change-a-previous-registration
    And the user completes details on the business-contact-details page
      | data                                    | fieldId  |
      | Previous Multiple Registration Trader 2 | fullName |
    And the user completes the second amend previous registration email verification process
    And the user is on the change-a-previous-registration page
    Then the user selects the amend change link for page bank-account-details from change-a-previous-registration
    And the user completes details on the bank-account-details page
      | data                                         | fieldId     |
      | Previous Multiple Registration Trader Name 2 | accountName |
    And the user is on the change-a-previous-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
    And all of the amended answers for previous registration IM9007230002 are displayed on the confirmation
    When the user manually navigates to the start-amend-journey page
    Then the user is on the change-your-registration page
    And the correct IOSS number IM9007230003 is displayed on the page
    Then the user selects the amend change link for page add-website-address from change-your-registration
    Then the user clicks remove via amend route for second website-address
    And the user answers yes on the remove-website-address/2 page
    And the user selects the list within amend change link for first website-address from change-add-website-address
    And the user amends data to www.amended-website-name-multiple-ioss.com on the website-address/1 page
    Then the user answers yes on the add-website-address page
    And the user adds https://www.2ndmultipleiosswebsite.eu on the second website-address page
    Then the user answers no on the add-website-address page
    Then the user selects the amend change link for page previous-schemes-overview from change-your-registration
    Then the user selects the list within amend change link for second previous-scheme-answers from change-previous-schemes-overview
    Then the user answers yes on the previous-scheme-answers/2 page
    And the user picks oss on the previous-scheme/2/2 page
    And the user adds CY12345678X on the second previous-oss-scheme-number/2 page
    Then the user answers no on the previous-scheme-answers/2 page
    And the user answers yes on the previous-schemes-overview page
    And the user selects Finland on the third previous-country page
    And the user picks oss on the previous-scheme/3/1 page
    And the user adds EU222456788 on the first previous-oss-scheme-number/3 page
    Then the user answers no on the previous-scheme-answers/3 page
    And the user answers no on the previous-schemes-overview page
    And the user is on the change-your-registration page
    And the user submits their amended registration
    Then the user is on the successful-amend page
    And all of the amended answers for current registration IM9007230003 are displayed on the confirmation

  Scenario: An IOSS registered user cannot amend sections of a previous registration that are not editable
    Given the user accesses the authority wizard
    And a user with VRN 100000001 and IOSS Number IM9007230000 accesses the amend registration journey
    Then the user is on the change-your-registration page
    When the user clicks on the View or change your previous registration link
    Then the user answers yes on the change-your-previous-registration?waypoints=change-your-registration page
    And the user is on the change-a-previous-registration page
    And the correct IOSS number IM9006230000 is displayed on the page
    When the user manually navigates to the have-uk-trading-name?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the add-uk-trading-name?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the uk-trading-name/1?waypoints=change-add-uk-trading-name%2Cchange-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the remove-uk-trading-name/1?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the previous-schemes-overview?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the previous-scheme-answers/1?waypoints=change-previous-schemes-overview%2Cchange-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the tax-in-eu?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the add-tax-details?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the check-tax-details/1?waypoints=change-add-tax-details%2Cchange-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the remove-tax-details/1?waypoints=change-add-tax-details%2Cchange-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the registration-tax-type/1?waypoints=check-tax-details-1%2Cchange-add-tax-details%2Cchange-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the add-website-address?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the website-address/1?waypoints=change-add-website-address%2Cchange-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the remove-website-address/1?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the remove-all-trading-names?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the remove-all-tax-details?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser
    When the user manually navigates to the remove-all-previous-registrations?waypoints=change-a-previous-registration page
    Then the user is redirected to the returns dashboard
    And the user clicks back on the browser




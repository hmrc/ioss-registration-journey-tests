@Registration

Feature: Registration journeys

  @ZAP @Accessibility
  Scenario: IOSS Registration journey for NI Trader
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers yes on the ni-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
    #    Temporary
    And the user is on the registration-service-error page
#    And the user is at the beginning of the signed in IOSS Registration journey

  Scenario: IOSS Registration journey for Norwegian Trader
    Given the user accesses the IOSS Registration service
    Then the user answers no on the ioss-registered page
    And the user answers yes on the selling-goods-outside-single-market page
    And the user answers yes on the goods-value page
    And the user answers yes on the registered-for-vat-in-uk page
    And the user answers no on the ni-based page
    And the user answers yes on the norway-based page
    And the user continues through the register-to-use-service page
    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
      #    Temporary
    And the user is on the registration-service-error page
#    Then the user signs in as an Organisation Admin with VAT enrolment 100000001
#    And the user is at the beginning of the signed in IOSS Registration journey


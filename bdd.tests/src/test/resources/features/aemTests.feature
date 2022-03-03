Feature: AEM Testing
  Scenario: User successfully logs in as Admin
    Given user navigates to login page
    When user logs in as admin
    Then user sees admin landing page

  Scenario: User successfully creates a new page object
    Given user is logged in as admin
    When user creates a new page object
    Then user sees new page
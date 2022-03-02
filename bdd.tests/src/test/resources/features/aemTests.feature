Feature: AEM Testing
  Scenario: User successfully logs in as Admin
    Given user navigates to login page
    When user logs in as admin
    Then user sees admin landing page
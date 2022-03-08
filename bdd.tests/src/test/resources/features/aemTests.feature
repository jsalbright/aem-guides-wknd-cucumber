Feature: AEM Testing
  Scenario: User successfully logs in as Admin
    Given user navigates to login page
    When user logs in as admin
    Then user sees admin landing page

  Scenario: User successfully crops asset
    Given user is logged in and on AssetsPage
    And searches for asset with keyword "asset"
    When user attempts to crop asset
    Then asset is cropped

  Scenario Outline: User successfully logs in
    Given user navigates to login page
    When user enters <UserName> in username field
    And user enters <Password> in password field
    Then user sees landing page
    Examples:
      | UserName  | Password  |
      | admin     | admin     |
      | user      | user      |
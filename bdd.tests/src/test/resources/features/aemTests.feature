Feature: AEM Testing
  @login
  Scenario: User successfully logs in as Admin
    Given user navigates to login page
    When user logs in as admin
    Then user sees admin landing page

  Scenario: User successfully crops asset
    Given user is logged in and on AssetsPage
    And searches for asset with keyword "asset"
    When user attempts to crop asset
    Then asset is cropped

#  @sitespage
  Scenario: User successfully authors page
    Given user is logged in and on SitesPage
    When user authors new content page
    Then user sees new content page

  @sitespage
  Scenario: User adds assets to content page
    Given user is logged in and on SitesPage
    When  user selects content page
    And user adds asset to content page
    Then  asset is visible on content page

#  Scenario Outline: User successfully logs in
#    Given user navigates to login page
#    When user enters <UserName> in username field
#    And user enters <Password> in password field
#    Then user sees landing page
#    Examples:
#      | UserName  | Password  |
#      | admin     | admin     |
#      | user      | user      |
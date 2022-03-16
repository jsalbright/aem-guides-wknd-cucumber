Feature: AEM Testing
  @login
  Scenario: User successfully logs in as Admin
    Given user navigates to login page
    When user logs in as admin
    Then user sees admin landing page

  @assetspage
  Scenario: User successfully crops asset
    Given user is logged in and on AssetsPage
    And searches for asset with keyword "asset"
    When user attempts to crop asset
    Then asset is cropped

  @sitespage
  Scenario: User successfully authors page
    Given user is logged in and on SitesPage
    When user authors new content page
    Then user sees new content page

  @contentpage
  Scenario: User adds assets to content page
    Given user is logged in and on SitesPage
    When  user opens content page
    And user adds asset to content page
    Then  asset is visible on content page
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
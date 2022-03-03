Feature: Arbitrary Page tests
  Scenario: Page contains title
    Given user visits some-site
    When page is rendered
    Then title is Hello World
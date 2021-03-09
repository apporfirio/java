Feature: Main menu navigation
  As a Selenium's website visitor
  I want to be able to successfully open any of its main menu pages
  So that I can get official information about Selenium

  Background:
    Given I open Selenium's website

  Scenario: Downloads page opens successfully
    When I select 'Downloads' on main menu
    Then I have access to all previous releases of Selenium

  Scenario: Projects page opens successfully
    When I select 'Projects' on main menu
    Then I can read about the following projects:
      | Selenium WebDriver  |
      | Selenium IDE        |
      | Selenium Grid       |

  Scenario: Documentation page opens successfully
    When I select 'Documentation' on main menu
    Then I can read how to install Selenium

  Scenario: Support page opens successfully
    When I select 'Support' on main menu
    Then I have access to the official user group
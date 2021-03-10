Feature: Main menu navigation
  As a Selenium's website visitor
  I want to be able to successfully open any of its main menu pages
  So that I can get official information about Selenium

  Background:
    Given I open Selenium's website

  Scenario: About page opens successfully
    When  I select 'About' option under 'About'
    Then I can read Selenium's history

  Scenario: Governance page opens successfully
    When I select 'Governance' option under 'About'
    Then I can know Selenium's governance modal and philosophy

  Scenario: Events page opens successfully
    When I select 'Events' option under 'About'
    Then I can find about Selenium's meetups around the world

  Scenario: Downloads page opens successfully
    When I select 'Downloads' option
    Then I have access to all previous releases of Selenium

  Scenario: Projects page opens successfully
    When I select 'Projects' option
    Then I can read about the following projects:
      | Selenium WebDriver  |
      | Selenium IDE        |
      | Selenium Grid       |

  Scenario: Documentation page opens successfully
    When I select 'Documentation' option
    Then I can read how to install Selenium

  Scenario: Support page opens successfully
    When I select 'Support' option
    Then I have access to the official user group

  Scenario: Blog page opens successfully
    When I select 'Blog' option
    Then I can find posts related to the following categories:
      | releases    |
      | conference  |
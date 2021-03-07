Feature: Main menu navigation
  As a Selenium's website visitor
  I want to be able to successfully open any of its main menu pages
  So that I can get whatever official information about it that I need

  Background:
    Given I open Selenium's website

  Scenario: can open Downloads page
    When I select 'Downloads' menu option
    Then I go to the right page
@ui
Feature: Calculator feature
  As a user,
  I should be able
  to use the calculator,
  de that I can do
  arithmetic operations.
@addition
  Scenario: Add 2 numbers
    Given calculator app is open
    When I add 2 with 2
    Then I should get result 41 displayed
@smoke
  Scenario: Add 2 numbers another example
    Given calculator app is open
    When I add 5 with 4
    Then I should get result 9 displayed

  Scenario: Add 2 numbers anoher example2
    Given calculator app is open
    When  I add 55 with 44
    Then I should get result 99 displayed
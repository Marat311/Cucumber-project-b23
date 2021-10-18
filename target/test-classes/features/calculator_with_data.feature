@calculator @non_ui
Feature: Calculator feature with data
  As a user,
  I should be able to use the calculator,
  so that I can do arithmetic operations.

  ## below line is how we add tag to a feature at scenario level
 @wip
   #scenario outline is used to run some scenario against multiple different set of data
   # tha data is provided under
  Scenario Outline:  Add 2 numbers multiple example
    Given calculator app is open
    When I add <num1> with <num2>
    Then I should get result <expectedResult> displayed
  Examples:
    | num1 | num2 | expectedResult |
    | 3    | 4    | 7              |
    | 4    | 7    | 11             |
    | 6    | 11   | 17             |



 # Scenario: Add 2 numbers
  #  Given calculator app is open
  #  When I add 2 with 2
 #   Then I should get result 4 displayed

 # Scenario: Add 2 numbers
 #   Given calculator app is open
 #   When I add 3 with 2
 #   Then I should get result 5 displayed

 # Scenario: Add 2 numbers
 #   Given calculator app is open
#    When I add 21 with 20
  #  Then I should get result 41 displayed
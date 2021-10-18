Feature: Eating too many cucumbers may not be good for you
  Eating too much of everything may not be good fro you

  Scenario: Eating a few is no problem
    Given John is hungry
    When He eats 3 cucumbers
    Then He will be full

  Scenario: Eating too many is problem
    Given Ivan is hungry
    When He eats 10 cucumbers
    Then He faints
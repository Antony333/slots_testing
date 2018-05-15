#Feature: Spin slot
#
#  Scenario: UI cannot be interacted while spin in progress
#    Given I am in slot screen
#    When I spin slot
#    Then I should see UI not interactable
Feature: Spin slot

  Scenario: UI cannot be interacted while spin in progress
    Given I am in slot screen
#    When I spin slot
    Then I should see UI not interactable
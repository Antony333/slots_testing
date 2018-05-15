Feature: Spin slot

  Scenario: UI cannot be interacted while spin in progress
    Given I am in slot screen
    When I spin slot
    Then I should see not interactable elements while spinning
    And I should see interactable elements when spin has come to a stop
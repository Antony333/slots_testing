Feature: Spin slot

  Scenario: UI cannot be interacted while spin in progress
    Given I play as guest
    When I choose all slots

    When I choose slot 1.png
      And I choose bet Bet_10000.png
      And I am in slot screen
    When I spin slot
    Then I should see not interactable elements while spinning
      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 2.png
      And I choose bet Bet_10000.png
      And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 3.png
      And I choose bet Bet_10800.png
      And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 69.png
      And I choose bet Bet_24000.png
      And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 68.png
      And I choose bet Bet_5000.png
      And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I want to swipe screen

    When I choose slot 4.png
    And I choose bet Bet_5000.png
    And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 5.png
    And I choose bet Bet_5000.png
    And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 6.png
    And I choose bet Bet_5000.png
    And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 7.png
    And I choose bet Bet_5000.png
    And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen

    When I choose slot 8.png
    And I choose bet Bet_5000.png
    And I am in slot screen
#    When I spin slot
#    Then I should see not interactable elements while spinning
#      And I should see interactable elements when spin has come to a stop
    When I want to back on the previous screen
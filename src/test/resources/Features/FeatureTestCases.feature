Feature: Test scenarios

@add_things_tobet
Scenario: User add events to bet slip
	Given User has navigated to website
	When User selects the "Cricket"
	Then User click on bet for any event
	Then User removes the bet by clicking on the same event bet

@remove_bets_by_click_on_same_event
Scenario: user deletes the bet by clicking on same event bet
	Given User has navigated to website
	When User selects the "Cricket"
	Then User click on bet for any event
	Then User removes the bet by clicking on the same event bet

@remove_bets_by_click_on_delete_bet
Scenario: user deletes the bet by clicking on delete bet button
	Given User has navigated to website
	When User selects the "Cricket"
	Then User click on bet for any event
	Then User removes bet by removing the delete button

@change_odds_format
Scenario: User changes the odds format
	Given User has navigated to website
	When User selects the "Cricket"
	When User change Odds format

@change_odds_format_and_add_event_bet
Scenario: User changes the odds format
	Given User has navigated to website
	When User selects the "Cricket"
	When User change Odds format
	Then User click on bet for any event



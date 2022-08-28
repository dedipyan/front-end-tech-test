# Frontend Technical Test

Welcome to The Stars Group technical test for frontend test engineers!

These tasks are open ended but we recommend that you spend no more than a few hours on them.

The site we would like you to test can be found at: https://www.pokerstarssports.uk. Please bear in mind that the site is highly dynamic and both the content and the layout may change over time. You will not need to register an account or log in to the website to complete this test.

### Task 1
Write a number of BDD scenarios using Gherkin to test the following features:
- Adding things to, and removing things from, the bet slip
    1. Navigate to any sport event and add any live event bet
    2. Add and verify multiple events bets to the bet slip
    3. Remove any event bet by clicking on the same bet(where user added the event bet to bet slip)
    4. Remove any event bet by clicking on the delete button in the respective event in bet slip
    5. Verify the functionality by adding event bet to bet slip and refresh the page
    6. verify multiple event bets can be added to the bet slip
- Changing the odds format
    1. User changes the Odds format for any event
    2. User verifies the bet value of added event bet after change of the odds format
    3. user changes the odds format and navigate to other events
    4. User changes the odds format and delete the existing bets in bet slip

### Task 2
Create a simple test framework using NPM, Cypress and Cucumber to automate a selection of the scenarios you have created. You should include at least two scenarios from each feature.
- https://www.npmjs.com
- https://www.cypress.io
- https://www.npmjs.com/package/cypress-cucumber-preprocessor


Setup: 
1. Install java version on or above 8.0 in the system
2. Use any IDE of user choice
3. Import the project with the specified pom.xml
4. under the src/test/resources/Features, user can find different scenarios in the features files
5. Take any scenario tag and keep in the RunTests.java under src/test/java/runner
6. Run the RunTests.java file with Junit runner
7. Screenshots folder is placed under the root directory of project and Screenshots will be taken for each and every step to ease of debugging
8. User can able to run the tests in Windows or Mac machines and the drivers files are kept in the drivers folder


After completing the tasks, please update the README.md file with your scenarios from Task 1 and instructions on how to run your tests, include any information you think is relevant, interesting or useful. The preferred delivery method for this project is via Github but we will also accept a zipped file sent as an email attachment.

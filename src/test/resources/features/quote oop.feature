@Day13 @quote_oop
Feature: Quote OOP

@quote1
  Scenario: Required fields for quote oop
  #verified 20200919
    Given I open "quote" page oop
    #And I wait for 3 sec
    When I fill out required fields for "user" oop
    And I submit the form oop
    Then I verify required fields for "user" oop

  @quote2
  Scenario: Required more fields for quote oop
    Given I open "quote" page oop
    When I fill out required fields for "user" oop
    When I fill out optional fields for "user" oop
    And I wait for 2 sec
    And I submit the form oop
    Then I verify required fields for "user" oop
    Then I verify optional fields for "user" oop

  @quote3
  Scenario: Required fields test oop
    #verified 20200919
    Given I open "quote" page oop
    And I submit the form oop
    Then I see "username" error message "This field is required."
    And I see "email" error message "This field is required."
    And I see "password" error message "This field is required."
    And I see "name" error message "This field is required."
    And I see "agreedToPrivacyPolicy" error message "- Must check!"

  @quote4
  Scenario: Market username test oop
    # implemented 20200919 using Slava's code as a clue
    Given I open "quote" page oop
    When I fill out "username" field with "abc"
    And I submit the form oop
    Then I don't see "username" error message
    When I fill out "username" field with "a"
    Then I see "username" error message "Please enter at least 2 characters."
    #And I wait for 2 sec
    When I fill out "username" field with "me"
    Then I don't see "username" error message

  @quote5
  Scenario: Market email test oop
    # implemented 20200919
    Given I open "quote" page oop
    When I fill out "email" field with "john"
    And I submit the form oop
    Then I see "email" error message "Please enter a valid email address."
    When I fill out "email" field with "john@example.com"
    Then I don't see "email" error message
  @quote6
  Scenario: Market passwords test oop
    #implemented 20200919
    Given I open "quote" page oop
    When I fill out "password" field with "1234"
    And I submit the form oop
    Then I see "password" error message "Please enter at least 5 characters."
    And I wait for 2 sec
    When I fill out "password" field with "12345"
    And I wait for 2 sec
    Then I don't see "password" error message
    And I wait for 2 sec
    When I fill out "confirmPassword" field with "1234"
    And I wait for 2 sec
    And I submit the form oop
    And I wait for 2 sec
    Then I see "confirmPassword" error message "Please enter at least 5 characters."
    When I fill out "confirmPassword" field with "54321"
    Then I see "confirmPassword" error message "Passwords do not match!"
    When I fill out "confirmPassword" field with "12345"
    Then I don't see "confirmPassword" error message
  @quote7
  Scenario: Market name test oop
    #implemented 20200920
    Given I open "quote" page oop
    When I fill out name field with first name "John" and last name "Doe"
    And I wait for 2 sec
    Then I verify "name" field value "John Doe"
    When I fill out name field with first name "John", middle name "Richard", last name "Doe"
    And I wait for 2 sec
    Then I verify "name" field value "John Richard Doe"


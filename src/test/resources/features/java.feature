@java

  Feature: Java exercises

    Scenario: step accepts two veriables
      Given I perform actions with "myvar" and "my"



    Scenario: Simple output
      Given I input "Pete"



    Scenario: combined
      Given Is a given word "Anna" polyndrome?
      And Is a given word "Mike" polyndrome?
      And Is a given word "Pop" polyndrome?
      And Does given string "my    string" contain "  "
      And Some string "my    ___string.." contains certain consecutive chars
      And I return the max value of a given "1,32,47,9,71,6" array


    Scenario: Java number exercises
      Given I have two integer variables 7 and 19 for calculations


    Scenario: Java boolean exercises
      Given compare two colors "gr(a|e)y" and "gray"
      And compare two colors "yellow" and "yellow"


    Scenario: Java if expression
      Given I jump to "GOOgle" start page


    Scenario Outline: If expressions in Java
      Given I print if number <number> positive or negative
      And I print the <day> -th day of week
    Examples:
      |number|day|
      |35    |3  |
      |0     |7  |
      |-2    |1  |


    Scenario: Java array exercises
      Given I print array


    Scenario: Java dynamic array exercises
      Given I work with a dynamic array
@day5
    Scenario: odd and even divisible by 5 and 3
      Given I return true or false if 54 divisible by three or five


@team1
    Scenario: stock market
      Given market prices


@day6
    Scenario: swap names
      Given I swap name "John" and lastname "George"

@day6
    Scenario: webDriver exercise1
      Given I go to "quote" page
      And I print page details


@day6
    Scenario: webDriver exercise2
      Given I go to "quote" page
      And I wait for 2 sec
      And I go to "yahoo" page
      And I wait for 2 sec
      And I go to previous page
      And I wait for 2 sec
      And I go to next page
      And I wait for 2 sec
      And I navigate to "google" page
      And I wait for 2 sec

@day6
  Scenario: webDriver exercise3
    Given I go to "quote" page
    And I change screen resolution to "phone"
    And I change screen resolution to "tablet"


@day6
  Scenario: webDriver exercise 4 printing to input fields and verify submitted form
  Given I go to "quote" page
  Then I verify that page title is "Get a Quote"
  When I print "John Doe" into "userName" input field
  And I click on element with xpath "//input[@id='name']"
  And I print "John" into "firstName" input field
  And I print "Doe" into "lastName" input field
  And I click on element with xpath "//span[@class='ui-button-text'][text()='Save']"
  And I print "jdoe@example.org" into "email" input field
  And I print "jd123456" into "password" input field
  And I print "jd123456" into "confirmPassword" input field
  And I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
  And I submit the page
  #And I wait for 2 sec
  Then I verify that page title is "Get a Quote"
  And I verify that "password" field contains text "jd123456"
  And I verify that "email" field contains text "jdoe@example.org"
  And I verify that Return Button is visible
  #And I verify that "" field is not visible


@day6
    Scenario: webDriver exercise 5 email field behavior
    # not implemented properly.
      Given I go to "quote" page
      When I print "jd@example.org" into "email" input field
      And I wait for 2 sec
      And I clear "email" input field
      And I print "jdoeexample.org" into "email" input field
      And I click on element with xpath "//input[@name='username']"
      And I wait for 2 sec
      And I delete 2 -th character from "email" input field
      #field "email" contains no text yet to work with
      And I clear "email" input field
      And I print "jdoe@example.org" into "email" input field
      And I wait for 2 sec

@day6
  Scenario: alert handling
    Given I go to "quote" page
    When I click on element with xpath "//button[@id='thirdPartyButton']"
    Then I accept an alert
    And I verify that "thirdParty" field contains text "You accepted"

@team1
  Scenario: removeall vowels
    Given I remove vowels from text "awefkqeweqtareuymvdisjewi"

@Day7
  Scenario: swap array elements
    Given I swap an array elements 3 and 5


@Day7
  Scenario: divisible by
    Given I input integer 36 and check if it is devisible


@Day8
  Scenario: all positive integers
    Given I print all positive integers

@Day8
  Scenario: min and max integers
    Given I print all integers

@Day8
  Scenario: all numbers up to n
    Given I print all numbers from to 30


@Day8
  Scenario: all numbers -n to n
    Given I print all numbers plus negative -18


@Day8
  Scenario: I print all integer array
    Given I print all integer array


@Day8
    Scenario: even numbers from integer array
      Given I print all even numbers from integer array

@Day8
  Scenario: check if array is empty
    Given I check if array is empty

@Day8
  Scenario: numbers to n divisible
    Given I print modified int array up to 48

@Day10
  Scenario: reverse every 3-rd character of the string
  #implemented 20200829
    Given String "My favorite string" to reverse


@Day10
  Scenario: Write a java program to add 2 numbers and it will print addition.
    Given I input two numbers for adding

@Day10
  Scenario: User inputs two numbers which are divided by 5
    Given 25 and 80 print ranges

@Day10
  Scenario: Speak like Yoda - reverse words in a sentence (Example: "I am Automation Engineer" => "Engineer Automation am I") (hint - method .split(" "))
  #implemented 20200829
  Given "A brilliant example for a sentence to be reversed" sentence to reverse


@Day11
#Write a function that finds if word is palindrome
#Write a function that counts number of each character in a string

  Scenario: 2 max numbers in an array
  #implemented 20200902
    Given find two max numbers in array "2,34,23,-52,-79,54,33,34,12,-54,54,54,34"


@Day11
  Scenario: if array contains duplicates
    #implemented 20200902
    Given find duplicates in array "yes,yep,ya,no,not,nop,nor,non,no,ya"
    #And find duplicates in str array#"2,34,23,-52,-79,54,33,34,12,-54,54,54,34"

@Day11
  Scenario: function that counts number of each character in a string
    Given string to analyse "baobab"


#@assesment
#  Scenario: assesment
#    Given
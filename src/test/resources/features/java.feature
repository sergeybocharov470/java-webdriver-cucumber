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
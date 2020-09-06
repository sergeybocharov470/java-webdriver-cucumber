@day9
Feature: Site: http://www.calculator.net/

@Day9
  Scenario: Verify calculator result
    #implemented 20200831
    Given I go to "calculator" page
    When I navigate to "Auto Loan Calculator"
    And I clear all calculator fields
    And I calculate
    #And I wait for 3 sec
    Then I verify "Please provide a positive auto price." calculator error
    And I enter "25000" price, "60" months, "4.5" interest, "5000" downpayment, "0" trade-in, "California" state, "7" percent tax, "300" fees
    And I calculate
    Then I verify monthly pay is "$372.86"


@Day11
  Scenario Outline: Verify calculator results outline
    #implemented 20200831
    Given I go to "calculator" page
    When I navigate to "Auto Loan Calculator"
    And I clear all calculator fields
    And I enter <price>, <months>, <interest>, <downpayment>, <tradein>, <state>, <tax>, <fees>
    And I calculate
    Then I verify monthly pay is <monthlypay>
  Examples:
      | price | months | interest | downpayment | tradein | state      | tax   | fees | monthlypay |
      | "25000" | "48"     | "3.8"      | "6000"        | "0"        | "Oklahoma"   | "8.625" | "135"  | "427.30"         |
      | "34000" | "12"     | "2.275"    | "12000"       | "5000"     | "California" | "7.125" | "330"  | "1,434.18"         |
      | "12000" | "30"     | "4.5"      | "1000"        | "1500"     | "Georgia"    | "7"     | "240"  | "335.41"         |


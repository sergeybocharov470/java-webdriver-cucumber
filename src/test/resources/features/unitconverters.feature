@unitconverters
  Feature: Unit converters
    exercises with various unit converters

@day9
  Scenario: area conversion
    Given I go to "UnitConverters" page
    When I switch to "Area" tab
    And I set From option to "Square Meter"
    And I set From value to "37.1"
    Then I set To option to "Square Foot" and verify To value "399.34"


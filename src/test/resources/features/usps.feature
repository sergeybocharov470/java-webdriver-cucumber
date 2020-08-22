@usps
  Feature: USPS tests

    #8/19/20 feature for USPS training exercise

    Scenario Outline: ZIP by address
      Given I open USPS website
      Then I should see page title equals "Welcome | USPS"
      When I put mouse at "Quick Tools"
      And I click on element "Look Up a ZIPCode"
      Then I shoud see page title containing "ZIP Code"
      When I click on item "Find by address"
      Then I shoud see page with label "ZIP Code by Address"
      When I print <streetAddress> into input "Street adderss" field
      And I print <city> into input "city" field
      And I select <state> from "state" list
      And I click on element "Find"
      Then I shoud see page with label ""
      And I Verify that element "" contains text <zip>
    Examples:
      |streetAddress|city|state|zip|


    @usps1
    Scenario: Validate ZIP code for Portnov Computer School
      Given I go to "usps" page
      When I go to Lookup ZIP page by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
      Then I validate "94022" zip code exists in the result


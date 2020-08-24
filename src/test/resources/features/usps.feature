@usps
  Feature: USPS tests

    #8/19/20 feature for USPS training exercise
@Day7
    Scenario Outline: ZIP by address
    not implemented yet 20200823
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



@Day7
    Scenario: Validate ZIP code for Portnov Computer School
    not implemented yet 20200823
      Given I go to "usps" page
      When I go to Lookup ZIP page by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
      Then I validate "94022" zip code exists in the result


@Day8
    Scenario: Calculate price
      Given I go to "usps" page
      When I go to Calculate Price Page
      And I select "Canada" with "Postcard" shape
      And I define "2" quantity
      Then I calculate the price and validate cost is "$2.40"


@Day8
    Scenario: Verify location
      Given I go to "usps" page
      When I perform "Free Boxes" search
      And I set "Mail & Ship" in filters
      Then I verify that "7" results found
      When I select "Priority Mail | USPS" in results
      And I click "Ship Now" button
      Then I validate that Sign In is required


@Day8
    Scenario: Quadcopters delivery
    not implemented yet 20200823
      Given I go to "usps" page
      When I go to "Help" tab
      And I perform "Quadcopters delivery" help search
      Then I verify that no results of "Quadcopters delivery" available in help search


@Day8
    Scenario: Phone number of the nearest Mail Pickup
    not implemented yet 20200823
      Given I go to "usps" page
      When I navigate to Find a Location page
      And I filter by "Post Offices" location types, "Pickup Services" services, "Accountable Mail" available services
      And I provide data as "4970 El Camino Real 110" street, "Los Altos" city, "CA" state
      Then I verify phone number is "800-275-8777"
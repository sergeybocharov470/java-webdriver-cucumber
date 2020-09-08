@usps
  Feature: USPS tests

    #8/19/20 feature for USPS training exercise
@Day7
#    Scenario Outline: ZIP by address
#    not implemented yet 20200823
#      Given I open USPS website
#      Then I should see page title equals "Welcome | USPS"
#      When I put mouse at "Quick Tools"
#      And I click on element "Look Up a ZIPCode"
#      Then I should see page title containing "ZIP Code"
#      When I click on item "Find by address"
#      Then I should see page with label "ZIP Code by Address"
#      When I print <streetAddress> into input "Street address" field
#      And I print <city> into input "city" field
#      And I select <state> from "state" list
#      And I click on element "Find"
#      Then I should see page with label ""
#      And I Verify that element "" contains text <zip>
#    Examples:
#      |streetAddress|city|state|zip|



@Day7
    Scenario: Validate ZIP code for Portnov Computer School
    #implemented 20200904
    #RESTORED
      Given I go to "usps" page
      When I go to Lookup ZIP page by address
      And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
      And I validate "94022" zip code exists in the result



@Day8
    Scenario: Calculate price
    #verified running 20200905
    #RESTORED
      Given I go to "usps" page
      When I go to Calculate Price Page
      And I select "Canada" with "Postcard" shape
      And I define "2" quantity
      Then I calculate the price and validate cost is "$2.40"


@Day8
    Scenario: Verify location
      # verified running 20200905  using "spinner" explicit wait
      #RESTORED
      Given I go to "usps" page
      When I perform "Free Boxes" search
      And I set "Mail & Ship" in filters
      Then I verify that "7" results found
      When I select "Priority Mail | USPS" in results
      And I click "Ship Now" button
      Then I validate that Sign In is required


@Day8
    Scenario: Quadcopters delivery
    # implemented  20200905, sucessfully updated 20200906
    #RESTORED
      Given I go to "usps" page
      When I go to "Help" tab and "FAQs" item
    # pay attention to implementation of the step definition below. Utilisation of a 'spinner' element.
      And I enter "Quadcopters delivery" into "Help" search
      Then I verify that no results of "Quadcopters delivery" available in "Help" search


@Day8
    Scenario: Phone number of the nearest Mail Pickup
    # implemented 20200905
    #RESTORED
      Given I go to "usps" page
      When I navigate to Find a Location page
      And I filter by "Post Offices" location types, "Pickup Services" services, "Accountable Mail" available services
      And I provide data as "4970 El Camino Real 110" street, "Los Altos" city, "CA" state
      Then I verify phone number is "408-747-0340"
      #And I wait for 3 sec
  

@Day9
  Scenario: Every door direct mail
  #implemented 20200905
  #final step needs to be reviewed
  Given I go to "usps" page
    When I go to "Every Door Direct Mail" under "Business"
    And I search for "4970 El Camino Real, Los Altos, CA 94022"
    And I click "Show Table" on the map
    And I wait for 3 sec
    When I click "Select All" on the table
    And I close modal window
    Then I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary



@Day12, @usps3
Scenario: Wrong store id does not match
  #sucessfully implemented  20200906
  #RESTORED
  Given I go to "usps" page
  When I go to "Postal Store" tab and "Search" item
  And I enter "12345" into "Store" search
  Then I verify that no results of "12345" available in "Store" search


@Day12, @usps4
    Scenario: One item found
    #implemented 20200906, updated and verified 20200907
    # RESTORED   but with incorrect XPATH for "Priority Mail" filter "checkbox"
      Given I go to "usps" page
      When I go to "Postal Store" tab and "Stamps" item
      And I choose "select" "Mail service" "Priority Mail" filter "checkbox"
      Then I verify 1 items found in "resultset"




@Day12, @usps5
    Scenario: Verify color
    #implemented besides the final step due to RegExp lack
      Given I go to "usps" page
      When I go to "Postal Store" tab and "Stamps" item
      When I choose "unselect" "Category" "Stamps" filter "checkbox"
      And I choose "select" "Stamp Shape" "Vertical" filter "checkbox"
      And I choose "select" "Color" "Blue" filter "checkbox"
      Then I verify "Blue" filter "set"
      And I verify "Vertical" filter "set"
      Then I verify 12 items found in "resultset"
      And I verify that items below 12 dollars exists in "resultset"


@Day12, @usps6
    Scenario: Verify "Passport Renewal" service
    #implemented 20200907
    #RESTORED
      Given I go to "usps" page
      When I perform "Passports" search
      And I select "Passport Application" in results
      And I click "Schedule an Appointment" button
      And I wait for 5 sec
      And I verify that "Passport Renewal" in "service" exists


@Day12, @usps7
    Scenario: PO Box
      Given I go to "usps" page
      When I go to "PO Boxes" under "Track & Manage"
      And I reserve new PO box for "94022"
      Then I verify that "Los Altos — Post Office™" in "office" exists
      And I verify that "Size 5-XL PO Box availability" in "Los Altos — Post Office™" exists
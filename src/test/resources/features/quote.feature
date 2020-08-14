#@predefined
  Feature: testing QUOTE page

  @predefined    #Test case 1. verified
    Scenario Outline: Responsive: desktop
      "Application", "date", "time", "location" web elements should be visible on a big landscape screen
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I resize window to 1366 and 800
      Then element with xpath <elem_big> should be displayed
    Examples:
      |elem_big|
      |"//b[@id='personalInfo']/.."|
      |"//b[@id='location']/.."|
      |"//b[@id='currentDate']/.."|
      |"//b[@id='currentTime']/.."|

  @predefined    #Test case 1. verified
    Scenario Outline: Responsive: phone
    web elements "date", "time", "location" should be hidden on a small phone portrait screen
    web element "Application" should be visible
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I resize window to 600 and 800
      Then element with xpath "//b[@id='personalInfo']/.." should be displayed
      And element with xpath <elem_small> should not be displayed
      Examples:
        |elem_small|
        |"//b[@id='location']/.."|
        |"//b[@id='currentDate']/.."|
        |"//b[@id='currentTime']/.."|


    #Scenario Outline: Responsive: phone
    #all web elements "Application", "date", "time", "location" should be hidden on a
    #  Given I open url "https://skryabin.com/market/quote.html"
    #  And I maximize window
    #  Then element with xpath "//legend[text()='Get a Quote']" should be displayed
    #  When I resize window to 320 and 600
    #  Then element with xpath <elem_small> should not be displayed
    #  Examples:
    #    |elem_small|
    #    |"//b[@id='personalInfo']/.."|
        #|"//b[@id='location']/.."|
        #|"//b[@id='currentDate']/.."|
        #|"//b[@id='currentTime']/.."|
  
  @predefined      #Test case 2. verified
  Scenario: “Username” field length - boundaries
    If "username" length is less than 2 characters an error message appears
    If "username" length is equal to 2 characters no error message appears
    Given I open url "https://skryabin.com/market/quote.html"
    And I maximize window
    Then element with xpath "//legend[text()='Get a Quote']" should be displayed
    When I type "g" into element with xpath "//input[@name='username']"
    And I click on element with xpath "//input[@id='password']"
    And I wait for 2 sec
    Then element with xpath "//label[@id='username-error']" should be displayed
    When I type "o" into element with xpath "//input[@name='username']"
    And I click on element with xpath "//input[@id='password']"
    Then element with xpath "//label[@id='username-error']" should not be displayed

   @predefined  #failed. org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: {"method":"xpath","selector":"//label[@id='username-error']"}
                 #Test case 2
   Scenario: “Username” field length - equivalent partition
    If "username" length is more than 2 characters no error message appears
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I type "game" into element with xpath "//input[@name='username']"
      And I click on element with xpath "//input[@id='password']"
      Then element with xpath "//label[@id='username-error']" should not be present


    @predefined     #Test case 3. verified
    Scenario Outline: email field accepts only valid email
    invalid and valid email addresses are inputed to switch err.message on and off respectively
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I type <invalid_email> into element with xpath "//input[@name='email']"
      And I click on element with xpath "//input[@id='password']"
      And I wait for 1 sec
      Then element with xpath "//label[@id='email-error']" should be displayed
      When I clear element with xpath "//input[@name='email']"
      And I type <valid_email> into element with xpath "//input[@name='email']"
      And I click on element with xpath "//input[@id='password']"
      And I wait for 1 sec
      Then element with xpath "//label[@id='email-error']" should not be displayed
      Examples:
      |invalid_email|valid_email|
      |"a@a."       |"a@a"      |
      |"@s.net"     |"q@s.net"  |
      |"we@"        |"we@ew1"   |
      |"t@.z"       |"1w@22d.2" |

  @predefined     #verified#Test case 4. verified
    Scenario Outline: Password and Confirm Password dependency
    Confirm Password is disabled if Password field is empty
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      And element with xpath "//input[@id='confirmPassword']" should be disabled
      When I type <pw> into element with xpath "//input[@id='password']"
      Then element with xpath "//input[@id='confirmPassword']" should be enabled
    Examples:
      |pw|
      |"wrsacdfsaf"|
      |"a"         |
      |" "         |



  @predefined     #Test case 5. verified
    Scenario: Name field behavior 1
    upon clicking inside of Name field popup dialog appears
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I click on element with xpath "//input[@id='name']"
      Then element with xpath "//input[@id='firstName']" should be displayed

  @predefined     #Test case 5. verified
    Scenario: Name field behavior 2
    verify that after putting First Name, Middle Name, Last Name, it concatenates it
    correctly and puts the value in the Name field
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I click on element with xpath "//input[@id='name']"
      And I wait for element with xpath "//input[@id='firstName']" to be present
      And I type "John" into element with xpath "//input[@id='firstName']"
      And I type "Doe" into element with xpath "//input[@id='lastName']"
      And I type "F" into element with xpath "//input[@id='middleName']"
      And I click on element with xpath "//span[@class='ui-button-text'][text()='Save']/.."
      And I wait for 2 sec
      Then element with xpath "//input[@id='name']" should have attribute "value" as "John F Doe"


    @predefined     #Test case 6.  Verified
    Scenario: Accepting Privacy Policy
    Validate that Accepting Privacy Policy is required to submit the form
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      When I click on element with xpath "//button[@id='formSubmit']"
      Then element with xpath "//label[@id='agreedToPrivacyPolicy-error']" should be displayed
      When I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
      Then element with xpath "//label[@id='agreedToPrivacyPolicy-error']" should not be displayed


    @predefined     #Test cases 7-8.   verified
    Scenario: Submitting form
    submit the form and verify input
      Given I open url "https://skryabin.com/market/quote.html"
      And I maximize window
      Then element with xpath "//legend[text()='Get a Quote']" should be displayed
      #Name input
      When I click on element with xpath "//input[@id='name']"
      And I wait for element with xpath "//input[@id='firstName']" to be present
      And I type "John" into element with xpath "//input[@id='firstName']"
      And I type "Doe" into element with xpath "//input[@id='lastName']"
      And I type "F" into element with xpath "//input[@id='middleName']"
      And I click on element with xpath "//span[@class='ui-button-text'][text()='Save']/.."
      #Country of origin select
      And I click on element with xpath "//select[@name='countryOfOrigin']/option[@value='Belarus']"
      #Adderss input
      And I type "4610 Main St Baltimore MD" into element with xpath "//textarea[@id='address']"
      #Username
      And I type "game@game" into element with xpath "//input[@name='username']"
      #Password and Confirmation
      And I type "myPassword" into element with xpath "//input[@id='password']"
      Then element with xpath "//input[@id='confirmPassword']" should be enabled
      When I type "myPassword" into element with xpath "//input[@id='confirmPassword']"
      #email
      And I type "a@a" into element with xpath "//input[@name='email']"
      #phonenumber
      And I type "8552333222" into element with xpath "//input[@name='phone']"
      #DOB
      And I type "07/19/1980" into element with xpath "//input[@id='dateOfBirth']"
      #gender   Select method should be used instead of Click
      And I click on element with xpath "//input[@name='gender'][@value='male']"
      #carmake Select method should be used here instead of Click
      And I click on element with xpath "//select[@name='carMake']//option[@value='Ford']"
      #privacy policy
      And I click on element with xpath "//input[@name='agreedToPrivacyPolicy']"
      #form submit
      And I click on element with xpath "//button[@id='formSubmit']"
      #verification of input
      Then element with xpath "//legend[@class='applicationResult']" should be displayed
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='carMake']" should contain text "Ford"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='phone']" should contain text "8552333222"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='email']" should contain text "a@a"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='name']" should contain text "John F Doe"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='dateOfBirth']" should contain text "07/19/1980"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='address']" should contain text "4610 Main"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='dateOfBirth']" should contain text "07/19/1980"
      And element with xpath "//div[@class='well form-container container-fluid']//b[@name='password']" should contain text "[entered]"
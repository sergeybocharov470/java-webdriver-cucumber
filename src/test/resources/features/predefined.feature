@predefined
Feature: Search engine steps

  @predefined1
  Scenario: Predefined steps for Google
    Given I open url "https://google.com"
    Then I should see page title as "Google"
    Then element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "(//input[@name='btnK'])[1]"
    Then I wait for element with xpath "//*[@id='res']" to be present
    Then element with xpath "//*[@id='res']" should contain text "Cucumber"

  @predefined2
  #verified
  Scenario: Predefined steps for Yahoo
    Given I open url "https://www.yahoo.com"
    Then I should see page title as "Yahoo"
    And element with xpath "//input[@id='header-search-input']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='header-search-input']"
    Then I click on element with xpath "//button[@id='header-desktop-search-button']"
    And I wait for element with xpath "//div[@id='web']" to be present
    And element with xpath "//div[@id='web']" should contain text "Cucumber"

  @predefined3
  #verified
  Scenario: Predefined steps for Bing
    Given I open url "https://www.bing.com"
    Then I should see page title as "Bing"
    And element with xpath "//input[@id='sb_form_q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='sb_form_q']"
    Then I click on element using JavaScript with xpath "//input[@id='sb_form_go']"
    And I wait for element with xpath "//ol[@id='b_results']" to be present
    And element with xpath "//ol[@id='b_results']" should contain text "Cucumber"

  @predefined4
  #verified
  Scenario: Predefined steps for GIBIRU
    Given I open url "http://gibiru.com"
    Then I should see page title contains "Gibiru"
    And element with xpath "//input[@id='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='q']"
    Then I click on element using JavaScript with xpath "//button[@id='button-addon2']"
    And I wait for element with xpath "(//div[@class='gsc-webResult gsc-result'])[1]/.." to be present
    And element with xpath "(//div[@class='gsc-webResult gsc-result'])[1]/.." should contain text "Cucumber"

  @predefined5    #VERIFIED
  Scenario: Predefined steps for duckduckgo
    Given I open url "https://duckduckgo.com"
    Then I should see page title contains "DuckDuckGo"
    And element with xpath "//input[@id='search_form_input_homepage']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='search_form_input_homepage']"
    Then I click on element using JavaScript with xpath "//input[@id='search_button_homepage']"
    And I wait for element with xpath "//div[@class='results--main']" to be present
    And element with xpath "//div[@class='results--main']" should contain text "Cucumber"

  @predefined6     #verified
  Scenario: Predefined steps for SWISSCOWS
    Given I open url "https://swisscows.com"
    Then I should see page title contains "Swisscows"
    And element with xpath "//input[@name='query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='query']"
    Then I click on element with xpath "//button[@class='search-submit']"
    And I wait for element with xpath "//div[@class='web-results']" to be present
    And element with xpath "//div[@class='web-results']" should contain text "Cucumber"
    #exclude results from ads
    And element with xpath "//div[@class='banners-wrap']" should not contain text "Cucumber"


  @predefined7    #verified
  Scenario: Predefined steps for Searchencrypt
    Given I open url "https://www.searchencrypt.com"
    Then I should see page title contains "Search Encrypt"
    And element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element with xpath "//button[@class='search-bar__submit']"
    And I wait for element with xpath "//section[@class='serp__results container']" to be present
    #And I wait for 2 sec
    And element with xpath "//section[@class='serp__results container']" should contain text "BDD"


  @predefined8       #verified
  Scenario: Predefined steps for Startpage
    Given I open url "https://www.startpage.com"
    Then I should see page title contains "Startpage.com"
    And element with xpath "//input[@id='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='q']"
    Then I click on element with xpath "//button[@class='search-form-home__form__button']"
    And I wait for element with xpath "//section[@class='w-gl w-gl--default']" to be present
    And I wait for 2 sec
    And element with xpath "//div[@class='show-results']" should contain text "Cucumber"


  @predefined9     #verified
  Scenario: Predefined steps for YANDEX
    Given I open url "https://www.yandex.com"
    Then I should see page title as "Yandex"
    And element with xpath "//input[@id='text']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='text']"
    Then I click on element using JavaScript with xpath "//div[@class='search2__button']//button"
    And I wait for element with xpath "//div[@class='content__left']" to be present
    And element with xpath "//ul[@id='search-result']" should contain text "Cucumber"

  @predefined10    #verified
  Scenario: Predefined steps for BOARDREADER
    Given I open url "http://boardreader.com"
    Then I should see page title contains "Boardreader"
    And element with xpath "//input[@id='title-query']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@id='title-query']"
    Then I click on element with xpath "//button[@id='title-submit']"
    And I wait for element with xpath "//ul[@class='mdl-list']" to be present
    And I wait for 2 sec
    And element with xpath "//ul[@class='mdl-list']" should contain text "Cucumber"


  @predefined11   #verified
  Scenario: Predefined steps for ECOSIA
    Given I open url "https://www.ecosia.org"
    Then I should see page title contains "Ecosia"
    And element with xpath "//input[@name='q']" should be present
    When I type "Behavior Driven Development" into element with xpath "//input[@name='q']"
    Then I click on element using JavaScript with xpath "//button[@aria-label='Submit']"
    And I wait for element with xpath "//div[@class='mainline-results']" to be present
    And element with xpath "//div[@class='mainline-results']" should contain text "Cucumber"

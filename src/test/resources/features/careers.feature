Feature: Skryabin careeers Heroku

@careers1
  Scenario: Recruiter removes position
    Given I navigate to "careers" page oop
    And I login as "recruiter"
    Then I verify "recruiter" login
    When I remove "Principal Automation Engineer" position
    And I verify "Principal Automation Engineer" position is removed
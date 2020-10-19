Feature: Skryabin careeers Heroku

@careers1
  Scenario: Recruiter removes position 1
    Given I open "careers" page
    And I login as "recruiter"
    Then I verify "recruiter" login
    When I remove "Principal Automation Engineer" position
    And I verify "Principal Automation Engineer" position is removed



@careers2
  Scenario: Recruiter removes position 2
    Given I open "careers" page
    And I login as "recruiter"
    Then I verify "recruiter" login
    When I remove "Totally manual QA inspector" position
    And I verify "Totally manual QA inspector" position is removed
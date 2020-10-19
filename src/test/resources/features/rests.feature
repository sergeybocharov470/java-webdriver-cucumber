@rest
  Feature: REST API automation for Careers project

    @rest1
    Scenario: REST API Position CRUD
      # POST /login to get token
      Given I login via REST as "recruiter"
      # POST /positions
      When I create via REST "austronaut" position
      # GET /positions
      Then I verify via REST new position is in the "austronaut" list
      # PUT /positions/{id}
      When I update via REST new "austronaut" position
      # GET /positions/{id}
      Then I verify via REST new "austronaut" position is updated
      # DELETE /positions/{id}
      When I delete via REST new position
      # GET /positions
      Then I verity via REST new position is deleted



    @rest2
    Scenario: REST API Candidates CRUD
      Given I login via REST as "recruiter"
      When I create via REST "sdet" candidate
      Then I verify via REST new "sdet" candidate is in the list
      When I update via REST "sdet" candidate
      Then I verify via REST new "sdet" candidate is updated
      When I delete via REST new candidate
      Then I verify via REST new candidate is deleted



    @careers2
    Scenario: Recruiter creates position
      Given I open "careers" page
      And I login as "recruiter"
      Then I verify "recruiter" login
      When I create new position
      Then I verify new position is created
      When I remove new position
      And I verify new position is removed

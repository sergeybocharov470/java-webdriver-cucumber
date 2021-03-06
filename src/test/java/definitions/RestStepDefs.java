package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import support.RestClient;
import support.TestContext;

import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.*;


public class RestStepDefs {

    String file = "";

    @Given("I login via REST as {string}")
    public void iLoginViaRESTAs(String role) {
        new RestClient().login(getDataFromYmlFile(role));
    }

    @When("I create via REST {string} position")
    public void iCreateViaRESTPosition(String type) {
        new RestClient().createPosition(/*getDataFromYmlFile(type)*/ getPositionFromFile(type));  //getPosition from TestContext
    }

    //////////////////////////////////////////////////////////////////////
	/// replaced with a code from Day20 class
/*    @Then("I verify via REST new position is in the {string} list")
    public void iVerifyViaRESTNewPositionIsInTheList(String fileName) {
        //System.out.println("ALL POSITIONS RETURNED: ");
        List<Map<String,Object>> actualPositions = new RestClient().getPositionsViaRest();
        String allPositions = "";
        for (Map<String,Object> position : positions) {
            allPositions = allPositions + position;
            //System.out.println(position.get("title"));
        }
        assertThat(allPositions.contains(getDataFromYmlFile(fileName).get("title"))).isTrue();             //(getDataFromYmlFile(fileName).get("title")));
    } */

   /* @Then("I verify via REST new position is in the {string} list")
    public void iVerifyViaRESTNewPositionIsInTheList(String fileName) {

       /* System.out.println("---------------TEST DATA-------------------");

        //System.out.println("TEST DATA");
        System.out.println("Map: " + getTestDataMap("newPosition"));
        //System.out.println("Var: " + testData);
        Map<String, Object> positionData = new HashMap<>();
        positionData = getTestDataMap("newPosition");
        System.out.println("String: " + positionData.get("title"));
        System.out.println("String: " + positionData.get("city"));
        System.out.println("From API: " + new TestContext().getTestDataMap("newPosition").get("title"));
        System.out.println("ID From API: " + new TestContext().getTestDataMap("newPosition").get("id"));

        System.out.println("From YAML: " + getDataFromYmlFile(fileName).get("title"));

        */


    /*    //assertThat(RestClient.getAllPositionTitlesViaRest().contains(getDataFromYmlFile(fileName).get("title"))).isTrue();
        assertThat(new TestContext().getTestDataMap("newPosition").get("title").toString().contains(getDataFromYmlFile(fileName).get("title"))).isTrue();
        assertThat(new TestContext().getTestDataMap("newPosition").get("description").toString().contains(getDataFromYmlFile(fileName).get("description"))).isTrue();
        assertThat(new TestContext().getTestDataMap("newPosition").get("city").toString().contains(getDataFromYmlFile(fileName).get("city"))).isTrue();//(getDataFromYmlFile(fileName).get("title")));
    }*/


    /*@When("I update via REST new {string} position")
    public void iUpdateViaRESTPosition(String type) {
        file = type;
        new RestClient().updatePosition(getDataFromYmlFile(type));
    }

    @Then("I verify via REST new position is updated")
    public void iVerifyViaRESTNewPositionIsUpdated() {
        //System.out.println("-------------------Data for update verification--------------------");
        //System.out.println("ZIP from API: " + new TestContext().getTestDataMap("updatedPosition").get("zip").toString());
        //System.out.println("ZIP(str) from API: " + String.valueOf(new TestContext().getTestDataMap("updatedPosition").get("zip")));
        assertThat(new TestContext().getTestDataMap("updatedPosition").get("title").toString().contains(getDataFromYmlFile(file).get("title"))).isTrue();
        assertThat(new TestContext().getTestDataMap("updatedPosition").get("description").toString().contains(getDataFromYmlFile(file).get("description"))).isTrue();
        assertThat(new TestContext().getTestDataMap("updatedPosition").get("city").toString().contains(getDataFromYmlFile(file).get("city"))).isTrue();
        assertThat(String.valueOf(new TestContext().getTestDataMap("updatedPosition").get("zip")).contains(String.valueOf(getDataFromYmlFile(file).get("zip")))).isTrue();
    }*/

   /* @When("I delete via REST new position")
    public void iDeleteViaRESTNewPosition() {
        new RestClient().deletePositionViaRest();

    }*/

   /* @Then("I verity via REST new position is deleted")
    public void iVerityViaRESTNewPositionIsDeleted() {
        new RestClient().newPositionDeleted();
    }*/

    @When("I create via REST {string} candidate")
    public void iCreateViaRESTCandidate(String candidate) {
        new RestClient().createCandidate(getCandidateFromFile(candidate));
        System.out.println("Candidate from API: " + new TestContext().getTestDataMap("newCandidate"));
    }

    /*@Then("I verify via REST new {string} candidate is in the list")
    public void iVerifyViaRESTNewCandidateIsInTheList(String candidate) {
        System.out.println("--------verification--------------");
        assertThat(new TestContext().getTestDataMap("newCandidate").get("firstName").toString().contains(getDataFromYmlFile(candidate).get("firstName"))).isTrue();
    }*/
	/////////////////////////
	//// added 20201018 Day 20 class
	    @Then("I verify via REST new position is in the {string} list")
    public void iVerifyViaRESTNewPositionIsInTheList(String type) {
														 
        List<Map<String, Object>> actualPositions = new RestClient().getPositions();
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        Map<String, String> expectedPosition = getPositionFromFile(type);
												   
        boolean isFound = false;
        for (Map<String, Object> actualPosition : actualPositions) {
            if (actualPosition.get("id").equals(expectedPositionId)) {
                isFound = true;

                for(String key : expectedPosition.keySet()) {
                    System.out.println("Verifying " + key);
                    assertThat(actualPosition.get(key)).isEqualTo(expectedPosition.get(key));
                }
                break;
            }
        }
        assertThat(isFound).isTrue();
																																																	  
    }


    @When("I update via REST new {string} position")
    public void iUpdateViaRESTPosition(String type) {
        Map<String, String> updatedPosition = getPositionFromFile(type + "_updated");
        Object id = getTestDataMap("newPosition").get("id");
        new RestClient().updatePosition(updatedPosition, id);
    }

    @Then("I verify via REST new {string} position is updated")
    public void iVerifyViaRESTNewPositionIsUpdated(String type) {
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        Map<String, Object> actualPosition = new RestClient().getPosition(expectedPositionId);
        Map<String, String> expectedFields = getPositionFromFile(type + "_updated");

        for (String key : expectedFields.keySet()) {
            System.out.println("Verifying " + key);
            assertThat(actualPosition.get(key).toString()).isEqualTo(expectedFields.get(key).toString());
        }
    }

    @When("I delete via REST new position")
    public void iDeleteViaRESTNewPosition() {
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        new RestClient().deletePositionViaRest(expectedPositionId);

    }

    @Then("I verity via REST new position is deleted")
    public void iVerityViaRESTNewPositionIsDeleted() {
        Object deletedId = getTestDataMap("newPosition").get("id");
        List<Map<String, Object>> actualPositions = new RestClient().getPositions();

        for (Map<String, Object> position : actualPositions) {
            assertThat(position.get("id")).isNotEqualTo(deletedId);
        }
    }
																									  
    /*@When("I create via REST {string} candidate")
    public void iCreateViaRESTCandidate(String title) {
        new RestClient().createCandidate(getCandidate(title));
    }*/

    @When("I add via REST {string} resume to a new candidate")
    public void iAddViaRESTResumeToANewCandidate(String fileType) {
        File resume = getFile("resume", fileType);
        new RestClient().addResume(resume, getTestDataMap("newCandidate").get("id"));
    }

    @When("I update via REST {string} candidate")
    public void iUpdateViaRESTCandidate(String fileType) {
        Map<String, String> updatedCandidate = getCandidateFromFile(fileType + "_updated");
        Object id = getTestDataMap("newCandidate").get("id");
        new RestClient().updateCandidate(updatedCandidate, id);
    }

    @Then("I verify via REST that {string} resume has been added")
    public void iVerifyViaRESTThatResumeHasBeenAdded(String fileType) throws IOException {
        ExtractableResponse<Response> response = new RestClient().getResume(getTestDataMap("newCandidate").get("id"));
        String disposition = response.header("content-disposition");
        assertThat(disposition).isEqualTo("attachment; filename=resume." + fileType);

        byte[] byteArray = response.asByteArray();
        String signature = Hex.encodeHexString(byteArray);
        switch (fileType) {
            case "pdf":
                assertThat(signature).startsWith("255044462d");
                break;
            case "doc":
            case "xls":
            case "ppt":
                assertThat(signature).startsWith("D0CF11E0A1B11AE1");
                break;
        }
//verify that contents of initial file and extracted and saved one are equal
        saveFile("returnedResume", fileType, byteArray);
        File actualFile = getFile("resume", fileType);
        File expectedFile = getFile("returnedResume", fileType);
        boolean areEqual = FileUtils.contentEquals(actualFile, expectedFile);
        assertThat(areEqual).isTrue();
   }

    @Then("I verify via REST new {string} candidate is in the list")
    public void iVerifyViaRESTNewCandidateIsInTheList(String type) {
        List<Map<String, Object>> actualCandidates = new RestClient().getCandidates();
        Object expectedCandidateId = getTestDataMap("newCandidate").get("id");
        Map<String, String> expectedCandidate = getCandidateFromFile(type);

        boolean isFound = false;
        for (Map<String, Object> actualCandidate : actualCandidates) {
            if (actualCandidate.get("id").toString().equals(expectedCandidateId.toString())) {

                for(String key : expectedCandidate.keySet()) {
                    if (key.equals("password")) {
                        continue;
                    }
                    else {
                        System.out.println("Verifying " + key + ", Actual: " + actualCandidate.get(key).toString() + "; Expected: " + expectedCandidate.get(key));
                        assertThat(actualCandidate.get(key).toString()).isEqualTo(expectedCandidate.get(key));
                        //System.out.println();
                    }
                }
                isFound = true;
                break;
            }
        }
        assertThat(isFound).isTrue();
    }

    @Then("I verify via REST new {string} candidate is updated")
    public void iVerifyViaRESTNewCandidateIsUpdated(String type) {
        Object candidateId = getTestDataMap("newCandidate").get("id");  //saved to 'testData' variable from 'createCandidate' response
     // an error expected here
        Map<String, Object> actualCandidate = new RestClient().getCandidate(candidateId);   //got from APP via REST request
        Map<String, String> expectedCandidate = getCandidateFromFile(type + "_updated"); //parsed from yml file

        for (String key : expectedCandidate.keySet()) {
            System.out.println("Verifying " + key);
            assertThat(actualCandidate.get(key).toString()).isEqualTo(expectedCandidate.get(key).toString());
        }
    }

    @When("I create new {string} position")  //authorisation is needed to post a new position
    public void iCreateNewPosition(String position) {
        new RestClient().createPosition(getPositionFromFile(position));
        setTestData("newExpectedPosition", getPositionFromFile(position));
        System.out.println("newExpectedPosition: " + getPositionFromFile(position));
    }

    @Then("I verify new position is created")
    public void iVerifyNewPositionIsCreated() {
        List<Map<String, Object>> actualPositions = new RestClient().getPositions();
        Object expectedPositionId = getTestDataMap("newPosition").get("id");
        Map<String, Object> expectedPosition = getTestDataMap("newExpectedPosition");

        boolean isFound = false;
        for (Map<String, Object> actualPosition : actualPositions) {
            if (actualPosition.get("id").equals(expectedPositionId)) {
                isFound = true;

                for(String key : expectedPosition.keySet()) {
                    System.out.println("Verifying " + key);
                    assertThat(actualPosition.get(key)).isEqualTo(expectedPosition.get(key));
                }
                break;
            }
        }
        assertThat(isFound).isTrue();
    }

    @When("I delete via REST new candidate")
    public void iDeleteViaRESTNewCandidate() {
        Object newCandidateId = getTestDataMap("newCandidate").get("id");
        new RestClient().deleteCandidate(newCandidateId);
        System.out.println("Candidate to delete Id: " + newCandidateId);
    }

    @Then("I verify via REST new candidate is deleted")
    public void iVerifyViaRESTNewCandidateIsDeleted() {
        Object newCandidateId = getTestDataMap("newCandidate").get("id");
        List<Map<String, Object>> candidates = new RestClient().getCandidates();
        boolean isFound = false;
        for (Map<String, Object> candidate : candidates) {
            if (candidate.get("id").equals(newCandidateId) ) {
                isFound = true;
            }
            break;
        }
        assertThat(isFound).isFalse();
        System.out.println("isFound: " + isFound);
    }
} // end of the class
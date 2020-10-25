package support;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;						  
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getTimestamp;
import static support.TestContext.setTestData;

public class  RestClient {

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private static String loginToken;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";
    public static final String AUTH = "Authorization";

    public void login(Map<String, String> user) {
        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("login")
                .header(CONTENT_TYPE, JSON)
                .body(user);

        // execute
        Response response = request.when()
                .post();

        // verify and extract data
        Map<String, Object> result = response.then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        loginToken = "Bearer " + result.get("token");
        System.out.println(loginToken);

    }

    public Map<String, Object> createPosition(Map<String, String> position) {
    //    String title = position.get("title");
    //    position.put("title", title + getTimestamp());

        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions")
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(position);

        // execute
        Response response = request.when()
                .post();

        // verify and extract
        Map<String, Object> result = response.then() //extracts data from API responce
                .log().all()
                .statusCode(201)    //verifies HTML responce code
                .extract()           //extracts
                .jsonPath()         //parse as a json
                .getMap("");    //creates a map

        setTestData("newPosition", result); //saves the response body to the 'testData' variable as a map

        return result;
    }

    public List<Map<String, Object>> getPositionsViaRest() {

        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions")
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken);

        // execute
        Response response = request.when()
                .get();

        // verify and extract
        List<Map<String, Object>> result = response.then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
        return result;
    }

    public static String getAllPositionIdViaRest() {
        List<Map<String,Object>> positions = new RestClient().getPositionsViaRest();
        String allPositions = "";
        for (Map<String,Object> position : positions) {
            allPositions = allPositions + position.get("id") +",";
        }
        return allPositions;
    }

    public List<Map<String, Object>> getPositions() {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
    }


    public Map<String, Object> updatePosition(Map<String, String> position, Object id) {
        // prepare
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(position)
				.when()
				.patch()
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getMap("");
    }
	
	public Map<String, Object> getPosition(Object id) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

											   

					  
    }

    public void deletePositionViaRest(Object id) {
        RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("positions/" + id)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .when()
                .delete()
                .then()
                .log().all()
                .statusCode(204);


    }

   /* public boolean newPositionDeleted() {
        if (getAllPositionIdViaRest().contains("3147"))  //new TestContext().getTestDataMap("updatedPosition").get("id").toString())) {
        {   return false;
        }
        else {
            return true;
        }

    } */

    public void newPositionDeleted() {
        assertThat(getAllPositionIdViaRest().contains(new TestContext().getTestDataMap("updatedPosition").get("id").toString())).isFalse();
    }

    public Map<String, Object> createCandidate(Map<String, String> newCandidate) {
       //String title = position.get("title");
       // position.put("title", title + getTimestamp());
        System.out.println("---------Create a new candidate--------------");
        // prepare
        RequestSpecification request = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates")
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(newCandidate);

        Response responce = request.when()
                .post();

        Map<String, Object> result = responce.then() //extracts data from API responce
                .log().all()
                .statusCode(201)    //verifies HTML responce code
                .extract()           //extracts
                .jsonPath()         //parse as a json
                .getMap("");    //creates a map

        setTestData("newCandidate", result); //saves resulted map to the 'testData' variable

        return result;
    }

    public void addResume(File resume, Object candidateId) {
        RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + candidateId + "/resume")
                .header(AUTH, loginToken)
                .multiPart("resume", resume)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(201);
    }

    public ExtractableResponse<Response> getResume(Object candidateId) {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + candidateId + "/resume")
                .header(AUTH, loginToken)
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract();
    }

    public List<Map<String, Object>> getCandidates() {
        return RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .basePath("candidates")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");
    }

    public Map<String, Object> updateCandidate(Map<String, String> updatedCandidate, Object id) {
        return RestAssured.given().
                log().all()
                .baseUri(baseUrl)
                .basePath("candidates/" + id)
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(updatedCandidate)
                .when()
                .patch()
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");

    }
} //end of class
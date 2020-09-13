package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDataFromYmlFile;

public class Quote_oop {

    QuoteForm myPage = new QuoteForm();
    QuoteResult myResult = new QuoteResult();

    private String dataFromFile(String fileName, String key) {
        Map<String, String> returnData = getDataFromYmlFile(fileName);
        return returnData.get(key);

    }


    @Given("I open {string} page oop")
    public void iOpenPageOop(String pageToOpen) {

        if (pageToOpen.equals("quote")) {
            myPage.open();
        } else {
            System.out.println(pageToOpen + " does not implemented yet");
        }
    }


    @When("I fill out required fields for {string} oop")
    public void iFillOutRequiredFieldsForOop(String role) {

        myPage.fillUsername(dataFromFile(role, "username"));
        myPage.fillName(dataFromFile(role, "firstname"), dataFromFile(role, "middlename"), dataFromFile(role, "lastname"));
        myPage.fillBothPasswords(dataFromFile(role, "password"));
        myPage.fillEMail(dataFromFile(role, "email"));
        myPage.agreeWithPrivacyPolicy();

        //System.out.println(dataFromFile(role,"username"));


    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        myPage.submit();
    }

    @Then("I verify required fields for {string} oop")
    public void iVerifyRequiredFieldsForOop(String role) {
        assertThat(myResult.getFirstName()).isEqualTo(dataFromFile(role, "firstname"));
        assertThat(myResult.getLastName()).isEqualTo(dataFromFile(role, "lastname"));
        assertThat(myResult.getMiddleName()).isEqualTo(dataFromFile(role, "middlename"));
        assertThat(myResult.getUserName()).isEqualTo(dataFromFile(role, "username"));
        assertThat(myResult.getPassword()).contains("entered");
        assertThat(myResult.getEMail()).isEqualTo(dataFromFile(role, "email"));
        assertThat(myResult.getPrivacyPolicy()).isTrue();
    }


}
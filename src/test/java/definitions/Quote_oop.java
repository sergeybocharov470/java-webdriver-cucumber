package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
//import static pages.QuoteForm.errorMessageExists;
import static support.TestContext.getDataFromYmlFile;

public class Quote_oop {

    QuoteForm quotePage = new QuoteForm();
    QuoteResult resultPage = new QuoteResult();

    private String dataFromFile(String fileName, String key) {
        Map<String, String> returnData = getDataFromYmlFile(fileName);
        return returnData.get(key);

    }


    @Given("I open {string} page oop")
    public void iOpenPageOop(String pageToOpen) {

        if (pageToOpen.equals("quote")) {
            quotePage.open();
        } else {
            System.out.println(pageToOpen + " does not implemented yet");
        }
    }


    @When("I fill out required fields for {string} oop")
    public void iFillOutRequiredFieldsForOop(String role) {

        quotePage.fillUsername(dataFromFile(role, "username"));
        quotePage.fillName(dataFromFile(role, "firstname"), dataFromFile(role, "middlename"), dataFromFile(role, "lastname"));
        quotePage.fillBothPasswords(dataFromFile(role, "password"));
        quotePage.fillEMail(dataFromFile(role, "email"));
        quotePage.agreeWithPrivacyPolicy();

        //System.out.println(dataFromFile(role,"username"));


    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        quotePage.submit();
    }

    @Then("I verify required fields for {string} oop")
    public void iVerifyRequiredFieldsForOop(String role) {
        assertThat(resultPage.getFirstName()).isEqualTo(dataFromFile(role, "firstname"));
        assertThat(resultPage.getLastName()).isEqualTo(dataFromFile(role, "lastname"));
        assertThat(resultPage.getMiddleName()).isEqualTo(dataFromFile(role, "middlename"));
        assertThat(resultPage.getUserName()).isEqualTo(dataFromFile(role, "username"));
        assertThat(resultPage.getPassword()).contains("entered");
        assertThat(resultPage.getEMail()).isEqualTo(dataFromFile(role, "email"));
        assertThat(resultPage.getPrivacyPolicy()).isTrue();
    }


    @When("I fill out optional fields for {string} oop")
    public void iFillOutOptionalFieldsForOop(String role) {
        String make = dataFromFile(role, "car_make");
        String state = dataFromFile(role, "country");
        String phNumber = dataFromFile(role, "phone");
        String genderInput = dataFromFile(role, "gender");
        String allowance = dataFromFile(role, "allow_to_contact");

        quotePage.selectCarMake(make);
        quotePage.selectCountry(state);
        quotePage.fillPhone(phNumber);
        quotePage.setGender(genderInput);
        quotePage.allowToContact(allowance);

        System.out.println("Car maker from file: " + make);
        System.out.println("Country of origin from file: " + state);
        System.out.println("Gender from file: " + genderInput);
    }

    @Then("I see {string} error message {string}")
    public void iSeeErrorMessage(String inputField, String errorMessageValue) {
        assertThat(quotePage.getErrorMessageText(inputField)).isEqualTo(errorMessageValue);
    }

    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String inputField, String inputValue) {
        quotePage.deleteFromInputField(inputField);
        quotePage.printIntoInputField(inputField, inputValue);
    }

    @Then("I don't see {string} error message")
    public void iDonTSeeErrorMessage(String inputField) {
        assertThat(quotePage.errorMessageVisible(inputField)).isFalse();
        /*
        if (quotePage.errorMessageVisible(inputField)) {
            return false;
        }
        //else if (quotePage.errorMessageExists(inputField)  && !quotePage.errorMessageVisible(inputField)) {
        //    return true;
        //}
        else {
            return true;
        }*/
    }


    @When("I fill out name field with first name {string} and last name {string}")
    public void iFillOutNameFieldWithFirstNameAndLastName(String nameToPrint, String lastNameToPrint) {
        quotePage.fillName(nameToPrint, lastNameToPrint);
    }


    @When("I fill out name field with first name {string}, middle name {string}, last name {string}")
    public void iFillOutNameFieldWithFirstNameMiddleNameLastName(String nameToPrint, String middleNameToPrint, String lastNameToPrint) {
        quotePage.fillName(nameToPrint, middleNameToPrint, lastNameToPrint);
    }

    @Then("I verify {string} field value {string}")
    public void iVerifyFieldValue(String inputField, String value) {
        assertThat(quotePage.getName()).isEqualTo(value);
    }
}
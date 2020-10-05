package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.CareersLanding;
import pages.CareersLogin;
import pages.CareersRecruit;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDataFromYmlFile;

public class CareersStepDefs {
    // xpath axes
//    String example1 ="//label[@for='loginUsername']/ancestor::div[@class='container']";
//    String example2 ="//label[@for='loginUsername']/following-sibling::input";

    // css sel
//    String example1Xpath = "//*[contains(@class,'fixed-top')]";
//    String example2Css = ".fixed-top";

    @And("I login as {string}")
    public void iLoginAs(String role) {
        new CareersLanding()
                .clickLogin()
                .login(getDataFromYmlFile(role));
    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String role) {
        String expectedName = getDataFromYmlFile(role).get("name");
        String actualName = new CareersLanding().getLoggedUserName();
        assertThat(actualName).isEqualTo(expectedName);
    }

    @When("I remove {string} position")
    public void iRemovePosition(String title) throws InterruptedException {
        new CareersLanding()
                .clickRecruit()
                .removePosition(title);
    }

    @And("I verify {string} position is removed")
    public void iVerifyPositionIsRemoved(String title) {
        new CareersRecruit().refresh();
        boolean isVisible = new CareersRecruit().isPositionVisible(title);
        boolean errorsPresent = new CareersRecruit().areErrorsPresent();
        assertThat(errorsPresent).isFalse();
        assertThat(isVisible).isFalse();
    }
}
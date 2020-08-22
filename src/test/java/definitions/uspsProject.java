package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.*;

import static support.TestContext.getDriver;

public class uspsProject {
    @Given("I open USPS website")
    public void iOpenUSPSWebsite() {
        getDriver().get("https://www.usps.com/");
    }

    @Then("I should see page title equals {string}")
    public void iShouldSeePageTitleEquals(String myTitle) {
        String title = getDriver().getTitle();
        assertThat(title.equals(myTitle));
    }


 //   @When("I put mouse at {string}")
 //   public void iPutMouseAt(String arg0) {
 //       getDriver().
 //   }
}

package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.UspsPostalPriceCalculator;
import pages.UspsHeader;
import pages.UspsHome;
import pages.UspsPostalPriceCalculatorResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UspsStepsDefOop {

    @Given("I open {string} page  oop")
    public void iOpenPage(String page) throws InterruptedException {
        switch (page) {
            case "usps":
                new UspsHome().open();
                break;
            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }


    @When("I go to Calculate Price Page oop")
    public void iGoToCalculatePricePageOop() {
        new UspsHeader().goToCalculatePrice();
    }

    @And("I select {string} with {string} shape oop")
    public void iSelectWithShapeOop(String country, String postalShape) {

        new UspsPostalPriceCalculator().selectDestinationCountry(country);
        new UspsPostalPriceCalculator().selectPostalShape(postalShape);


    }

    @And("I define {string} quantity oop")
    public void iDefineQuantityOop(String quantity) {
        new UspsPostalPriceCalculatorResult().inputQuantityOfItems(quantity);

    }

    @Then("I calculate the price and validate cost is {string} oop")
    public void iCalculateThePriceAndValidateCostIsOop(String totalSum) {
        new UspsPostalPriceCalculatorResult().clickCalculateButton();
        assertThat(new UspsPostalPriceCalculatorResult().getTotal().equals(totalSum)).isTrue();
    }

    @When("I go to Postal Store tab and Stamps item oop")
    public void iGoToPostalStoreTabAndStampsItemOop() {
        {
            new UspsHeader().goToPostalStore();
            new UspsHeader().clickStampsOption();
        }

    }
}   // end of UspsStepsDefOop class

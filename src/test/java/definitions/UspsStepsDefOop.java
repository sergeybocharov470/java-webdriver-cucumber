package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.PostalPriceCalculator;
import pages.UspsHeader;
import pages.UspsHome;
import pages.UspsPage;

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

        new PostalPriceCalculator().selectDestinationCountry(country);
        new PostalPriceCalculator().selectPostalShape(postalShape);


    }

    @And("I define {string} quantity oop")
    public void iDefineQuantityOop(String quantity) {
        new PostalPriceCalculator().inputQuantityOfItems(quantity);
        new PostalPriceCalculator().clickCalculateButton();
    }
}   // end of UspsStepsDefOop class

package definitions;

import cucumber.api.java.en.Given;
import pages.CareersHome;


public class CareersStepsDefOop {

    @Given("I navigate to {string} page oop")
    public void iNavigateToPageOop(String page) throws InterruptedException {
        switch (page) {
            case "careers":
                new CareersHome().open();
                break;
            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }





} // end of class

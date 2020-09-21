package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.UPS_Main;

import java.util.Map;

import static support.TestContext.*;

public class Ups_oop {

    UPS_Main upsMainPage = new UPS_Main();

    private String dataFromFile(String key) {
        Map<String, String> returnData = getDataFromYmlFile("UPS_shipment");
        return returnData.get(key);

    }


    @Given("I go to {string} page oop")
    public void iGoToPageOop(String pageToOpen) {

        if (pageToOpen.equals("ups")) {
            upsMainPage.open();
        } else {
            System.out.println(pageToOpen + " does not implemented yet");
        }
    }


    @And("I open Shipping menu oop")
    public void iOpenShippingMenuOop() {
        upsMainPage.getShippingMenu();
    }


    @And("I go to Create a Shipment oop")
    public void iGoToCreateAShipmentOop() {
        upsMainPage.selectCreateAShipment();
    }

    @When("I fill out origin shipment fields oop")
    public void iFillOutOriginShipmentFieldsOop() {
        //String country = dataFromFile("country");
        UPS_Main shippingFromLabel = new UPS_Main();
        getWait().until(ExpectedConditions.visibilityOf(shippingFromLabel.getWhereAreYouShippingFromLabel()));
        upsMainPage.setCountry(dataFromFile("country"));
        upsMainPage.setSenderName(dataFromFile("sender_name"));
        upsMainPage.setSenderAddress(dataFromFile("sender_address"));
        upsMainPage.setSenderZip(dataFromFile("sender_zip"));
        upsMainPage.setSenderEmail(dataFromFile("sender_email"));
        upsMainPage.setSenderPhone(dataFromFile("sender_phone"));
    }

    @And("I submit the shipment form oop")
    public void iSubmitTheShipmentFormOop() throws InterruptedException {
        /*UPS_Main shippingFromLabel = new UPS_Main();
        getWait(8).until(ExpectedConditions.elementToBeClickable(shippingFromLabel.getContinueButton()));
        */
        upsMainPage.switchToIFrame();
        upsMainPage.waitForSpinnerInvisibility();
        upsMainPage.switchToDefaultContent();
        upsMainPage.clickContinueButton();
    }
}  // end of class

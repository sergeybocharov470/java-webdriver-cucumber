package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.UPS_Main;
import pages.UPS_Shipment;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.Keys.DOWN;
import static support.TestContext.*;

public class Ups_oop {

    UPS_Main upsMainPage = new UPS_Main();
    UPS_Shipment upsShipmentPage = new UPS_Shipment();

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
    public void iFillOutOriginShipmentFieldsOop() throws InterruptedException {
        UPS_Shipment shipping = new UPS_Shipment();
        getWait().until(ExpectedConditions.visibilityOf(shipping.getWhereAreYouShippingFromLabel()));
        //Thread.sleep(3000);
        upsShipmentPage.setCountry(dataFromFile("country"));
        upsShipmentPage.setSenderName(dataFromFile("sender_name"));
        upsShipmentPage.setSenderAddress(dataFromFile("sender_address"));
        upsShipmentPage.setSenderZip(dataFromFile("sender_zip"));
        upsShipmentPage.setSenderEmail(dataFromFile("sender_email"));
        upsShipmentPage.setSenderPhone(dataFromFile("sender_phone"));
    }

    @And("I submit the shipment form oop")
    public void iSubmitTheShipmentFormOop() throws InterruptedException {
        UPS_Shipment shipping = new UPS_Shipment();

       // getWait().until(ExpectedConditions.visibilityOf(upsMainPage.getCheckMark()));    ////div[@id='CityContainer']//span[@class='ups-icon-check']
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(upsShipmentPage.getSenderCity(), "value"));

        shipping.getContinueButton().sendKeys(DOWN);
        upsShipmentPage.clickContinueButton();
    }

    @Then("I verify origin shipment fields submitted oop")
    public void iVerifyOriginShipmentFieldsSubmittedOop() {
        UPS_Shipment shipping = new UPS_Shipment();
        getWait().until(ExpectedConditions.visibilityOf(shipping.getShipFromLabel()));
        assertThat(upsShipmentPage.getSenderToVerifyText().contains("Doe")).isTrue();

    }
}  // end of class

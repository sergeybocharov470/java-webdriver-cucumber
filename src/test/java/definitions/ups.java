package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.getDriver;
import static support.TestContext.getExecutor;

public class ups {
    @And("I open Shipping menu")
    public void iOpenShippingMenu() {
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks2']")).click();
    }

    @And("I go to Create a Shipment")
    public void iGoToCreateAShipment() throws InterruptedException {
        getDriver().findElement(By.xpath("//a[text()='Create a Shipment:']")).click();
        //Thread.sleep(5000);
        
    }

    @When("I fill out origin shipment fields")
    public void iFillOutOriginShipmentFields() {

        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Sergey Bocharov");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("4601 Willowgrove Drive");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("21042");

        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("sbocharov222@example.org");   /// mock email
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys("4109553322");

        /*WebDriverWait myCityName = new WebDriverWait(getDriver(), 3);
        myCityName.until(ExpectedConditions.attributeContains(getDriver().findElement(By.xpath("//input[@id='origincity']")),  "Value", value ));

        */

        //input[@id='originname']
    }

    @And("I submit the shipment form")
    public void iSubmitTheShipmentForm() throws InterruptedException {
        ////// LOOK FOR SPINNER!   probably on iframe
        WebElement iframe	=	getDriver().findElement(By.xpath("//iframe[@src='javascript:void(0)']"));
        getDriver().switchTo().frame(iframe);
        //wait for spinner invisibility
        WebDriverWait mySpinner = new WebDriverWait(getDriver(),3);
        mySpinner.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='button-spinner']")));

        //return to the default content
        getDriver().switchTo().defaultContent();

        //Thread.sleep(5000);

        //click() with script instead of direct webelement clicking
        WebElement element = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
        getExecutor().executeScript("arguments[0].click();",	element);
        //getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']")).click();
    }

    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() {
        //verify with a file data
        //simplified prototype. Necessary to use data from file
        assertThat(getDriver().findElement(By.xpath("//div[@class='ups-group ups-group_condensed']")).getText()).contains("Sergey Bocharov");
    }

    @And("I cancel the shipment form")
    public void iCancelTheShipmentForm() {
        //cancellation
        WebElement element = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']"));
        getExecutor().executeScript("arguments[0].click();",	element);
        //getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']")).click();
        //verify cancellation form appears
        WebDriverWait waitForCancel = new WebDriverWait(getDriver(),3);
        waitForCancel.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@class='modal-body ups-form_wrap']"))));
        //submit cancellation
        getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']")).click();


    }

    @Then("I verify shipment form is reset")
    public void iVerifyShipmentFormIsReset() {
        WebDriverWait waitForInitial = new WebDriverWait(getDriver(), 3);
        waitForInitial.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Use a different return address?')]")));

        assertThat(getDriver().findElement(By.xpath("//input[@id='originname']")).getText().isEmpty());
        assertThat(getDriver().findElement(By.xpath("//input[@id='originaddress1']")).getText().isEmpty());
        assertThat(getDriver().findElement(By.xpath("//input[@id='originpostal']")).getText().isEmpty());
        assertThat(getDriver().findElement(By.xpath("//input[@id='originemail']")).getText().isEmpty());
        assertThat(getDriver().findElement(By.xpath("//input[@id='originphone']")).getText().isEmpty());

    }

    @When("I fill out destination shipment fields")
    public void iFillOutDestinationShipmentFields() {
    }
}

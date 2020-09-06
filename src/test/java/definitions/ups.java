package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.*;
import static support.TestContext.*;

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
    public void iFillOutOriginShipmentFields() throws InterruptedException{

        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Sergey Bocharov");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("4601 Willowgrove Drive");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("21042");

        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("sbocharov222@example.org");   /// mock email
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys("4109553322");
        System.out.println("I fill out origin shipment fields");
        //Thread.sleep(3000);
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

        //Thread.sleep(2000);

        //click() with script instead of direct webelement clicking
        WebElement element = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
        getExecutor().executeScript("arguments[0].click();",	element);
        //getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']")).click();
        System.out.println("I submitted");
    }

    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() throws InterruptedException {
        //verify with a file data
        //simplified prototype. Necessary to use data from file
        //Thread.sleep(3000);
        assertThat(getDriver().findElement(By.xpath("//div[@class='ups-group ups-group_condensed']")).getText()).contains("Sergey Bocharov");
        System.out.println("I verify origin shipment fields submitted");
        //Thread.sleep(3000);
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

        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys("Mikhail");
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys("4970 El Camino Real");
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys("94022");
        getDriver().findElement(By.xpath("//input[@id='destinationemail']")).sendKeys("MikhailE@example.org");
        //getWait().until(ExpectedConditions.attributeToBeNotEmpty(getDriver().findElement(By.xpath("//input[@id='destinationcity']")), "Value"));
        //getWait().until(ExpectedConditions.textToBe(By.xpath("//input[@id='destinationcity']", "Califirnia")
        //getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']")).click();
        System.out.println("Destination shipment fields are filled out");
    }



    @And("I set packaging type and weight")
    public void iSetPackagingTypeAndWeight() throws InterruptedException {
        // packaging size
        String packaging = "//select[@id='nbsPackagePackagingTypeDropdown0']";
        getWait().until(ExpectedConditions.elementToBeClickable(By.xpath(packaging)));
        Select packagingType = new Select(getDriver().findElement(By.xpath(packaging)));
        packagingType.selectByValue("59");
        getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']")).sendKeys("2");
        System.out.println("I filled out packaging fields");
        Thread.sleep(3000);
    }



    @Then("I verify total charges appear")
    public void iVerifyTotalChargesAppear() throws InterruptedException {
        WebElement charged = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']"));
        WebDriverWait waitForCharges = new WebDriverWait(getDriver(),4);
        waitForCharges.until(ExpectedConditions.visibilityOf(charged));
        Thread.sleep(3000);
        System.out.println(charged.getAttribute("text"));
    }
}

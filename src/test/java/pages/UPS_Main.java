package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static support.TestContext.getDriver;

public class UPS_Main {

    protected String url;

    //menu variables
    @FindBy (xpath = "//a[@id='ups-menuLinks2']")
    private WebElement shippingMenu;

    @FindBy (xpath = "//a[contains(text(),'Create a Shipment')]")
    private WebElement createAShipmentOption;

       // page constructor
    public UPS_Main() {
        PageFactory.initElements(getDriver(), this);
        url = "https://www.ups.com/us/en/Home.page";
    }

    public void open() {
        getDriver().get(url);
    }

    public void getShippingMenu() {
        shippingMenu.click();
    }


    public void selectCreateAShipment() {
        createAShipmentOption.click();
    }


}  // end of class



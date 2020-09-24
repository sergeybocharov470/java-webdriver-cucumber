package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static support.TestContext.getDriver;

public class UPS_Shipment extends UPS_Main {

    public UPS_Shipment() {
       // PageFactory.initElements(getDriver(), this);
        url = "https://www.ups.com/us/en/Home.page";
    }



    @FindBy(xpath = "//h2[contains(text(),'Where are you shipping from?')]")
    private WebElement whereAreYouShippingFromLabel;

    @FindBy (xpath = "//h2[contains(text(),'Ship From /')]")
    private WebElement shipFromLabel;

    @FindBy (xpath = "//select[@id='origincountry']")
    private WebElement countryOption;

    @FindBy (xpath = "//input[@id='originname']")
    private WebElement senderName;

    @FindBy (xpath = "//input[@id='originaddress1']")
    private WebElement senderAddress;

    @FindBy (xpath = "//input[@id='originpostal']")
    private WebElement senderZip;

    @FindBy (xpath = "//input[@id='originemail']")
    private WebElement senderEmail;

    @FindBy (xpath = "//input[@id='originphone']")
    private WebElement senderPhone;

    @FindBy (xpath = "//button[text()='Continue']")
    private WebElement continueButton;

    @FindBy (xpath = "//button[@id='nbsBackForwardNavigationCancelShipmentButton']")
    private WebElement cancelButton;

    @FindBy (xpath ="//button[@id='nbsCancelShipmentWarningYes']")
    private WebElement cancelWarningButton;

    @FindBy (xpath = "//input[@id='origincity']")
    private WebElement senderCity;

    //@FindBy (xpath = "//span[@class='button-spinner']")
    //private WebElement buttonSpinner;

    //@FindBy (xpath = "//iframe[@src='javascript:void(0)']")
    //private WebElement iFrame;

    @FindBy (xpath = "//div[@class='ups-group ups-group_condensed']")
    private WebElement senderToVerify;


    /////////////////
    public WebElement getWhereAreYouShippingFromLabel() {
        return whereAreYouShippingFromLabel;
    }

    public WebElement getShipFromLabel() {
        return shipFromLabel;
    }

    public void setCountry(String country) {
        new Select(countryOption).selectByVisibleText(country);
    }

    public void setSenderName(String name) {
        senderName.sendKeys(name);
    }

    public void setSenderAddress(String address) {
        senderAddress.sendKeys(address);
    }

    public void setSenderZip(String zip) {
        senderZip.sendKeys(zip);
    }

    public void setSenderEmail(String email) {
        senderEmail.sendKeys(email);
    }

    public void setSenderPhone(String phone) {
        senderPhone.sendKeys(phone);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public WebElement getContinueButton() {
        return continueButton;
    }

    /*public WebElement getButtonSpinner() {
        return buttonSpinner;
    } */

    public WebElement getSenderEmail() {return senderEmail;}

    public WebElement getSenderPhone() {return senderPhone;}

    public WebElement getSenderName() {return senderName;}

    public WebElement getSenderCity() {return senderCity;}

    public WebElement getSenderAddress() {return senderAddress;}

    public String getSenderToVerifyText() {return senderToVerify.getText();}

    public void clickCancelButton() {
        cancelButton.click();
    }

    public void warningAccept() {
        cancelWarningButton.click();
    }


}  // end of class

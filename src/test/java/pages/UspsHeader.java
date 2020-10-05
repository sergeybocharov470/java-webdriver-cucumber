package pages;

import cucumber.api.java8.Fi;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getExecutor;

public class UspsHeader extends UspsPage {

    @FindBy(id="mail-ship-width")
    private WebElement mailAndShip;

    @FindBy(xpath="//li[@class='tool-zip']/a")
    private WebElement lookupByZip;

    @FindBy(xpath="//li[@class='tool-calc']/a[contains(@href,'post')]")
    private WebElement calculatePrice;

    @FindBy (xpath = "//a[text()='Postal Store']")
    private WebElement postalStore;

    @FindBy (xpath = "//h3[text()='Shop']/..//a[contains(text(),'Stamps')]")
    private WebElement stamps;


    public void goToLookupByZip() {
        mouseOver(mailAndShip);
        click(lookupByZip);
    }

    public void goToCalculatePrice() {
        mouseOver(mailAndShip);
        calculatePrice.click();
    }

    public void goToPostalStore() {
        mouseOver(postalStore);

    }

    public void clickStampsOption() {
        stamps.click();
    }

}
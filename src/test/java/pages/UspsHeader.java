package pages;

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


    public void goToLookupByZip() {
        mouseOver(mailAndShip);
        click(lookupByZip);
    }

    public void goToCalculatePrice() {
        mouseOver(mailAndShip);
        calculatePrice.click();
    }

}
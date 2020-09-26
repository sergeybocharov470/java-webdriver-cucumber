package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UspsPostalPriceCalculatorResult extends UspsHeader {

    @FindBy(xpath = "//input[@placeholder='Quantity']")
    WebElement QuantityOfItems;

    @FindBy (xpath = "//input[@value='Calculate']")
    WebElement calculateButton;

    @FindBy (xpath = "//div[@id='total']")
    WebElement total;



    public void inputQuantityOfItems (String quantity) {
        QuantityOfItems.sendKeys(quantity);

    }

    public void clickCalculateButton() {
        calculateButton.click();
    }

    public String getTotal() {
        return total.getText();
    }


} // end of class
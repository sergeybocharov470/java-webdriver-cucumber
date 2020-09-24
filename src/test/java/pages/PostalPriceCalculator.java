package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PostalPriceCalculator extends UspsHeader {

    @FindBy (xpath = "//select[@id='CountryID']" )
    private WebElement destinationCountry;

    @FindBy (xpath = "//input[@value='Postcard']")
    private WebElement postcard;

    @FindBy (xpath = "//input[@placeholder='Quantity']")
    WebElement QuantityOfItems;

    @FindBy (xpath = "//input[@value='Calculate']")
    WebElement calculateButton;



    public void selectDestinationCountry(String country) {
        new Select(destinationCountry).selectByVisibleText(country);

    }

    public void selectPostalShape(String shape) {
        switch (shape) {
            case "Postcard" -> postcard.click();
            default -> System.out.println("Choose another postal shape!");
        }
    }

    public void inputQuantityOfItems (String quantity) {
        QuantityOfItems.sendKeys(quantity);

    }

    public void clickCalculateButton() {
        calculateButton.click();
    }

}  // end of PostalPriceCalculator class
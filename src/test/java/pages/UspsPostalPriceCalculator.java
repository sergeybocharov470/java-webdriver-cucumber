package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UspsPostalPriceCalculator extends UspsHeader {

    @FindBy (xpath = "//select[@id='CountryID']" )
    private WebElement destinationCountry;

    @FindBy (xpath = "//input[@value='Postcard']")
    private WebElement postcard;





    public void selectDestinationCountry(String country) {
        new Select(destinationCountry).selectByVisibleText(country);

    }

    public void selectPostalShape(String shape) {
        switch (shape) {
            case "Postcard" -> postcard.click();
            default -> System.out.println("Choose another postal shape!");
        }
    }


}  // end of PostalPriceCalculator class
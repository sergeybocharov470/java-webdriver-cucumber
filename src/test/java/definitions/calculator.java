package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static support.TestContext.getDriver;

public class calculator {
    @When("I navigate to {string}")
    public void iNavigateTo(String myCalc) {
        //save initial window
        String initialWindow = getDriver().getWindowHandle();

        getDriver().findElement(By.xpath("//a[contains(@href,'auto-loan-calculator')]")).click();
        //switch to new window
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }

    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() {
        List<WebElement> inputFields = getDriver().findElements(By.xpath("//table[@id='calinputtable']//input[@type='text']"));
        for (WebElement element : inputFields) {
            element.clear();
        }
    }
}

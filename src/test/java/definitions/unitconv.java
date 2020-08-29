package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;



public class unitconv {

    //int t;

    @When("I switch to {string} tab")
    public void iSwitchToTab(String tab) {
        switch (tab) {
            case "Length" -> getDriver().findElement(By.xpath("//a[contains(@href,'Length')]")).click();
            case "Temperature" -> getDriver().findElement(By.xpath("//a[contains(@href,'Temperature')]")).click();
            case "Area" -> getDriver().findElement(By.xpath("//a[contains(@href,'Area')]")).click();
            case "Volume" -> getDriver().findElement(By.xpath("//a[contains(@href,'Volume')]")).click();
            case "Weight" -> getDriver().findElement(By.xpath("//a[contains(@href,'Weight')]")).click();
            default -> System.out.println("Option \"" + tab + "\" not implemented yet");
        }
    }


    @And("I set From option to {string}")
    public void iSetFromOptionTo(String fromOption) {
        WebDriverWait myWait = new WebDriverWait(getDriver(),1);
        myWait.until(ExpectedConditions.textToBePresentInElement(getDriver().findElement(By.xpath("//select[@id='calFrom']")), "a"));    //.visibilityOf(getDriver().findElement(By.xpath("//select[@id='calFrom']"))));

        getDriver().findElement(By.xpath("//select[@id='calFrom']//option[text()='" + fromOption + "']")).click();

    }



    @And("I set From value to {string}")
    public void iSetFromValueTo(String fromValue) {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(fromValue);

    }




    @Then("I set To option to {string} and verify To value {string}")
    public void iSetToOptionToAndVerifyToValue(String toOption, String toValue) {
        getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'" + toOption + "')]")).click();
        assertThat(getDriver().findElement(By.xpath("//select[@id='calTo']//option[contains(text(),'" + toOption + "')]")).getText().contains(toValue)).isTrue();
    }
}


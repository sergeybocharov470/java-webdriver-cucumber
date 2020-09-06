package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
            if (!element.getAttribute("id").equalsIgnoreCase("cmonthlypay")) {
                //element.getAttribute("id");
                element.clear();
            }
          //  else {
          //      System.out.println("element not found");
          //  }
        }
    }

    @And("I calculate")
    public void iCalculate() {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
        //input[@value='Calculate']
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String errorText) {
        List<WebElement> errors = getDriver().findElements(By.xpath("//a[@name='autoloanresult']/.."));
        //int numErrors = 0;
            for (WebElement myError : errors) {
                assertThat(myError.getText().equalsIgnoreCase(errorText));
                break;

            }
    }


    @And("I enter {string} price, {string} months, {string} interest, {string} downpayment, {string} trade-in, {string} state, {string} percent tax, {string} fees")
    public void iEnterPriceMonthsInterestDownpaymentTradeInStatePercentTaxFees(String price, String months, String interest, String downpayment, String tradeIn, String state, String tax, String fees) {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).sendKeys(months);
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).sendKeys(interest);
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).sendKeys(downpayment);
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).sendKeys(tradeIn);
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).sendKeys(tax);
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).sendKeys(fees);
        Select myState = new Select(getDriver().findElement(By.xpath("//select[@name='cstate']")));
        myState.selectByVisibleText(state);


    }

    @Then("I verify monthly pay is {string}")
    public void iVerifyMonthlyPayIs(String monthlyPay) {
        WebElement result = getDriver().findElement(By.xpath("//a[@name='autoloanresult']/..//h2"));
        //System.out.println(result.getText());
        assertThat(result.getText()).containsIgnoringCase(monthlyPay);
    }




    @And("I enter {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iEnterCalculationParams(String price, String months, String interest, String downpayment, String tradein, String state, String tax, String fees) {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).sendKeys(months);
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).sendKeys(interest);
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).sendKeys(downpayment);
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).sendKeys(tradein);
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).sendKeys(tax);
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).sendKeys(fees);
        Select myState = new Select(getDriver().findElement(By.xpath("//select[@name='cstate']")));
        myState.selectByVisibleText(state);
    }

    @Then("I verify monthly pay {string}")
    public void iVerifyMonthlyPay(String monthlyPay) {
        WebElement result = getDriver().findElement(By.xpath("//a[@name='autoloanresult']/..//h2"));
        System.out.println(result.getText());
        assertThat(result.getText()).containsIgnoringCase(monthlyPay); //    .compareToIgnoreCase(monthlyPay)).   .contains());
    }



}

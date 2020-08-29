package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import static support.TestContext.getDriver;

public class uspsProject {
    @Given("I open USPS website")
    public void iOpenUSPSWebsite() {
        getDriver().get("https://www.usps.com/");
    }

    @Then("I should see page title equals {string}")
    public void iShouldSeePageTitleEquals(String myTitle) {
        String title = getDriver().getTitle();
        assertThat(title.equals(myTitle));
    }

    @When("I go to Calculate Price Page")
    public void iGoToCalculatePricePage() {
        WebElement qTools = getDriver().findElement(By.xpath("//a[@class='nav-first-element menuitem']"));
        Actions mouseOver = new Actions(getDriver());
        mouseOver.moveToElement(qTools).perform();
        getDriver().findElement(By.xpath("//img[@alt='Calculate a Price Icon']/..")).click();
    }

    @And("I select {string} with {string} shape")
    public void iSelectWithShape(String country, String shape) throws InterruptedException {
        WebElement myCountry = getDriver().findElement(By.xpath("//select[@id='CountryID']"));
        //explicit wait to be added
        WebDriverWait wait = new WebDriverWait(getDriver(), 3);
        wait.until(ExpectedConditions.textToBePresentInElement(myCountry, country));
        // Select object definition
        Select selectCountry = new Select(myCountry);
        selectCountry.selectByVisibleText(country);
        if (shape.equals("Postcard")) {
            getDriver().findElement(By.xpath("//input[@value='Postcard']")).click();
        }
        else {
            System.out.println("Option \"" + shape +"\" not implemented yet");
        }

    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String sQuantity) throws InterruptedException {
        int myQuantity = Integer.parseInt(sQuantity);
        String newSQuantity = String.valueOf(myQuantity);
        if (sQuantity.equals(newSQuantity)) {
            getDriver().findElement(By.xpath("//input[@id='quantity-0']")).sendKeys(sQuantity);
        }
        else {
            System.out.println("Quantity should be numeric");
        }
    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePriceAndValidateCostIs(String myPrice) {
        getDriver().findElement(By.xpath("//input[@class='btn btn-pcalc btn-default']")).click();
        assertThat(getDriver().findElement(By.xpath("//div[@id='cost-0']")).getText().equals(myPrice));
    }

    @When("I perform {string} search")  //I perform "Free Boxes" search
    public void iPerformSearch(String searchString) throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='home-input']")).sendKeys(searchString);

        WebElement myButton = getDriver().findElement(By.xpath("//button[@class='input--search btn']"));

        myButton.click();
        Thread.sleep(2000);
       // WebDriverWait iWait = new WebDriverWait(getDriver(),10);
       // iWait.until(ExpectedConditions.elementToBeClickable(myButton));

    }

    @And("I set {string} in filters")  //I set "Mail & Ship" in filters
    public void iSetInFilters(String filterOption) {
        //explicit wait
        WebDriverWait myWait = new WebDriverWait(getDriver(),3);
        myWait.until(ExpectedConditions.titleContains("Search Results Page"));
        //filter a result set
        getDriver().findElement(By.xpath("//a[@class='dn-attr-a'][@title='Mail & Ship']")).click();
        //verify that filter is applied
        WebElement filterCat = getDriver().findElement(By.xpath("//span[contains(text(), 'Mail & Ship(')]/../../.."));     //("//a[@class='dn-attr-a'][@title='Mail & Ship']"));
        myWait.until(ExpectedConditions.attributeContains(filterCat,"class","active"));

    }

    @Then("I verify that {string} results found")
    public void iVerifyThatResultsFound(String result) {
        //counts returned elements
        assertThat(getDriver().findElement(By.xpath("//span[@id='searchResultsHeading']")).getText().contains(result));    //
    }


    @When("I select {string} in results")   //I select "Priority Mail | USPS" in results
    public void iSelectInResults(String filteredResult) throws InterruptedException {
        //Thread.sleep(3000);    // spinner    //span[@id='snippet_22']
        WebDriverWait waitForLoad = new WebDriverWait(getDriver(),3);
        WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
        waitForLoad.until(ExpectedConditions.invisibilityOf(spinner));
        //waitForLoad.until(ExpectedConditions.not(presenceOfElementLocated()))

        getDriver().findElement(By.xpath("//div[@id='main_res']//span[contains(text(),'Priority Mail | USPS')]/../..")).click();

        WebDriverWait waitForPage = new WebDriverWait(getDriver(),3);
        waitForPage.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//div[@id='maincontent']//a[@class='button--primary']") )));
    }

    @And("I click {string} button")
    public void iClickButton(String button) {
        String myXpath;
        if (button.equals("Ship Now")) {
            myXpath = "//div[@id='maincontent']//a[@class='button--primary']";
            getDriver().findElement(By.xpath(myXpath)).click();
        }
        else {
            System.out.println("Your input \"" + button +"\" is not correct. Use \"Ship Now\" instead");
        }
    }

    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() {
        assertThat(getDriver().getTitle().contains("Sign In"));
    }


    @When("I go to {string} tab")
    public void iGoToTab(String arg0) {

        //mouse over
        WebElement myHelp = getDriver().findElement(By.xpath("//a[@class='menuitem'][text()='Help']"));
        Actions mouseOver = new Actions(getDriver());
        mouseOver.moveToElement(myHelp).perform();


        //explicit wait

        //return to initial window

    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String searchString) throws InterruptedException {
        //save unitial window
        String initialWindow = getDriver().getWindowHandle();
        //click FAQ element
        getDriver().findElement(By.xpath("//div[@class='repos']//a[text()='FAQs']")).click();
        //switch to new window
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }
        WebElement myInput = getDriver().findElement(By.xpath("//input[@placeholder='Search for a topic']"));
        WebDriverWait myWait = new WebDriverWait(getDriver(),5);
        myWait.until(ExpectedConditions.visibilityOf(myInput));

        myInput.sendKeys(searchString);

        //pay attention for thr behavior of the button while you input data to search
        WebDriverWait myWaitForButton = new WebDriverWait(getDriver(),5);
        myWaitForButton.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//button[@class='slds-button slds-button_brand search-button focus']"))));
        getDriver().findElement(By.xpath("//button[@class='slds-button slds-button_brand search-button focus']")).click();
    }

    @Then("I verify that no results of {string} available in help search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String expectedString) {
        List<WebElement> myList = getDriver().findElements(By.xpath("//div[@class='resultBody']"));     //li[contains(@class,'kbResultStencil')]
        for (WebElement element : myList) {
            assertThat(!element.getText().contains(expectedString));

        }
    }

    @When("I navigate to Find a Location page")
    public void iNavigateToFindALocationPage() {
        //save unitial window
        String initialWindow = getDriver().getWindowHandle();

        WebElement qTools = getDriver().findElement(By.xpath("//a[@class='nav-first-element menuitem']"));
        Actions mouseOver = new Actions(getDriver());
        mouseOver.moveToElement(qTools).perform();
        getDriver().findElement(By.xpath("//img[@alt='Post Office Locator Icon']/..")).click();

        //switch to new window
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }

    }

    @And("I filter by {string} location types, {string} services, {string} available services")
    public void iFilterByLocationTypesServicesAvailableServices(String LocationType, String ServiceType, String AvailableService) throws InterruptedException{
        //Location type select
        getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
        getDriver().findElement(By.xpath("//ul[@class='dropdown-menu']//a[text()='" + LocationType + "' and @data-value='po']")).click();//ul[@class='dropdown-menu']//a[@data-value='po']
        //service type select
        getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
        getDriver().findElement(By.xpath("//ul[@class='dropdown-menu']//a[text()='" + ServiceType + "']")).click();
        //available service select
        WebDriverWait myWaitAvailableService = new WebDriverWait(getDriver(),5);
        myWaitAvailableService.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//button[@id='available-service-select']"))));
        getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
        getDriver().findElement(By.xpath("//ul[@class='dropdown-menu']//a[text()='" + AvailableService + "']")).click();

    }

    @And("I provide data as {string} street, {string} city, {string} state")
    public void iProvideDataAsStreetCityState(String myStreet, String myCity, String myState) {
        getDriver().findElement(By.xpath("//input[@id='search-input']")).click();
        getDriver().findElement(By.xpath("//input[@id='addressLineOne']")).sendKeys(myStreet);
        getDriver().findElement(By.xpath("//input[@id='cityOrZipCode']")).sendKeys(myCity);
        getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']")).click();
        Select selectState = new Select(getDriver().findElement(By.xpath("//select[@id='servicesStateSelect']")));
        selectState.selectByValue(myState);
        getDriver().findElement(By.xpath("//a[contains(text(),'Go to Results')]")).click();
    }

    @Then("I verify phone number is {string}")
    public void iVerifyPhoneNumberIs(String myPhNumber) {
        List<WebElement> result = getDriver().findElements(By.xpath("//div[@id='po-location-detail']"));
        for (WebElement element : result) {
            assertThat(element.getText().contains(myPhNumber));
        }

    }
}

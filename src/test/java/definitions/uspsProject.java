package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.data.Percentage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static definitions.Usps_support.webElementList;
import static definitions.Usps_support.xpathClick;
import static java.lang.System.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static support.TestContext.*;

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
            out.println("Option \"" + shape +"\" not implemented yet");
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
            out.println("Quantity should be numeric");
        }
    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePriceAndValidateCostIs(String myPrice) {
        getDriver().findElement(By.xpath("//input[@class='btn btn-pcalc btn-default']")).click();
        assertThat(getDriver().findElement(By.xpath("//div[@id='cost-0']")).getText()).contains(myPrice);
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

        getDriver().findElement(By.xpath("//div[@id='main_res']//span[contains(text(),'" + filteredResult +"')]/../..")).click();

        WebDriverWait waitForPage = new WebDriverWait(getDriver(),3);
        waitForPage.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath("//div[@id='maincontent']//a[@class='button--primary']") )));
    }

    @And("I click {string} button")
    public void iClickButton(String button) {
        String myXpath;

        //////////   create an "button" for "Passport appointment"
        if (button.equals("Ship Now")) {
            myXpath = "//div[@id='maincontent']//a[@class='button--primary']";
            getDriver().findElement(By.xpath(myXpath)).click();
        }
        if (button.equals("Schedule an Appointment")) {
            myXpath = "//a[@class='button--primary'][text()='Schedule an Appointment']";
            getDriver().findElement(By.xpath(myXpath)).click();
        }
        else {
            out.println("Your input \"" + button +"\" is not implemented yet.");
        }
    }

    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() {
        assertThat(getDriver().getTitle().contains("Sign In"));
    }


    @When("I go to {string} tab and {string} item")
    public void iGoToTabItem( String tab, String item) {

        //mouse over
        WebElement myHelp = getDriver().findElement(By.xpath("//a[@class='menuitem'][text()='" + tab + "']"));
        Actions mouseOver = new Actions(getDriver());
        mouseOver.moveToElement(myHelp).perform();
        //click desired item
        switch (item) {
            case "FAQs" -> getDriver().findElement(By.xpath("//div[@class='repos']//a[text()='" + item + "']")).click();
            case "Stamps" -> getDriver().findElement(By.xpath("//a[text()='Stamps']")).click();

        }
        //explicit wait

        //return to initial window

    }

    @And("I enter {string} into {string} search")
    public void iEnterIntoSearch(String searchString, String searchType) throws InterruptedException {
        String inputXpath = "";
        String clickXpath = "";

        if (searchType.equals("Help")) {
            inputXpath = "//input[@placeholder='Search for a topic']";
            clickXpath = "//button[@class='slds-button slds-button_brand search-button focus']";
        }
        else if (searchType.equals("Store")) {
            inputXpath = "//input[@id='global-header--search-track-store']";
            clickXpath = "//li[@class='menuheader active']//input[@class='input--search search--submit']";
        }
        else {
            out.println("\"" + searchType + "\" not implemented yet.");
        }

        WebElement myInput = getDriver().findElement(By.xpath(inputXpath));
        WebDriverWait myWait = new WebDriverWait(getDriver(),5);
        myWait.until(ExpectedConditions.visibilityOf(myInput));

        myInput.sendKeys(searchString);


        //pay attention for thr behavior of the button while you input data to search
        WebDriverWait myWaitForButton = new WebDriverWait(getDriver(),5);
        myWaitForButton.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.xpath(clickXpath))));
        getDriver().findElement(By.xpath(clickXpath)).click();
    }

    @Then("I verify that no results of {string} available in {string} search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String expectedString , String param) {
        String searchXpath = "";


        if (param.equals("Help")) {
            searchXpath = "//div[@class='resultBody']";
            List<WebElement> myList = getDriver().findElements(By.xpath(searchXpath));     //li[contains(@class,'kbResultStencil')]
            for (WebElement element : myList) {
                assertThat(element.getText()).doesNotContain(expectedString);

            }
        }
        else if (param.equals("Store")) {
            searchXpath = "//p[contains(text(),'not match any products.')]";
            WebDriverWait myWait= new WebDriverWait(getDriver(),5);
            myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchXpath)));
        }
        else {
            out.println("\"" + param + "\" not implemented yet.");
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
        //getDriver().manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        //System.out.println(selectState.selectByValue(myState).getValue()   );
        getDriver().findElement(By.xpath("//a[contains(text(),'Go to Results')]")).click();
    }

    @Then("I verify phone number is {string}")
    public void iVerifyPhoneNumberIs(String myPhNumber) {
        List<WebElement> result = getDriver().findElements(By.xpath("//div[contains(@class,'list-item-location popover-trigger')]"));
        List<String> phones = new ArrayList<>();
        StringBuilder allPhones = new StringBuilder();
        for (WebElement element : result) {
                getExecutor().executeScript("arguments[0].click();", element);
                WebDriverWait myWait = new WebDriverWait(getDriver(),3);
                myWait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath("//div[@class='phone-wrapper']"))));
                phones.add(getDriver().findElement(By.xpath("//div[@class='phone-wrapper']")).getText());
        }
        for (String phone : phones) {
            allPhones.append(phone);
        }
        assertThat(allPhones.toString()).contains(myPhNumber);

    }


    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {
        Actions mouseOver = new Actions(getDriver());
        mouseOver.moveToElement(getDriver().findElement(By.xpath("//a[@class='nav-first-element menuitem']"))).perform();
        getDriver().findElement(By.xpath("//*[contains(@alt,'Zip Code')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@class, 'zip-code-address')]")).click();
        assertThat(getDriver().findElement(By.xpath("//form[@id='zip-code-address-form']")).isDisplayed());
    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        Select myState = new Select(getDriver().findElement(By.xpath("//select[@id='tState']")));
        myState.selectByValue(state);
        getDriver().manage().timeouts().implicitlyWait(4000,TimeUnit.MILLISECONDS);
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();
    }

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String myZip) {
        WebElement element = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));
        WebDriverWait myWait = new WebDriverWait(getDriver(),3);
        myWait.until(ExpectedConditions.textToBePresentInElement(element, myZip));
        assertThat(element.getText()).contains(myZip);

    }
// scenatio Every door direct mail
    @When("I go to {string} under {string}")   //I go to "Every Door Direct Mail" under "Business"
    public void iGoToUnder(String subOption, String option) {
        Actions mouseOver = new Actions(getDriver());
        mouseOver.moveToElement(getDriver().findElement(By.xpath("//a[text()='" + option + "']"))).perform();

        getDriver().findElement(By.xpath("//a[text()='" + subOption + "']")).click();
        //a[@role='menuitem' and text()='Every Door Direct Mail']
    }

    @And("I search for {string}")    //I search for "4970 El Camino Real, Los Altos, CA 94022"
    public void iSearchFor(String address) {
        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(address);
        //input[@id='address']

        //push button
        getDriver().findElement(By.xpath("//button[@class='search-form-field-icon search-form-field-icon-search']")).click();
        //button[@class='search-form-field-icon search-form-field-icon-search']

        }


    @And("I click {string} on the map")    //"Show Table"
    public void iClickOnTheMap(String myElement) {
        //explicitly wait
        WebElement myButton = getDriver().findElement(By.xpath("//a[text()='" + myElement + "']"));
        WebDriverWait waitForMyButtonClickable = new WebDriverWait(getDriver(),10);
        waitForMyButtonClickable.until(ExpectedConditions.elementToBeClickable(myButton));  //   .visibilityOfElementLocated(By.xpath("//a[@class='route-table-toggle']")));  ////form[@id='formSearch']
        getExecutor().executeScript("arguments[0].click();", myButton);
    }

    @When("I click {string} on the table")   //Select All
    public void iClickOnTheTable(String label) {
        getExecutor().executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//a[contains(@class,'totalsArea')]")));

    }

    @And("I close modal window")
    public void iCloseModalWindow() {
        getDriver().findElement(By.xpath("//button[@id='dropOffDone']")).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() throws ParseException {
        /*List<WebElement> costList = new ArrayList<>(getDriver().findElements(By.xpath("//table[@class='dojoxGridRowTable']//td[@idx='7']")));
        double totalCostCalculated = 0;

        while (costList.size() < totalCount) {
            System.out.println("Actual elements size: " + costList.size());
            int lastIndex = costList.size() - 1;
            getActions().moveToElement(costList.get(lastIndex)).perform();
            costList = getDriver().findElements(costListSelector);

            WebDriverWait myWait = new WebDriverWait(getDriver(),5);
            myWait.until(ExpectedConditions.textToBePresentInElement(cost, "$"));
            totalCostCalculated += Double.parseDouble(cost.getText().replace("$", ""));


        double totalCost = Double.parseDouble(getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText());
        assertThat(totalCost).isCloseTo(totalCostCalculated, Percentage.withPercentage(1.5));
*/


        String totalCountString = getDriver().findElement(By.xpath("//a[contains(@class, 'totalsArea')]")).getText();
        int totalCount = Integer.parseInt(totalCountString.replaceAll("\\D*", ""));

        By costListSelector = By.xpath("//td[@idx='7']");
        List<WebElement> costList = getDriver().findElements(costListSelector);
        out.println("Expected elements size: " + totalCount);

        // dealing with infinite scroll
        while (costList.size() < totalCount) {
            out.println("Actual elements size: " + costList.size());
            int lastIndex = costList.size() - 1;
            getActions().moveToElement(costList.get(lastIndex)).perform();
            costList = getDriver().findElements(costListSelector);
        }
        out.println("Actual elements size: " + costList.size());

        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        double actualTotal = 0;
        for (WebElement cost : costList) {
            double costTotal = formatter.parse(cost.getText()).doubleValue();
            actualTotal += costTotal;
        }
        out.println("Actual total " + actualTotal);

        String expectedTotalString = getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText();
        double expectedTotal = Double.parseDouble(expectedTotalString);
        out.println("Expected total " + expectedTotal);

        assertThat(actualTotal).isCloseTo(expectedTotal, Percentage.withPercentage(1));




    }

    @And("I choose {string} {string} {string} filter {string}")
    public void iChooseCheckbox(String action, String group, String filter, String option) {
        String myUrl = getDriver().getCurrentUrl().substring(0,28);
        //System.out.println(myUrl);
        getExecutor().executeScript("arguments[0].click();",	getDriver().findElement(By.xpath(xpathClick(myUrl,filter,option))));
        //getDriver().findElement(By.xpath(clickXpath)).click();
    }

    @Then("I verify {int} items found in {string}")
    public void iVerifyItemsFound(int number, String option) {
        String myUrl = getDriver().getCurrentUrl().substring(0,28);

        //String clickXpath = "//div[@class='col-6 col-md-4 results-per-page ']";
        List<WebElement> elements = new ArrayList<WebElement>(getDriver().findElements(By.xpath(xpathClick(myUrl,null,option))));

        assertThat(elements.size()).isEqualTo(number);
    }


    @Then("I verify {string} filter {string}")
    public void /*boolean*/ iVerifyFilter(String expectedValue, String option) {
        String myUrl = getDriver().getCurrentUrl().substring(0,28);
        //webElementList(xpathClick(myUrl,null,option))

        //List<WebElement> myList = new ArrayList<WebElement>(getDriver().findElements(By.xpath(xpathClick(myUrl,null,option))));
        boolean b = false;
        for (WebElement element : webElementList(xpathClick(myUrl,null, option))) {
            out.println("Actual: " + element.getText() + " , Expected: " + expectedValue);
            if ((element.getText()).contains(expectedValue)) {
                b = true;
                break;
            }
        }
        out.println("Comparison result is: " + b);
        out.println();
        assertTrue(b);

    }



    @And("I verify that items below {int} dollars exists in {string}")
    public void iVerifyThatItemsBelowDollarsExistsIn(double doublePrice, String option) {
        String myUrl = getDriver().getCurrentUrl().substring(0,28);
        List<Double> prices = new ArrayList<>();
        for (WebElement element : webElementList(xpathClick(myUrl,null,option) + "//div[@class='results-product-preview-price']")) {
            //correct conversion to double needed!!!    "For input string: "1650.00  5500.00""
            out.println(Double.valueOf(element.getText().replaceAll("[a-zA-Z$,-]", "")));
            prices.add(Double.valueOf(element.getText().replaceAll("[a-zA-Z$,-]", "")));
        }
        double minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.size(); ) {
            if (prices.get(i) < minPrice) {
                minPrice = prices.get(i);
            }
        }
        assertThat(minPrice).isLessThan(doublePrice);

    }

    @And("I verify that {string} in {string} exists")
    public void verifyServiceExists(String expectedService, String option) {
        String myUrl = getDriver().getCurrentUrl();
        //out.println(myUrl);
        //out.println(getDriver().findElement(By.xpath(xpathClick(myUrl,null,option))).getText());
        assertThat(getDriver().findElement(By.xpath(xpathClick(myUrl,null,option))).getText()).contains(expectedService);
    }
}

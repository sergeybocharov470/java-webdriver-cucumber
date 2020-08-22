package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.util.*;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class JavaExercises {
    @Given("I input {string}")
    public void iInput(String name) {
        System.out.println(name);
    }

    @Then("Is a given word {string} polyndrome?")
    public void iReceivePolyndromeResult(String slovo) {
        int a = slovo.length();
        int i;
        String newslovo = slovo.substring(a);
        for (i = 1; i <= a; i++) {
            int j = a - i;
            newslovo = newslovo + slovo.substring(j, j + 1);
        }
        System.out.println("Initial word:  " + slovo);
        System.out.println("Reversed word: " + newslovo);

        if (newslovo.equalsIgnoreCase(slovo)) {
            System.out.println("You bet, \"" + slovo + "\" is polyndrome");
        } else {
            System.out.println("Enter another word");
        }
    }

    @Then("Does given string {string} contain {string}")
    public void givenStringContains(String mystring, String target) {
        Boolean a;
        if (mystring.contains(target)) {
            a = true;
        } else {
            a = false;
        }
        String b = String.valueOf(a);
        System.out.println(b);


    }

    @Then("Some string {string} contains certain consecutive chars")
    public void givenStringShouldNotContainCertainConsequitiveChars(String teststring) {
        String[] a = {"  ", "--", "__", ".."};
        int l = a.length;
        String res = "Test string contains ";
        String r1 = "";
        for (int i = 0; i < l; i++) {
            if (teststring.contains(a[i])) {
                r1 = r1 + a[i] + " ,";
            }
        }
        int k = r1.length();
        if (k > 1) {
            r1 = r1.substring(1, k - 1);
            res = res + r1 + "consecutive chars.";
        } else {
            res = res + " no consecutive chars.";
        }
        System.out.println(res);
    }

    @Then("I return the max value of a given {string} array")
    public void iReturnTheMaxValueOfAGivenArray(String a) {
        String[] arr = a.split(",");
        //System.out.println(arr[2]);
        int l = arr.length;
        int m = Integer.parseInt(arr[0]);
        for (int i = 1; i < l; i++) {
            int m1 = Integer.parseInt(arr[i]);
            if (m1 > m) {
                m = m1;
            }
        }
        System.out.println("Max value is " + m);
    }

    @Given("I perform actions with {string} and {string}")
    public void iPerformActionsWithAnd(String var1, String var2) {
        String t = "";
        String tu = "";
        String tv = "";
        String var1u = var1.toUpperCase();
        String var2u = var2.toUpperCase();
        if (!var1.equals(var2)) {
            t = "not ";
        }
        if (!var1u.equals(var2u)) {
            tu = "not ";
        }
        if (!var1.contains(var2)) {
            tv = "not ";
        }
        System.out.println("Values of variables as they are: " + var1 + ", " + var2);
        System.out.println("Uppercased values of variables: " + var1.toUpperCase() + ", " + var2.toUpperCase());
        System.out.println("Lengths of \"" + var1 + "\" and \"" + var2 + "\" are " + var1.length() + " and " + var2.length() + " respectively");
        System.out.println("Value of variable1: \"" + var1 + "\" is " + t + "equal to value of variable2: \"" + var2 + "\"");
        System.out.println("Value of uppercased variable1: \"" + var1u + "\" is " + tu + "equal to value of uppercased variable2: \"" + var2u + "\"");
        System.out.println("Value of variable1: \"" + var1 + "\" " + tv + "contains the value of variable2: \"" + var2 + "\"");
    }

    @Given("I have two integer variables {int} and {int} for calculations")
    public void iHaveTwoIntegerVariablesAndForCalculations(int num1, int num2) {
        System.out.println("Addition: " + (num1 + num2));
        System.out.println("Subtraction: " + (num1 - num2));
        System.out.println("Division: " + (num1 / num2));
        System.out.println("Multiplication: " + (num1 * num2));
        System.out.println("Modulus: " + (num1 % num2));
    }

    @Given("compare two colors {string} and {string}")
    public void compareTwoColorsAnd(String someColor, String notFavoriteColor) {
        System.out.println(someColor.equals(notFavoriteColor));
    }

    @Given("I jump to {string} start page")
    public void iJumpToStartPage(String url) {
        if (url.toLowerCase().equals("yahoo")) {
            System.out.println("https://www.yahoo.com");
        } else if (url.toLowerCase().equals("google")) {
            System.out.println("https://www.google.com");
        } else if (url.toLowerCase().equals("yandex")) {
            System.out.println("https://www.yandex.com");
        } else {
            System.out.println("https://www.something.net");
        }
    }

    @Given("I print if number {int} positive or negative")
    public void iPrintIfNumberNumberPositiveOrNegative(int number) {
        if (number >= 0) {
            System.out.println("Given number " + number + " is positive");
        } else {
            System.out.println("Given number " + number + " is negative");
        }
    }

    @And("I print the {int} -th day of week")
    public void iPrintTheDayThDayOfWeek(int day) {
        switch (day) {
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Tuesday");
            default -> System.out.println("No such a week day!");
        }
    }

    @Given("I print array")
    public void iPrintArray() {
        String[] a = {"pasta", "bread", "milk", "pork", "potato", "cookies"};
        System.out.println(a);
        System.out.println(a[0]);
        System.out.println(a[2]);

    }

    @Given("I work with a dynamic array")
    public void iWorkWithADynamicArray() {
        List<String> arrList = Arrays.asList("pasta", "bread", "milk", "pork", "potato", "cookies");
        List<String> da = List.of("plums", "pears", "kiwi", "melon");
        List<Character> chList = List.of('u', 'p', 'd', 'a', 't', 'e');
        List<String> oceans = new ArrayList<>();
        //List<String> list = List.of("plum", "apple", "kiwi"");
        System.out.println(arrList.size());
        for (String element : arrList) {
            System.out.println(element);
        }
        for (String element1 : da) {
            System.out.println(element1);
        }
        for (Character element2 : chList) {
            System.out.print(element2);
        }
        oceans.add("Atlantic");
        oceans.add("Pacific");
        oceans.add("Indian");//da.remove("melon");
        for (String element3 : oceans) {
            System.out.println(element3);
        }
    }


    @Given("I return true or false if {int} divisible by three or five")
    public void iReturnTrueOrFalseIfDivisibleByThreeOrFive(int myNum) {
        boolean evenFive = false;
        //boolean oddThree;
        int evenOdd = myNum % 2;
        int three = myNum % 3;
        int five = myNum % 5;
        if (evenOdd == 0 && five == 0) {
            evenFive = true;
        } else if (evenOdd != 0 && three == 0) {
            evenFive = true;
        }
        System.out.println(evenFive);
    }


    @Given("market prices")
    public void marketPrices() {
        int[] price = {2, 10, 7, 1, 6, 4, 3, 1, 5, 7, 9, 5};
        int profit = 0;
        int daysInTheMarket = price.length;
        for (int tDay = 0; tDay < daysInTheMarket - 1; tDay++) {
            int buyPrice = price[tDay];
            for (int nTD = tDay + 1; nTD < daysInTheMarket; nTD++) {
                int sellPrice = price[nTD];
                if ((sellPrice - buyPrice) > profit) {
                    profit = sellPrice - buyPrice;
                }
            }

        }
        System.out.println(profit);
    }


    @Given("I swap name {string} and lastname {string}")
    public void iSwapNameAndLastname(String name, String surname) {
        Map<String, String> myTestMap = new LinkedHashMap();
        myTestMap.put("name", name);
        myTestMap.put("surname", surname);
        System.out.println(myTestMap);
        String mySwap = myTestMap.get("name");
        myTestMap.put("name", myTestMap.get("surname")); //gets 'surname from map not from Cucumber step
        myTestMap.put("surname", mySwap);
        System.out.println(myTestMap);
        //info.put("name", info.get("surname"));

    }

    @Given("I go to {string} page")
    public void iGoToPage(String url) {
        if (url.equals("quote")) {
            url = "https://skryabin.com/market/quote.html";
        } else if (url.equals("yahoo")) {
            url = "https://www.yahoo.ru/";
        } else {
            System.out.println("No implementation for " + url);
        }
        getDriver().get(url);
    }


    @And("I print page details")
    public void iPrintPageDetails() {
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getPageSource());
    }

    @And("I go to previous page")
    public void iGoToPreviousPage() {
        getDriver().navigate().back();
    }

    @And("I go to next page")
    public void iGoToNextPage() {
        getDriver().navigate().forward();
    }

    @And("I navigate to {string} page")
    public void iNavigateToPage(String url) {
        if (url.equals("google")) {
            url = "https://www.google.com/";
        } else {
            System.out.println("No implementation for " + url);
        }
        getDriver().navigate().to(url);
    }

    @And("I change screen resolution to {string}")
    public void iChangeScreenResolutionTo(String resolution) throws InterruptedException {
        if (resolution.equals("phone")) {
            getDriver().manage().window().setSize(new Dimension(400, 768));
            Thread.sleep(1500);
        } else if (resolution.equals("tablet")) {
            getDriver().manage().window().setSize(new Dimension(800, 600));
            Thread.sleep(1500);
        } else if (resolution.equals("desktop")) {
            getDriver().manage().window().setSize(new Dimension(1024, 768));
            Thread.sleep(1500);
        } else {
            System.err.println("No such size. Screen was maximized");
            getDriver().manage().window().maximize();
            Thread.sleep(1500);
        }
    }

    @When("I print {string} into {string} input field")
    public void iPrintIntoInputField(String input, String field) {
        switch (field) {
            case "firstName" -> field = "//input[@id='firstName']";
            case "lastName" -> field = "//input[@id='lastName']";
            case "userName" -> field = "//input[@name='username']"; ////span[@class='ui-button-text'][text()='Save']
            case "email" -> field = "//input[@name='email']";
            case "password" -> field = "//input[@id='password']";
            case "confirmPassword" -> field = "//input[@id='confirmPassword']";
            default -> System.out.println("No such input field " + field);
        }
        getDriver().findElement(By.xpath(field)).sendKeys(input);
    }

    @And("I submit the page")
    public void iSubmitThePage() {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
    }

    @And("I clear {string} input field")
    public void iClearInputField(String field) {
        switch (field) {
            case "firstName" -> field = "//input[@id='firstName']";
            case "lastName" -> field = "//input[@id='lastName']";
            case "userName" -> field = "//input[@name='username']"; ////span[@class='ui-button-text'][text()='Save']
            case "email" -> field = "//input[@name='email']";
            case "password" -> field = "//input[@id='password']";
            case "confirmPassword" -> field = "//input[@id='confirmPassword']";
            default -> System.err.println("No such input field " + field);
        }
        getDriver().findElement(By.xpath(field)).clear();
    }

    @And("I delete {int} -th character from {string} input field")
    public void iDeleteThCharacterFromInputField(int character, String field) {
        switch (field) {
            case "firstName" -> field = "//input[@id='firstName']";
            case "lastName" -> field = "//input[@id='lastName']";
            case "userName" -> field = "//input[@name='username']";
            case "email" -> field = "//input[@name='email']";
            case "password" -> field = "//input[@id='password']";
            case "confirmPassword" -> field = "//input[@id='confirmPassword']";
            default -> System.err.println("No such input field " + field);
        }
        String email = getDriver().findElement(By.xpath(field)).getText();
        //field contains no text yet. Step can not be completed.
        System.out.println("\nemail : " + email);
    }

    @Then("I verify that page title is {string}")
    public void iVerifyThatPageTitleIs(String expectedTitle) {
        String actualTitle = getDriver().getTitle();
        assertThat(actualTitle).isEqualTo(expectedTitle);
    }


    @And("I verify that {string} field contains text {string}")
    public void iVerifyThatFieldContainsText(String field, String value) {
        switch (field) {
            case "name" -> field = "//b[@name='name']";
            case "username" -> field = "/b[@name='username']";
            case "email" -> field = "//b[@name='email']";
            case "password" -> field = "//b[@name='password']";
            case "agreedToPrivacyPolicy" -> field = "//b[@name='agreedToPrivacyPolicy']";
            case "thirdParty" -> field = "//span[@id='thirdPartyResponseMessage']";
            default -> System.err.println("\nNo such field implemented: " + field);
        }
        String actualValue = getDriver().findElement(By.xpath(field)).getText();
        if (field.equals("//b[@name='password']")) {
            value = "[entered]";
        }
        assertThat(actualValue).contains(value);
    }


    @And("I verify that Return Button is visible")
    public void iVerifyThatReturnButtonIsVisible() {
        getDriver().findElement(By.xpath("//button[@id='return']")).isDisplayed();
    }

    @Given("I accept an alert")
    public void iAcceptAnAlert() {
        System.out.println(getDriver().switchTo().alert().getText());
        getDriver().switchTo().alert().accept();

    }

    @Given("I dismiss an alert")
    public void iDismissAnAlert() {
        System.out.println(getDriver().switchTo().alert().getText());
        getDriver().switchTo().alert().dismiss();

    }

    @Given("I remove wovels from text")
    public void iRemoveWovelsFromText() {
        String text = "rebauiobtsabuoiessdewnfobaghioho";
        System.out.println(text);
        String vowels = "eioau";
        for (int i = 0; i < vowels.length(); i++) {
            String vowel = text.substring(i, i + 1);
            text.replace(vowel, " ");
        }
        System.out.println(text);

    }

    @Given("I swap an array elements {int} and {int}")
    public void iSwapAnArrayElementsAnd(int arg0, int arg1) {
        int[] myArr = {5, 2, 9, 7, 3};
        //    for (int element : myArr) {
        //        System.out.print(element + " ");
        //    }
        //    System.out.println();
        int tmp = myArr[arg0 - 1];
        myArr[arg0 - 1] = myArr[arg1 - 1];
        myArr[arg1 - 1] = tmp;
        //    for (int element : myArr) {
        //        System.out.print(element + " ");
        //    }
    }


    @Given("I input integer {int} and check if it is devisible")
    public String iInputIntegerAndCheckIfItIsDevisible(int myInt) {
        //    Scanner scanner = new Scanner(System.in);
        //    int n = scanner.nextInt();
        //    scanner.close();
        if (myInt % 3 == 0 && myInt % 4 == 0) {
            return "Divisible by 3 and 4";   // if your method should return something
            //     System.out.println("Divisible by 3 and 4");
        } else if (myInt % 4 == 0) {
            return ("Divisible by 4");
        } else if (myInt % 3 == 0) {
            System.out.println("Divisible by 3");
        } else {
            System.out.println("Input another integer");
        }
        return "smth";
    }

    @Given("I print all positive integers")
    public void iPrintAllPositiveIntegers() {
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            System.out.println(i);
        }
    }

    @Given("I print all integers")
    public void iPrintAllIntegers() {
        System.out.println(Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
            System.out.println(i);
        }
    }
}

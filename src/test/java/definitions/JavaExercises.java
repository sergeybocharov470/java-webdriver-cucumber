package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.maven.surefire.shared.utils.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.seleniumhq.jetty9.util.StringUtil;

import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static org.apache.maven.surefire.shared.utils.StringUtils.split;
import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class JavaExercises {
    @Given("I input {string}")
    public void iInput(String name) {
        System.out.println(name);
    }

    @Then("Is a given word {string} palindrome?")
    public void iReceivePalindromeResult(String slovo) {
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
            System.out.println("You bet, \"" + slovo + "\" is palindrome");
        } else {
            System.out.println("Enter another word");
        }
    }

    @Then("Does given string {string} contain {string}")
    public void givenStringContains(String myString, String target) {
        boolean a;
        if (myString.contains(target)) {
            a = true;
        } else {
            a = false;
        }
        String b = String.valueOf(a);
        System.out.println(b);


    }

    @Then("Some string {string} contains certain consecutive chars")
    public void givenStringShouldNotContainCertainConsequitiveChars(String testString) {
        String[] a = {"  ", "--", "__", ".."};
        int l = a.length;
        String res = "Test string contains ";
        String r1 = "";
        for (int i = 0; i < l; i++) {
            if (testString.contains(a[i])) {
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
        switch (url) {
            case "quote" -> url = "https://skryabin.com/market/quote.html";
            case "google" -> url = "https://www.google.com/";
            case "yahoo" -> url = "https://www.yahoo.com/";
            case "usps" -> url = "https://www.usps.com/";
            case "UnitConverters" -> url = "https://www.unitconverters.net/";
            case "calculator" -> url = "https://www.calculator.net/";
            case "ups" -> url = "https://www.ups.com/us/en/Home.page";
            default -> System.out.println("No implementation for " + url);
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








    @Given("I remove vowels from text {string}")
    public String iRemoveVowelsFromText(String text) {
      return text.replaceAll("[eioau]", "");
    }









    public String textWithoutVowels (String arg) {
        return arg.replaceAll("[eioau]", "");
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
        //System.out.println(Integer.MAX_VALUE);
        for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE; i++) {
            System.out.println(i);
        }
    }

@Given("I print all numbers from to {int}")
    public void iPrintAllNumbersFromTo(int n) {
        for (int i = 0; i <= n; i++) {
            System.out.print(i + " ");
        }

    }

    @Given("I print all numbers plus negative {int}")
    public void iPrintAllNumbersPlusNegative(int n) {
        for (int i = n; i <= -n; i++) {
            System.out.print(i + " ");
        }
    }

    @Given("I print all integer array")
    public void iPrintAllIntegerArray() {
        int[] myArrInt = {2, 12, 85, 3, 199, 47, 1, 800};
            for (int element : myArrInt) {
                System.out.print(element + " ");
            }
    }

    @Given("I print all even numbers from integer array")
    public void iPrintAllEvenNumbersFromIntegerArray() {
        int[] myArrInt = {2, 12, 85, 3, 199, 47, 1, 800};
        for (int element : myArrInt) {
            if (element%2 == 0) {
            System.out.print(element + " ");
            }
        }
    }

    @Given("I check if array is empty")
    public boolean iCheckIfArrayIsEmpty() {
        String[] myArr = {"de", "re"};
            if (myArr.length > 0) {
                return false;
            }
            else {
                return true;
            }
    }

    @Given("I print modified int array up to {int}")
    public void iPrintModifiedIntArrayUpTo(int n) {
        for (int i = 0; i <= n; i++) {
            if (i%3 == 0 && i%5 == 0) {
                System.out.print("FizzBuzz ");
            }
            else if (i%3 == 0) {
                System.out.print("Fizz ");
            }
            else if (i%5 == 0) {
                System.out.print("Buzz ");
            }
            else {
                System.out.print(i + " ");
            }
        }
    }

    @Given("String {string} to reverse")
    public void stringToReverse(String myString) {
        StringBuffer myBuffer = new StringBuffer(myString);
        myBuffer = myBuffer.reverse();
        System.out.println(myBuffer);
        System.out.println();
        for (int i = 1; i < myBuffer.length() ; i++) {
            if ((i+1)%3 == 0) {
                System.out.print(myBuffer.charAt(i));
            }
        }





    }

    @Given("I input two numbers for adding")
    public void iInputTwoNumbersForAdding() {

        int num1, num2, sum;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First Number: ");
        num1 = sc.nextInt();
        getDriver().manage().logs().get("browser");

        System.out.println("Enter Second Number: ");
        num2 = sc.nextInt();

        sc.close();
        sum = num1 + num2;
        System.out.println("Sum of these numbers: " + sum);

    }

    @Given("{int} and {int} print ranges")
    public void andPrintRanges(int first, int second) {


    }

    @Given("{string} sentence to reverse")
    public void sentenceToReverse(String initialString) {
        String[] myArray = initialString.split(" ");
        for (int i = myArray.length - 1; i >= 0; i--) {
            System.out.print(myArray[i] + " ");

        }

    }


    @Given("find two max numbers in array {string}")   //2 max numbers in an array
    public void findTwoMaxNumbersInArray(String myArr) {
        String[] strArr = myArr.split(",");
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
            for (int i = 0; i < strArr.length; i++) {
                if (Integer.parseInt(strArr[i]) > max1) {
                    max1 = Integer.parseInt(strArr[i]);
                }
            }
            for (int j = 0; j < strArr.length; j++) {
                if (Integer.parseInt(strArr[j]) > max2 && Integer.parseInt(strArr[j]) < max1) {
                    max2 = Integer.parseInt(strArr[j]);
                }

            }
        System.out.println("Maximum 1 is: " + Integer.toString(max1) + ".");
        System.out.println("Maximum 2 is: " + Integer.toString(max2) + ".");
    }

    @Given("find duplicates in array {string}")
    public boolean findDuplicatesArray(String givenString) {
        String[] myArr = givenString.split(",");
            for (int i = 0; i < myArr.length - 1; i++) {
               for (int j = i + 1; j < myArr.length; j++) {
                    if (myArr[j].equals(myArr[i])) {
                        //System.out.println(myArr[j]);
                        return true;
                        //break;
                    }

                }

            }
        //System.out.println();
        return false;
    }

    @Given("string to analyse {string}")  //baobab     //My string for extended character analysis
    public void stringToAnalyse(String myString) {
    // Creating a HashMap containing char
    // as a key and occurrences as  a value
    HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

    // Converting given string to char array

    char[] strArray = myString.toCharArray();

    // checking each char of strArray
        for (char c : strArray) {
        if (charCountMap.containsKey(c)) {

            // If char is present in charCountMap,
            // incrementing it's count by 1
            charCountMap.put(c, charCountMap.get(c) + 1);
        }
        else {

            // If char is not present in charCountMap,
            // putting this char to charCountMap with 1 as it's value
            charCountMap.put(c, 1);
        }
    }

        /*public void stringToAnalyse(String myString) {
        for (int i = 0; i < myString.length() - 1; i++) {
            int count = 1;
            String mySymbol = myString.substring(i, i + 1);
            for (int j = i + 1; j < myString.length(); j++) {
                if (myString.substring(j, j + 1).equals(myString.substring(i, i + 1))) {
                    count += 1;

                }

            }
            System.out.println("Symbol " + mySymbol + " appears " + count + " time(s)");
        }
    }  */

    // Printing the charCountMap
        for (Map.Entry entry : charCountMap.entrySet()) {
        System.out.println(entry.getKey() + " " + entry.getValue());
    }
}

    @Given("I return all natural numbers between {string} and {string}")
    public void iReturnAllNaturalNumbersBetweenAnd(String minNumber, String maxNumber) {
        int min = Integer.parseInt(minNumber);
        int max = Integer.parseInt(maxNumber);

        if (max < 2) {
            System.out.println("No natural numbers in the range between " + minNumber + " and " + maxNumber);

        }
        else  {

            if (min < 2) {min = 2;
            }


            String result = "";
            for (int i = min; i <= max; i++) {
                int count = 0;

                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        count = count + 1;
                    }

                }
                if (count == 0) {
                    result = result + i + " ";

                }

            }
            System.out.println("Natural numbers: " + result);
        }
    }


    @Given("I print evens and odds")
    public void iPrintEvensAndOdds() {


            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            int j = 3;
            while (j > 0) {
                Scanner scanner = new Scanner (System.in);
                String s = scanner.next();

                String evens = "";
                String odds = "";
                for (int i =0; i<s.length()-1; i++) {

                    if (i%2==0) {
                        evens = evens + s.substring(i+1,i+2);

                    }
                    else {
                        odds = odds + s.substring(i+1,i+2);
                    }

                }
                System.out.println(evens + " " + odds);
                System.out.println(s);
                //scanner.close();
                j--;
            }

    }
}

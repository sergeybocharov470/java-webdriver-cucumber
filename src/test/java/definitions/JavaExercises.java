package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.awt.*;

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
        String b=String.valueOf(a);
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
        System.out.println("Max value is "+m);
    }

    @Given("I perform actions with {string} and {string}")
    public void iPerformActionsWithAnd(String var1, String var2) {
        String t="";
        String tu="";
        String tv="";
        String var1u=var1.toUpperCase();
        String var2u=var2.toUpperCase();
        if(!var1.equals(var2)) {
            t="not ";
        }
        if(!var1u.equals(var2u)) {
            tu="not ";
        }
        if(!var1.contains(var2)) {
            tv="not ";
        }
        System.out.println("Values of variables as they are: "+var1+", "+var2);
        System.out.println("Uppercased values of variables: "+var1.toUpperCase()+", "+var2.toUpperCase());
        System.out.println("Lengths of \""+var1+"\" and \""+var2+"\" are "+var1.length()+" and "+var2.length()+" respectively");
        System.out.println("Value of variable1: \""+var1+"\" is "+t+"equal to value of variable2: \""+var2+"\"");
        System.out.println("Value of uppercased variable1: \""+var1u+"\" is "+tu+"equal to value of uppercased variable2: \""+var2u+"\"");
        System.out.println("Value of variable1: \""+var1+"\" " +tv+"contains the value of variable2: \""+var2+"\"");
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
        }
        else if (url.toLowerCase().equals("google")) {
            System.out.println("https://www.google.com");
        }
        else if (url.toLowerCase().equals("yandex")) {
            System.out.println("https://www.yandex.com");
        }
        else {
            System.out.println("https://www.something.net");
        }
    }

    @Given("I print if number {int} positive or negative")
    public void iPrintIfNumberNumberPositiveOrNegative(int number) {
        if (number >=0) {
            System.out.println("Given number " + number + " is positive");
        }
        else {
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
            case 6 -> System.out.println("Tuesday");
            default -> System.out.println("Sunday");
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
        List<String> da = List.of("pasta", "bread", "milk", "pork", "potato", "cookies");
    }
}

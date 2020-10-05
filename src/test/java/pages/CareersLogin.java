package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class CareersLogin extends CareersHeader {

    @FindBy(xpath="//label[@for='loginUsername']/../input")
        private WebElement username;

    @FindBy(xpath="//label[@for='loginPassword']/../input")
        private WebElement password;

    @FindBy(id="loginButton")
        private WebElement login;

    public CareersLanding login(String usernameValue, String passwordValue) {
        sendKeys(username, usernameValue);
        sendKeys(password, passwordValue);
        click(login);
        return new CareersLanding();
    }

    public CareersLanding login(Map<String, String> user) {
        sendKeys(username, user.get("email"));
        sendKeys(password, user.get("password"));
        click(login);
        return new CareersLanding();
    }



}
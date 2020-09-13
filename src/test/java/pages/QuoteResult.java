package pages;

import com.gargoylesoftware.htmlunit.html.HtmlDateInput;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static support.TestContext.getDriver;

public class QuoteResult {


    private String url;
    //private String legend;


    // lazy instantiation technique


    @FindBy(xpath = "//b[@name='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//b[@name='middleName']")
    private WebElement middleName;

    @FindBy(xpath = "//b[@name='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//b[@name='username']")
    private WebElement userName;

    @FindBy(xpath = "//b[@name='email']")
    private WebElement eMail;

    @FindBy(xpath = "//b[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//b[@name='name']")
    private WebElement fullName;

    @FindBy(xpath = "//b[@name='agreedToPrivacyPolicy']")
    private WebElement privacyPolicy;

    @FindBy(xpath = "//button[@id='return']")
    private WebElement returnButton;


    public QuoteResult() {
        PageFactory.initElements(getDriver(), this);
        url = "https://skryabin.com/market/quote.html";
        //legend = "Submitted Application";

    }

    public void openForm() {
        getDriver().get(url);
    }

    public String  getFirstName() {
        return firstName.getText();
    }

    public String getUserName() {
        return userName.getText();
    }

    public String getMiddleName() {
        return middleName.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getEMail() {
        return eMail.getText();
    }

    public String getFullName() {
        return fullName.getText();
    }

    public boolean getPrivacyPolicy() {
        return Boolean.parseBoolean(privacyPolicy.getText());
    }

    public void clickReturn() {
        returnButton.click();
    }









}

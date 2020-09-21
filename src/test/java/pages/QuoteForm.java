package pages;


        import org.openqa.selenium.NoSuchElementException;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.Select;

        import java.util.List;

        import static org.assertj.core.api.Assertions.assertThat;
        import static support.TestContext.getDriver;
        import static support.TestContext.getWait;
// import pages.QuoteResult;

public class QuoteForm {

    // fields

    private String url;
    private String title;

    // lazy instantiation technique

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPassword;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='middleName']")
    private WebElement middleNameInput;

    @FindBy(xpath = "//span[text()='Save']")
    private WebElement saveButton;

    @FindBy(name = "agreedToPrivacyPolicy")
    private WebElement privacyInput;

    @FindBy(id = "formSubmit")
    private WebElement submit;

    @FindBy(xpath = "//select[@name='carMake']/option")
    private List<WebElement> carMakersSelect;

    @FindBy(xpath = "//select[@name='countryOfOrigin']")
    private WebElement countriesInput;

    @FindBy(xpath = "//input[@name='phone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@name='gender']")
    private List<WebElement> genderSelect;

    @FindBy(xpath = "//input[@name='allowedToContact']")
    private WebElement isAllowedToContact;





    //error messages
    @FindBy(xpath = "//label[@id='name-error']")
    private WebElement nameErr;

    @FindBy(xpath = "//label[@id='username-error']")
    private WebElement usernameErr;

    @FindBy(xpath = "//label[@id='password-error']")
    private WebElement passwordErr;

    @FindBy(xpath = "//label[@id='email-error']")
    private WebElement emailErr;

    @FindBy(xpath = "//label[@id='agreedToPrivacyPolicy-error']")
    private WebElement agreeToPrivacyPolicyErr;

    @FindBy(xpath = "//label[@id='confirmPassword-error']")
    private WebElement confirmPasswordErr;

    // constructor

    public QuoteForm() {
        PageFactory.initElements(getDriver(), this);
        url = "https://skryabin.com/market/quote.html";
        title = "Get a Quote";
    }

    // methods

    public void open() {
        getDriver().get(url);
    }

    public void fillUsername(String value) {
        usernameInput.sendKeys(value);
    }

    public void fillEMail(String value) {
        emailInput.sendKeys(value);
    }

    public void fillBothPasswords(String value) {
        passwordInput.sendKeys(value);
        confirmPassword.sendKeys(value);
    }

    public void fillName(String firstNameValue, String middleNameValue, String lastNameValue) {
        nameInput.click();
        getWait(2).until(ExpectedConditions.elementToBeClickable(firstNameInput));
        firstNameInput.sendKeys(firstNameValue);
        middleNameInput.sendKeys(middleNameValue);
        lastNameInput.sendKeys(lastNameValue);
        saveButton.click();
    }

    public void fillName(String firstNameValue, String lastNameValue) {
        nameInput.click();
        firstNameInput.sendKeys(firstNameValue);
        lastNameInput.sendKeys(lastNameValue);
        saveButton.click();
    }



    public void agreeWithPrivacyPolicy() {
        if (!privacyInput.isSelected()) {
            privacyInput.click();
        }
    }

    public void selectCarMake(String make) {
        for (WebElement carMake : carMakersSelect) {
            //System.out.println(carMake.getText());
            if (carMake.getText().equals(make)) {
                carMake.click();
                break;
            }
        }
    }


    public void selectCountry(String state) {
        Select country = new Select(countriesInput);
        country.selectByValue(state);
    }

    public void fillPhone (String value) {
        phoneInput.sendKeys(value.replaceAll("\\D+", ""));
        //System.out.println(value.replaceAll("\\D+", ""));
    }

    public void setGender (String genderInput) {
        for (WebElement gender : genderSelect) {
            if (gender.getAttribute("value").equals(genderInput)) {
                gender.click();
            }
        }
    }

    public void submit() {

        submit.click();
    }

    public void allowToContact(String allowance) {
        if (allowance.toLowerCase().equals("yes") && !isAllowedToContact.isSelected()) {
            isAllowedToContact.click();
        }
        else if (!allowance.toLowerCase().equals("yes") && isAllowedToContact.isSelected()) {
            isAllowedToContact.click();
        }
    }

    public WebElement getErrorLabel(String inputField) {
        WebElement selectedErrorLabel = null;
        switch (inputField) {
            //case "name" -> name.getText();
            case "username" -> selectedErrorLabel = usernameErr;
            case "password" -> selectedErrorLabel = passwordErr;
            case "email" -> selectedErrorLabel = emailErr;
            case "confirmPassword" -> selectedErrorLabel = confirmPasswordErr;
            case "agreedToPrivacyPolicy" -> selectedErrorLabel = agreeToPrivacyPolicyErr;
            case "name" -> selectedErrorLabel = nameErr;
        }
        return selectedErrorLabel;
    }

    public String getErrorMessageText(String inputField) {
        return getErrorLabel(inputField).getText();
    }

    public boolean errorMessageVisible(String inputField) {
        //implemented using code pattern given by Slava at Day14
        boolean myResult;
        try {
            myResult = getErrorLabel(inputField).isDisplayed();
        } catch (NoSuchElementException e) {
            myResult = false;
        }
        return myResult;

    }
//////////////////////////////////////////////////////////////////
    public WebElement getInputField(String inputField) {
        WebElement selectedInputField = null;
        switch (inputField) {
            //case "name" -> name.getText();
            case "username" -> selectedInputField = usernameInput;
            case "password" -> selectedInputField = passwordInput;
            case "email" -> selectedInputField = emailInput;
            case "confirmPassword" -> selectedInputField = confirmPassword;
        }
        return selectedInputField;
    }

    public void printIntoInputField(String inputField, String inputValue) {
            getInputField(inputField).sendKeys(inputValue);
    }


    public void deleteFromInputField(String inputField) {
                getInputField(inputField).clear();
    }

    public String getName() {
        return nameInput.getAttribute("value");
     }



}
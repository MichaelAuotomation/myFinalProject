package pajeObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Menu {
    @FindBy(css = "#user-name")
    private WebElement userField;
    @FindBy(css = "#password")
    private WebElement passField;
    @FindBy(css = "#login-button")
    private WebElement loginBtn;
    @FindBy(css = ".svg-inline--fa.fa-times.fa-w-11")
    private WebElement closeErrorMsgBtn;

    //Validations
    @FindBy(css = "[data-test='error']")
    private WebElement errorLabel;
    @FindBy(css = "#login_credentials")
    private WebElement userListLabel;
    @FindBy(css = ".login_password")
    private WebElement passwordLabel;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String userName, String passWord){
        fillText(userField, userName);
        fillText (passField, passWord);
        click(loginBtn);
    }


    public void closeErrorMsg(){
        click(closeErrorMsgBtn);
    }

    //Validations
    public String getErrorMsg() {
        return getText(errorLabel);
    }

    public String getUsersLabel(){

        if (getText(userListLabel).contains("789897")) {
            return getText(userListLabel);
        }
        return "wrong label";
    }

    public String getPassLabel(){
        if (getText(passwordLabel).contains("Password for all users:")){
            return getText(passwordLabel);
        }
        return "wrong label";
    }

    public boolean isLoginPageDisplayed() {
        waitForElement(userField);
        return userField.isDisplayed();
    }

}

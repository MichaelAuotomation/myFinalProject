package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends Menu{
    @FindBy(css = "#first-name")
    private WebElement nameField;
    @FindBy(css = "#last-name")
    private WebElement lastNameField;
    @FindBy(css = "#postal-code")
    private WebElement zipField;
    @FindBy(css = "button > svg > path")
    private WebElement errorLabel;
    @FindBy(css = "#cancel")
    private WebElement cancelBtn;
    @FindBy(css = "#continue")
    private WebElement continueBtn;

    //Validations
    @FindBy(css = ".error-message-container.error")
    private WebElement errorMsg;
    @FindBy(css = ".title")
    private WebElement titleLabel;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }


    public void performCheckout(String name, String lastName, String zipCode){
        fillText(nameField, name);
        fillText(lastNameField, lastName);
        fillText(zipField, zipCode);
        click(continueBtn);
    }

    public void closeErrorLabel(){
        click(errorLabel);
    }

    public void btnCancel() {
        click(cancelBtn);
    }

    public void btnContinue() {
        click(continueBtn);
    }

    //Validations
    public String getErrorMsg() {
        return getText(errorMsg);
    }

    public boolean isYourInformationPage() {
        if (getText(titleLabel).equalsIgnoreCase("Checkout: Your Information")) {
            return true;
        }
        return false;
    }
}

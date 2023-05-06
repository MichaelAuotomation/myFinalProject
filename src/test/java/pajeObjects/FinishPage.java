package pajeObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinishPage extends Menu{
    @FindBy(css = "#back-to-products")
    private WebElement HomeBtn;

    //Validations
    @FindBy(css = ".complete-header")
    private WebElement titleMsg;
    @FindBy(css = ".complete-text")
    private WebElement descriptionMsg;
    @FindBy(css = ".title")
    private WebElement titleLabel;


    public FinishPage(WebDriver driver) {
        super(driver);
    }


    public void backHomeBtn(){
        click(HomeBtn);
    }

    //Validations
    public String printFinishMsg() {
        String msg = getText(titleMsg);
        System.out.println(msg);
        return msg;
    }

    public String getText() {
        String msg = getText(descriptionMsg);
        System.out.println(msg);
        return msg;
    }

    public boolean isFinishPage() {
        if (getText(titleLabel).equalsIgnoreCase("Checkout: Complete!")) {
            return true;
        }
        return false;
    }
}
package pajeObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage extends Menu{

    @FindBy(css = "#finish")
    private WebElement finishBtn;
    @FindBy(css = "#cancel")
    private WebElement cancelBtn;

    //Validations
    @FindBy(css = ".summary_info_label.summary_total_label")
    private WebElement priceTotal;
    @FindBy(css = ".title")
    private WebElement titleLabel;


    public OverviewPage(WebDriver driver) {
        super(driver);
    }


    public void btnFinish(){
        click(finishBtn);
    }

    public void cancel() {
        click(cancelBtn);
    }

    //Validations
    public String getTotal(){
        return getText(priceTotal);
    }

    public boolean isOverviewPage() {
        if (getText(titleLabel).equalsIgnoreCase("Checkout: Overview")) {
            return true;
        }
        return false;
    }

}
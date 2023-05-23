package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends BasePage{
    @FindBy(css = "#react-burger-menu-btn")
    private WebElement menuBtn;
    @FindBy(css = "#react-burger-cross-btn")
    private WebElement closeMenuBtn;
    @FindBy(css = "#inventory_sidebar_link")
    private WebElement allItemsBtn;
    @FindBy(css = "#about_sidebar_link")
    private WebElement aboutBtn;
    @FindBy(css = "#logout_sidebar_link")
    private WebElement logoutBtn;
    @FindBy(css = "#reset_sidebar_link")
    private WebElement resetAppBtn;
    @FindBy(css = ".shopping_cart_link")
    private WebElement cartBtnFromCompletePage;
    @FindBy(css = ".shopping_cart_link")
    private WebElement openCartBtn;
    @FindBy(css = ".app_logo")
    private WebElement headlineLabel;

    //Validation


    public Menu(WebDriver driver) {
        super(driver);
    }


    public void openCart(){
        click(openCartBtn);
    }

    public void openCartFromCompletePage() {
        click(cartBtnFromCompletePage);
    }

    public void openMenu() {
        click(menuBtn);
    }

    public void closMenu() {
        click(closeMenuBtn);
    }

    public void clickAllItemsBtn() {
        click(allItemsBtn);
    }

    public void clickAboutBtn() {
        click(aboutBtn);
    }

    public void clickLogoutBtn() {
        click(logoutBtn);
    }

    public void clickResetAppBtn() {
        click(resetAppBtn);
    }

    public String getHeadline(){
        return getText(headlineLabel);
    }

    //Validation
    public boolean isMenuOpened() {
        return getText(allItemsBtn).equalsIgnoreCase("ALL ITEMS");
    }

}

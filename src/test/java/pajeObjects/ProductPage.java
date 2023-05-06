package pajeObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends Menu {
    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    private WebElement addBtn;
    @FindBy(css = ".back-image")
    private WebElement backToProductsBtn;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    private WebElement removeBtn;


    public ProductPage(WebDriver driver) {
        super(driver);
    }


    public void addToCart(){
        click(addBtn);
    }

    public void removeFromCart(){
        click(removeBtn);
    }

    public void backToProducts(){
        click(backToProductsBtn);
    }

}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends Menu {

    @FindBy(css = ".inventory_item_name")
    private List<WebElement> list;
    @FindBy(css = ".product_sort_container")
    private WebElement sort;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    private WebElement removeBtn;

    //Validations
    @FindBy(css = ".shopping_cart_link")
    private WebElement numOfProductsLabel;
    @FindBy(css = ".title")
    private WebElement pageTitle;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }


    public void chooseProduct(String name){
        //List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement el: list)
            if (getText(el).equalsIgnoreCase(name)){
                click(el);
                break;
        }
    }

    public void addToCart(String name){
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item"));
        for (WebElement el : list) {
            WebElement titleEl = el.findElement(By.cssSelector(".inventory_item_name"));
            if (titleEl.getText().equalsIgnoreCase(name)){
               WebElement addBtn = el.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
               click(addBtn);
               break;
            }
        }
    }

    public void productSort(int index){
    select(sort,index);
    }

    public void removeFromCart(String name){
        List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item"));
        for (WebElement el : list) {
            WebElement titleEl = el.findElement(By.cssSelector(".inventory_item_name"));
            if (titleEl.getText().equalsIgnoreCase(name)){
                WebElement removeBtn = el.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory"));
                click(removeBtn);
                break;
            }
        }
    }

    public void removeProductBtn() {
        click(removeBtn);
    }

    //Validations
    public int getNumOfProducts(){
        String s = getText(numOfProductsLabel);
        int num = Integer.parseInt(s);
        return num;
    }

    public boolean isProductsPage() {
        return getText(pageTitle).equalsIgnoreCase("Products");
    }

}

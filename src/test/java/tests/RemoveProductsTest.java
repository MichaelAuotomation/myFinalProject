package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pajeObjects.ProductPage;
import pajeObjects.ProductsPage;
import pajeObjects.YourCartPage;
import utils.Configuration;

public class RemoveProductsTest extends BaseTest{
    private final long twoSeconds = 2000;


    @Test
    public void tc01_removeFromProductPage() {
        login();
        addToCartFromProductPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        ProductPage pp = new ProductPage(driver);
        pp.removeFromCart();
        sleep(twoSeconds);
        logout();
    }

    @Test
    public void tc02_removeFromProductsPage() {
        login();
        addToCartFromProductPage(Configuration.SAUCE_LABS_ONESIE);
        ProductsPage psp = new ProductsPage(driver);
        psp.removeProductBtn();
        sleep(twoSeconds);
        logout();
    }

    @Test
    public void tc03_removeFromYourCartPage() {
        login();
        addToCartFromProductPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        ProductsPage psp = new ProductsPage(driver);
        psp.openCart();
        sleep(twoSeconds);
        YourCartPage ycp = new YourCartPage(driver);
        ycp.removeProduct();
        sleep(twoSeconds);
        logout();
    }

    //Methods
    //Add To Cart From Product Page
    public void addToCartFromProductPage(String productName) {
        ProductsPage psp = new ProductsPage(driver);
        sleep(twoSeconds);
        psp.chooseProduct(productName);
        sleep(twoSeconds);
        ProductPage pp = new ProductPage(driver);
        pp.addToCart();
        pp.backToProducts();
        psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
        sleep(twoSeconds);
    }

    // Add To Cart From Products Page
    public void addToCartFromProductsPage(String productName) {
        ProductsPage psp = new ProductsPage(driver);
        sleep(twoSeconds);
        psp.addToCart(productName);
        sleep(twoSeconds);
    }

}


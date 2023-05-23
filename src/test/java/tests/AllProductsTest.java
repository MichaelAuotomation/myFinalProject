package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.Configuration;

public class AllProductsTest extends BaseTest{
    private final long twoSeconds = 2000;


    @Test
    public void tc01_buyAllProductsFromProductsPage() {
        login();
        ProductsPage psp = new ProductsPage(driver);
        psp.addToCart(Configuration.SAUCE_LABS_BACKPACK);
        psp.addToCart(Configuration.SAUCE_LABS_ONESIE);
        psp.addToCart(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        psp.addToCart(Configuration.SAUCE_LABS_BIKE_LIGHT);
        psp.addToCart(Configuration.SAUCE_LABS_FLEECE_JACKET);
        psp.addToCart(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        sleep(twoSeconds);
        paymentProcess();
        logout();
    }

    @Test
    public void tc02_buyAllProductsFromProductPage() {
        login();
        addToCartFromProductPage(Configuration.SAUCE_LABS_BACKPACK);
        addToCartFromProductPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        addToCartFromProductPage(Configuration.SAUCE_LABS_ONESIE);
        addToCartFromProductPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        addToCartFromProductPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        addToCartFromProductPage(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        paymentProcess();
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
    //Payment process
    public void paymentProcess() {
        ProductsPage psp = new ProductsPage(driver);
        psp.openCart();
        sleep(twoSeconds);

        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        sleep(twoSeconds);

        CheckoutPage cop = new CheckoutPage(driver);
        cop.performCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        sleep(twoSeconds);

        OverviewPage ovp = new OverviewPage(driver);
        ovp.btnFinish();
        sleep(twoSeconds);

        FinishPage fp = new FinishPage(driver);
        fp.printFinishMsg();
        String expected = Configuration.COMPLETE_MESSAGE;
        String actual = fp.printFinishMsg();
        Assert.assertEquals(actual, expected);
    }

}

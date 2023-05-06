package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pajeObjects.*;
import utils.Configuration;

public class ProductsTest extends BaseTest {
    private final long twoSeconds = 2000;


    @Test(description = "Check you on products page")
    public void tc01_login() {
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.USER, Configuration.PASSWORD);
        ProductsPage pp = new ProductsPage(driver);
        Assert.assertTrue(pp.isProductsPage());
        sleep(twoSeconds);
    }

    @Test(description = "Add and remove products")
    public void tc02_addProducts() {
        ProductsPage psp = new ProductsPage(driver);
        psp.productSort(3);
        psp.chooseProduct(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        ProductPage pp = new ProductPage(driver);
        pp.backToProducts();
        sleep(twoSeconds);

        psp = new ProductsPage(driver);
        psp.addToCart(Configuration.SAUCE_LABS_ONESIE);
        sleep(twoSeconds);

        psp.addToCart(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        psp.addToCart(Configuration.SAUCE_LABS_FLEECE_JACKET);
        //Remove products from cart - Products page
        psp.removeFromCart(Configuration.SAUCE_LABS_ONESIE);
        //Add another product you will transfer to product page - Product page
        psp.chooseProduct(Configuration.SAUCE_LABS_BIKE_LIGHT);
        pp = new ProductPage(driver);
        //Add items
        pp.addToCart();
        pp.backToProducts();
        //Choose another product from the list (you will go to the product page)
        psp = new ProductsPage(driver);
        psp.chooseProduct(Configuration.SAUCE_LABS_BACKPACK);
        pp = new ProductPage(driver);
        //Add items
        pp.addToCart();
        //Remove items
        pp.removeFromCart();
        pp.backToProducts();

    }

    @Test
    public void tc03_paymentProcess() {
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

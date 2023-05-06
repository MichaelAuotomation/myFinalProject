package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pajeObjects.*;
import utils.Configuration;

public class ProductTest extends BaseTest {
    private final long twoSeconds = 2000;


    @Test
    public void tc01_login() {
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.USER, Configuration.PASSWORD);
        ProductsPage psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
        sleep(twoSeconds);
    }

    @Test
    public void tc02_addProducts() {
        ProductsPage psp = new ProductsPage(driver);
        psp.chooseProduct(Configuration.SAUCE_LABS_BIKE_LIGHT);
        sleep(twoSeconds);

        ProductPage pp = new ProductPage(driver);
        pp.addToCart();
        pp.backToProducts();
        psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
        sleep(twoSeconds);

        psp = new ProductsPage(driver);
        psp.chooseProduct(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        sleep(twoSeconds);

        pp = new ProductPage(driver);
        pp.addToCart();
        pp.backToProducts();
        psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
    }

    @Test
    public void tc03_payment() {
        ProductsPage psp = new ProductsPage(driver);
        psp.openCart();
        YourCartPage ycp = new YourCartPage(driver);
        ycp.isYourCartPage();
        sleep(twoSeconds);

        ycp = new YourCartPage(driver);
        ycp.checkout();
        CheckoutPage cop = new CheckoutPage(driver);
        cop.isYourInformationPage();
        sleep(twoSeconds);

        cop = new CheckoutPage(driver);
        cop.performCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        OverviewPage ovp = new OverviewPage(driver);
        ovp.isOverviewPage();
        sleep(twoSeconds);

        ovp = new OverviewPage(driver);
        ovp.btnFinish();
        FinishPage fp = new FinishPage(driver);
        fp.isFinishPage();
        sleep(twoSeconds);

        fp = new FinishPage(driver);
        fp.printFinishMsg();
        String expected = Configuration.COMPLETE_MESSAGE;
        String actual = fp.printFinishMsg();
        Assert.assertEquals(actual, expected);
    }

}

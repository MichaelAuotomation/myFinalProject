package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pajeObjects.*;
import utils.Configuration;

public class YourCartTest extends BaseTest{
    private final long twoSeconds = 2000;


    @Test
    public void tc01_continueShoppingButton() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        openCart();
        YourCartPage ycp = new YourCartPage(driver);
        ycp.continueShopping();
        ProductsPage psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        paymentProcess();
        logout();
    }

    @Test
    public void tc02_backHomeButton() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BOLT_T_SHIRT);
        paymentProcess();
        FinishPage fp = new FinishPage(driver);
        fp.backHomeBtn();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        logout();
    }

    @Test
    public void tc03_cancelButtonFromYourInformationPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_ONESIE);
        openCart();
        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        sleep(twoSeconds);
        CheckoutPage cop = new CheckoutPage(driver);
        cop.btnCancel();
        ycp.isYourCartPage();
        logout();
    }

    @Test
    public void tc04_cancelButtonFromOverviewPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BIKE_LIGHT);
        openCart();
        YourCartPage yourCartPage = new YourCartPage(driver);
        yourCartPage.checkout();
        sleep(twoSeconds);

        CheckoutPage cop = new CheckoutPage(driver);
        cop.performCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        sleep(twoSeconds);

        OverviewPage ovp = new OverviewPage(driver);
        ovp.cancel();
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());

        logout();
    }

    @Test
    public void tc05_clickOnCartButtonFromYIPage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        openCart();

        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        sleep(twoSeconds);

        CheckoutPage cop = new CheckoutPage(driver);
        cop.openCart();
        ycp.isYourCartPage();

        logout();
    }

    @Test
    public void tc06_clickOnCartButtonFromOverviewPage() {
        login();
        addToCartFromProductsPage(Configuration.TEST_ALLTHETHINGS_T_SHIRT_RED);
        openCart();

        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        sleep(twoSeconds);

        CheckoutPage cop = new CheckoutPage(driver);
        cop.performCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, Configuration.POSTAL_CODE);
        sleep(twoSeconds);

        OverviewPage ovp = new OverviewPage(driver);
        ovp.openCart();
        ycp.isYourCartPage();

        logout();
    }

    @Test
    public void tc07_clickOnCartButtonFromCompletePage() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        openCart();

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
        fp.openCartFromCompletePage();
        ycp.isYourCartPage();
        logout();
    }


    //Methods
    // Add To Cart From Products Page
    public void addToCartFromProductsPage(String productName) {
        ProductsPage psp = new ProductsPage(driver);
        sleep(twoSeconds);
        psp.addToCart(productName);
        sleep(twoSeconds);
    }
    //Open the cart
    public void openCart() {
        ProductsPage psp = new ProductsPage(driver);
        psp.openCart();
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

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckoutPage;
import pageObjects.ProductsPage;
import pageObjects.YourCartPage;
import utils.Configuration;

public class CheckOutTest extends BaseTest {
    private final long twoSeconds = 2000;


    @Test
    public void tc01_fillOnlyFirstName() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_ONESIE);
        paymentProcess();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.performCheckout(Configuration.FIRST_NAME, "", "");
        sleep(twoSeconds);
        Assert.assertEquals(checkoutPage.getErrorMsg(), Configuration.LAST_NAME_ERROR);
        logout();
    }

    @Test
    public void tc02_fillOnlyLastName() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_FLEECE_JACKET);
        paymentProcess();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.performCheckout("", Configuration.LAST_NAME, "");
        sleep(twoSeconds);
        Assert.assertEquals(checkoutPage.getErrorMsg(), Configuration.FIRST_NAME_ERROR);
        logout();
    }

    @Test
    public void tc03_emptyPostalCode() {
        login();
        addToCartFromProductsPage(Configuration.SAUCE_LABS_BACKPACK);
        paymentProcess();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.performCheckout(Configuration.FIRST_NAME, Configuration.LAST_NAME, "");
        sleep(twoSeconds);
        Assert.assertEquals(checkoutPage.getErrorMsg(), Configuration.POSTAL_CODE_ERROR);
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

    //Payment process
    public void paymentProcess() {
        ProductsPage psp = new ProductsPage(driver);
        psp.openCart();
        sleep(twoSeconds);

        YourCartPage ycp = new YourCartPage(driver);
        ycp.checkout();
        sleep(twoSeconds);

        CheckoutPage cop = new CheckoutPage(driver);
        Assert.assertTrue(cop.isYourInformationPage());

    }

}

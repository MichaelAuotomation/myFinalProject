package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pajeObjects.Menu;
import pajeObjects.ProductsPage;

public class MenuTest extends BaseTest {
    private final long fourSeconds = 4000;

    @Test
    public void tc01_openAboutPage() {
        login();
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(fourSeconds);
        menu.clickAboutBtn();
        sleep(fourSeconds);
        driver.navigate().back();
        menu.closMenu();
        ProductsPage psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
        sleep(fourSeconds);
        logout();
    }

    @Test
    public void tc02_logout() {
        login();
        logout();
    }

    @Test
    public void tc03_clickAllItems() {
        login();
        ProductsPage psp = new ProductsPage(driver);
        Assert.assertTrue(psp.isProductsPage());
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(fourSeconds);
        menu.clickAllItemsBtn();
        menu.closMenu();
        sleep(fourSeconds);
        Assert.assertTrue(psp.isProductsPage());
        sleep(fourSeconds);
        logout();
    }

    @Test
    public void tc04_openAndCloseMenu() {
        login();
        Menu menu = new Menu(driver);
        menu.openMenu();
        menu.isMenuOpened();
        sleep(fourSeconds);
        menu.closMenu();
        sleep(fourSeconds);
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPage());
        logout();
    }

}

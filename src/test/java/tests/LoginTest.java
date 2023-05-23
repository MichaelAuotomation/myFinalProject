package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utils.Configuration;

public class LoginTest extends BaseTest {
    private final long twoSeconds = 2000;


    @Test(description = "Failed login - empty user")
    public void tc01_failedLogin(){
        LoginPage lp = new LoginPage(driver);
        lp.login("", Configuration.PASSWORD);
        sleep(twoSeconds);
        String expected = Configuration.WRONG_USER_ERROR;
        String actual = lp.getErrorMsg();
        Assert.assertEquals(expected, actual);
        driver.navigate().refresh();
    }

    @Test (description = "Failed login - no User")
    public void tc02_failedLogin() {
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.USER, "");;
        sleep(twoSeconds);
        String expected = Configuration.WRONG_PASSWORD_ERROR;
        String actual = lp.getErrorMsg();
        Assert.assertEquals(expected, actual);
        driver.navigate().refresh();
    }

    @Test (description = "Failed login - no Pass")
    public void tc03_failedLogin() {
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.USER, "");
        sleep(twoSeconds);
        String expected = Configuration.WRONG_PASSWORD_ERROR;
        String actual = lp.getErrorMsg();
        Assert.assertEquals(expected, actual);
        driver.navigate().refresh();
    }

    @Test (description = "Failed login - invalid credentials")
    public void tc04_failedLogin(){
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.WRONG_USER, Configuration.WRONG_PASSWORD);
        sleep(twoSeconds);
        String expected = Configuration.WRONG_USER_AND_PASSWORD_ERROR;
        String actual = lp.getErrorMsg();
        Assert.assertEquals(expected, actual);
        driver.navigate().refresh();
    }

    @Test (description = "Valid login")
    public void tc05_login(){
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.USER, Configuration.PASSWORD);
        sleep(twoSeconds);
        ProductsPage pp = new ProductsPage(driver);
        Assert.assertTrue(pp.isProductsPage());
        driver.navigate().refresh();
    }

}

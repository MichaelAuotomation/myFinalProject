package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pajeObjects.LoginPage;
import pajeObjects.Menu;
import pajeObjects.ProductsPage;
import utils.Configuration;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    WebDriver driver;
    private final long twoSeconds = 2000;



    @BeforeClass
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.get(Utils.readProperty("url"));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method will run after wach test,
     * it will take screenshot only for tests that failed
     */
    @AfterMethod
    public void failedTest(ITestResult result) {
        //check if the test failed
        if (result.getStatus() == ITestResult.FAILURE ){
            TakesScreenshot ts = (TakesScreenshot)driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./ScreenShots/"+result.getName()+".jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //result.getname() method will give you current test case name.
            //./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
        }
    }

    public void login() {
        LoginPage lp = new LoginPage(driver);
        lp.login(Configuration.USER, Configuration.PASSWORD);
        sleep(2000);
        ProductsPage pp = new ProductsPage(driver);
        Assert.assertTrue(pp.isProductsPage());
    }
    public void logout() {
        Menu m = new Menu(driver);
        m.openMenu();
        m.isMenuOpened();
        sleep(twoSeconds);
        m.clickLogoutBtn();
        sleep(twoSeconds);
        LoginPage lp = new LoginPage(driver);
        Assert.assertTrue(lp.isLoginPageDisplayed());
        driver.navigate().refresh();
    }

}

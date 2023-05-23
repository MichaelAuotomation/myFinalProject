package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.Configuration;

public class LoginFailedTestsWithDDT extends  BaseTest{

    /*
     * In order to use DDT (Data Driven Testing) you should:
     * 1. create method to read from with @DataProvider annotation (in this example it called getData())
     * 2. add dataProvider="getData" parameter to the @Test
     */
    @Test(dataProvider = "getData", description = "use incorrect login information")
    public void tc01_loginWrongUserAndPass(String user, String password) {
        //login failed
        LoginPage lp = new LoginPage(driver);
        //Using the variables we get from the @DataProvider method
        lp.login(user, password);
        sleep(1000);

        //Should check that we get the right message
        String expected = Configuration.WRONG_USER_AND_PASSWORD_ERROR;
        String actual = lp.getErrorMsg();
        Assert.assertEquals(actual, expected);
        driver.navigate().refresh();
    }

    /*
     * This is example of a method that return 2 dimensional object (like a table)
     * Using the @DataProvider the method above will get the parameters for each iteration.
     */
    @DataProvider
    public Object[][] getData() {
        Object[][] myData;
        myData = new Object[][]{
                {"$user$", "secret_sauce"},
                {"standard_user", "456"},
                {"locked_out_user", "1#444"},
                {"Dani", "123456878"},
        };
        return myData;
    }
}

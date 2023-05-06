package utils;

import static utils.Utils.readProperty;

public class Configuration {

    //Product Names
    public final static String SAUCE_LABS_BACKPACK = readProperty("sauceLabsBackpack");
    public final static String SAUCE_LABS_BOLT_T_SHIRT = readProperty("sauceLabsBoltT-Shirt");
    public final static String SAUCE_LABS_ONESIE = readProperty("sauceLabsOnesie");
    public final static String SAUCE_LABS_BIKE_LIGHT = readProperty("sauceLabsBikeLight");
    public final static String SAUCE_LABS_FLEECE_JACKET = readProperty("sauceLabsFleeceJacket");
    public final static String TEST_ALLTHETHINGS_T_SHIRT_RED = readProperty("test.allTheThings()T-Shirt(Red)");

    //Credentials
    public final static String USER = readProperty("user");
    public final static String PASSWORD = readProperty("password");
    public final static String WRONG_USER = readProperty("wrongUser");
    public final static String WRONG_PASSWORD = readProperty("wrongPassword");

    public final static String FIRST_NAME = readProperty("firstName");
    public final static String LAST_NAME = readProperty("lastName");
    public final static String POSTAL_CODE = readProperty("postalCode");
    public final static String COMPLETE_MESSAGE = readProperty("completeMessage");

    //Error Messages
    public final static String WRONG_USER_ERROR = readProperty("userNameError");
    public final static String WRONG_PASSWORD_ERROR = readProperty("passwordError");
    public final static String WRONG_USER_AND_PASSWORD_ERROR = readProperty("wrongUserAndPasswordError");

    public final static String LAST_NAME_ERROR = readProperty("lastNameError");
    public final static String FIRST_NAME_ERROR = readProperty("firstNameError");
    public final static String POSTAL_CODE_ERROR = readProperty("postalCodeError");

}

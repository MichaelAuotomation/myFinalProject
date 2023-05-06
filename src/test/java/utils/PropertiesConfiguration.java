package utils;

import java.util.Properties;

public class PropertiesConfiguration {
    private static Properties prop;

    public static Properties initiateProperties() {
        if (prop == null) {
            prop = new Properties();
        }
        return prop;
    }
}

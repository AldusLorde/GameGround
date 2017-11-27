package aldus.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}

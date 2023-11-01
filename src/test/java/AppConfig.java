import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String PROPERTY_FILE = "config.properties";
    private Properties properties;

    public AppConfig() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("base_url");
    }

    public String getUsername1() {
        return properties.getProperty("username1");
    }

    public String getPassword1() {
        return properties.getProperty("password1");
    }
    public String getUsername2() {
        return properties.getProperty("username2");
    }

    public String getPassword2() {
        return properties.getProperty("password2");
    }
}

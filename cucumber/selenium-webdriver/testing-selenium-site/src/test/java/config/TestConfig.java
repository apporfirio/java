package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final String RESOURCES_DIRECTORY_RELATIVE_PATH = "src/test/resources";
    private static final String TEST_PROPERTIES_FILE_NAME = "test-config.properties";

    private static Properties properties = readProperties();

    public static String getSeleniumWebsiteURL() {
        return readProperty("selenium-website-url");
    }

    public static WebDriverAlias getWebDriverAlias() {
        return WebDriverAlias.valueOf(readProperty("webdriver-alias").toUpperCase());
    }

    public static boolean checkWebDriverOnFullScreenMode() {
        return Boolean.parseBoolean(readProperty("webdriver-on-full-screen-mode"));
    }

    public static int getWebDriverTimeout() {
        return Integer.valueOf(readProperty("webdriver-get-url-timeout"));
    }

    public static String getChromeWebDriverAbsolutePath() {
        return readProperty("absolute-path-to-chrome-webdriver");
    }

    public static String getFirefoxWebDriverAbsolutePath() {
        return readProperty("absolute-path-to-firefox-webdriver");
    }

    public static String getSeleniumPreviousReleasesURL() {
        return readProperty("selenium-previous-releases-url");
    }

    public static String getSeleniumOfficialUserGroupURL() {
        return readProperty("selenium-official-user-group-url");
    }

    private static String readProperty(String propertyName) {
        if (properties == null)
            return "";
        else
            return properties.getProperty(propertyName);
    }

    private static Properties readProperties() {
        try {
            InputStream input = new FileInputStream(RESOURCES_DIRECTORY_RELATIVE_PATH + "/" + TEST_PROPERTIES_FILE_NAME);

            Properties props = new Properties();
            props.load(input);

            return props;
        }
        catch (Exception e) {
            throw new RuntimeException("Não foi possível ler o arquivo '" + TEST_PROPERTIES_FILE_NAME + "': " + e);
        }
    }

}

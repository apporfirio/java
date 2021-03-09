package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConfig {

    public static WebDriver getWebDriver() {
        WebDriver driver;
        WebDriverAlias webDriverAlias = TestConfig.getWebDriverAlias();

        switch (webDriverAlias) {
            case CHROME: {
                System.setProperty("webdriver.chrome.driver", TestConfig.getChromeWebDriverAbsolutePath());
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                System.setProperty("webdriver.gecko.driver", TestConfig.getFirefoxWebDriverAbsolutePath());
                driver = new FirefoxDriver();
                break;
            }
            default:
                throw new RuntimeException("WebDriver não encontrado para browser de nome igual a '" + webDriverAlias + "'");
        }

        if (TestConfig.checkWebDriverOnFullScreenMode()) {
            driver.manage().window().maximize();
        }

        return driver;
    }

}

package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConfig {

    public static WebDriver getWebDriver() {
        WebDriver driver;
        TargetBrowserName targetBrowserName = TestConfig.getTargetBrowserName();

        switch (targetBrowserName) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("WebDriver não encontrado para browser de nome igual a '" + targetBrowserName + "'");
        }

        if (TestConfig.checkWebDriverOnFullScreenMode()) {
            driver.manage().window().maximize();
        }

        return driver;
    }

}

package components;

import config.TestConfig;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL();
    }

    @Override
    public String getTitle() {
        return "SeleniumHQ Browser Automation";
    }
}

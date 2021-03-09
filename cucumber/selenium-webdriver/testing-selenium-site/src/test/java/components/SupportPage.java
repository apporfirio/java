package components;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

public class SupportPage extends MenuPage {

    private static final String BASE_URL = TestConfig.getSeleniumWebsiteURL();
    private static final String SELENIUM_OFFICIAL_USER_GROUP_URL = TestConfig.getSeleniumOfficialUserGroupURL();

    public SupportPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkOfficialUserGroupLink() {
        WebElement officialUserGroupLink = driver.findElement(By.xpath("//a[@href = '" + SELENIUM_OFFICIAL_USER_GROUP_URL + "']"));
        return officialUserGroupLink.isDisplayed();
    }

    @Override
    public String getMenuPath() {
        return "Support";
    }

    @Override
    public String getURL() {
        return BASE_URL + "/support/";
    }

    @Override
    public String getTitle() {
        return "Selenium Support";
    }
}

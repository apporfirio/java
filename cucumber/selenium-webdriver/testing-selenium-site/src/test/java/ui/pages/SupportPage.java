package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.URLChecker;

public class SupportPage extends Page {

    private static final String SELENIUM_OFFICIAL_USER_GROUP_URL = TestConfig.getSeleniumOfficialUserGroupURL();

    public SupportPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkOfficialUserGroupLinkIsDisplayed() {
        WebElement officialUserGroupLink = driver.findElement(By.xpath("//a[@href = '" + SELENIUM_OFFICIAL_USER_GROUP_URL + "']"));
        return officialUserGroupLink.isDisplayed();
    }

    public boolean checkOfficialUserGroupLinkIsWorking() {
        return URLChecker.checkStatusOk(SELENIUM_OFFICIAL_USER_GROUP_URL);
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/support/";
    }

    @Override
    public String getTitle() {
        return "Selenium Support";
    }
}

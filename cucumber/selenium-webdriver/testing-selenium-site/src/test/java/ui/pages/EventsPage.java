package ui.pages;

import config.TestConfig;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

public class EventsPage extends MenuPage {

    @FindBy(css = ".article.meetups")
    private WebElement meetupsArticle;

    private static final String SELENIUM_MEETUPS_URL = TestConfig.getSeleniumMeetupsURL();

    public EventsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkMeetupsLinkIsDisplayed() {
        WebElement meetupsLink = meetupsArticle.findElement(By.xpath(".//a[@href = '" + SELENIUM_MEETUPS_URL + "']"));
        return meetupsLink.isDisplayed();
    }

    public boolean checkMeetupsLinkIsWorking() {
        return URLChecker.checkStatusOk(SELENIUM_MEETUPS_URL);
    }

    @Override
    public String getMenuPath() {
        return "About/Events";
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/events/";
    }

    @Override
    public String getTitle() {
        return "Selenium Events";
    }
}

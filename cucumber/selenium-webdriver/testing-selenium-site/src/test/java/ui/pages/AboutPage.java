package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

public class AboutPage extends MenuPage {

    @FindBy(css = ".article.history")
    private WebElement historyArticle;

    private static final String FULL_HISTORY_URL_SUFFIX = "/history";
    private static final String BASE_URL = TestConfig.getSeleniumWebsiteURL();

    public AboutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkFullHistoryLinkIsDisplayed() {
        WebElement fulLHistoryLink = historyArticle.findElement(By.xpath(".//a[@href = '" + FULL_HISTORY_URL_SUFFIX + "']"));
        return fulLHistoryLink.isDisplayed();
    }

    public boolean checkFullHistoryLinkIsWorking() {
        return URLChecker.checkStatusOk(BASE_URL + FULL_HISTORY_URL_SUFFIX);
    }

    @Override
    public String getMenuPath() {
        return "About/About";
    }

    @Override
    public String getURL() {
        return BASE_URL + "/about/";
    }

    @Override
    public String getTitle() {
        return "About Selenium";
    }

}

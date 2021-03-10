package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

public class DownloadsPage extends MenuPage {

    @FindBy(xpath = "//*[@class = 'releases-button' and text() = 'Previous Releases']")
    private WebElement previousReleasesButton;

    @FindBy(id = "releasesContainer")
    private WebElement releasesContainer;

    private static final String SELENIUM_PREVIOUS_RELEASES_URL = TestConfig.getSeleniumPreviousReleasesURL();

    public DownloadsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkPreviousReleasesLinkIsDisplayed() {
        previousReleasesButton.click();

        if (releasesContainer.isDisplayed()) {
            WebElement previousReleasesLink = releasesContainer.findElement(By.xpath("//a[@href = '" + SELENIUM_PREVIOUS_RELEASES_URL + "']"));
            return previousReleasesLink.isDisplayed();
        }

        return false;
    }

    public boolean checkPreviousReleasesLinkIsWorking() {
        return URLChecker.checkStatusOk(SELENIUM_PREVIOUS_RELEASES_URL);
    }

    @Override
    public String getMenuPath() {
        return "Downloads";
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/downloads/";
    }

    @Override
    public String getTitle() {
        return "Downloads";
    }
}

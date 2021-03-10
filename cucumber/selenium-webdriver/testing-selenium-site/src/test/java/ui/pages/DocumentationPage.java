package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

public class DocumentationPage extends MenuPage {

    @FindBy(css = "#sidebar .topics")
    private WebElement topicsList;

    private static final String INSTALLATION_URL_SUFFIX = "/selenium_installation/";

    public DocumentationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkInstallationLinkIsDisplayed() {
        WebElement seleniumInstallationLink = getTopicLink("Selenium installation");

        if (seleniumInstallationLink.isDisplayed()) {
            String seleniumInstallationLinkHREF = seleniumInstallationLink.getAttribute("href");
            return seleniumInstallationLinkHREF.endsWith(INSTALLATION_URL_SUFFIX);
        }

        return false;
    }

    public boolean checkInstallationLinkIsWorking() {
        return URLChecker.checkStatusOk(getURL() + INSTALLATION_URL_SUFFIX);
    }

    private WebElement getTopicLink(String topicLinkText) {
        return topicsList.findElement(By.linkText(topicLinkText));
    }

    @Override
    public String getMenuPath() {
        return "Documentation";
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/documentation/en/";
    }

    @Override
    public String getTitle() {
        return "The Selenium Browser Automation Project :: Documentation for Selenium";
    }
}

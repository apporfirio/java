package components;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentationPage extends MenuPage {

    @FindBy(css = "#sidebar .topics")
    private WebElement topicsList;

    private static final String BASE_URL = TestConfig.getSeleniumWebsiteURL();

    public DocumentationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkSeleniumInstallationLink() {
        WebElement seleniumInstallationLink = getTopicLink("Selenium installation");

        if (seleniumInstallationLink.isDisplayed()) {
            String seleniumInstallationLinkHREF = seleniumInstallationLink.getAttribute("href");
            return seleniumInstallationLinkHREF.endsWith("/selenium_installation/");
        }

        return false;
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
        return BASE_URL + "/documentation/en/";
    }

    @Override
    public String getTitle() {
        return "The Selenium Browser Automation Project :: Documentation for Selenium";
    }
}

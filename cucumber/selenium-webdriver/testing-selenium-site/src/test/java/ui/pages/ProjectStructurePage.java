package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

public class ProjectStructurePage extends Page {

    @FindBy(css = ".article.governance")
    private WebElement governanceArticle;

    private static final String GOVERNANCE_URL_SUFFIX = "/governance";
    private static final String BASE_URL = TestConfig.getSeleniumWebsiteURL();

    public ProjectStructurePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkGovernanceLinkIsDisplayed() {
        WebElement governanceLink = governanceArticle.findElement(By.xpath(".//a[@href = '" + GOVERNANCE_URL_SUFFIX + "']"));
        return governanceLink.isDisplayed();
    }

    public boolean checkGovernanceLinkIsWorking() {
        return URLChecker.checkStatusOk(BASE_URL + GOVERNANCE_URL_SUFFIX);
    }

    @Override
    public String getURL() {
        return BASE_URL + "/project/";
    }

    @Override
    public String getTitle() {
        return "Selenium Project Structure & Governance";
    }

}

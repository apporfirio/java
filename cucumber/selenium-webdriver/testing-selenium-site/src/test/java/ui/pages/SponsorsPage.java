package ui.pages;

import config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SponsorsPage extends Page {

    @FindBy(css = ".backer-logo")
    List<WebElement> companiesLogos;

    public SponsorsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkCompaniesLogosArePresent() {
        return companiesLogos.size() > 0;
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/sponsors/";
    }

    @Override
    public String getTitle() {
        return "Sponsor";
    }

}

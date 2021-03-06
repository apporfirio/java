package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage extends Page {

    @FindBy(css = ".hero .header-description")
    private WebElement descriptionContainer;

    public AboutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkDescriptionIsDisplayed() {
        String descriptionPiece = "Selenium is a suite of tools for automating web browsers";
        WebElement description = descriptionContainer.findElement(By.xpath(".//*[contains(text(), '" + descriptionPiece + "')]"));
        return description.isDisplayed();
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/about/";
    }

    @Override
    public String getTitle() {
        return "About Selenium";
    }

}

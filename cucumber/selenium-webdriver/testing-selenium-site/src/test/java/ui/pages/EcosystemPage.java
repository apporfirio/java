package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EcosystemPage extends Page {

    @FindBy(css = ".hero .header-description")
    private WebElement descriptionContainer;

    public EcosystemPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkDescriptionIsDisplayed() {
        String descriptionPiece = "a large ecosystem of Open Source projects have sprouted up";
        WebElement description = descriptionContainer.findElement(By.xpath(".//*[contains(text(), '" + descriptionPiece + "')]"));
        return description.isDisplayed();
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/ecosystem/";
    }

    @Override
    public String getTitle() {
        return "Ecosystem";
    }
}

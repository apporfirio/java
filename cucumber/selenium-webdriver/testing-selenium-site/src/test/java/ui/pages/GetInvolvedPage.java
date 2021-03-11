package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GetInvolvedPage extends Page {

    @FindBy(css = ".hero .header-description")
    private WebElement descriptionContainer;

    public GetInvolvedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkDescriptionIsDisplayed() {
        String descriptionPiece = "Selenium is certainly a team effort! There are several ways you can help out";
        WebElement description = descriptionContainer.findElement(By.xpath(".//*[contains(text(), '" + descriptionPiece + "')]"));
        return description.isDisplayed();
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/getinvolved/";
    }

    @Override
    public String getTitle() {
        return "Get Involved";
    }

}

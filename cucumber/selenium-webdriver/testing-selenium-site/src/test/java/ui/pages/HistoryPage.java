package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HistoryPage extends Page {

    public HistoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkHistoryBeginningIsDisplayed() {
        String historyBeginningTitle = "The story starts in 2004";
        WebElement historyBeginningTitleElement = driver.findElement(By.xpath("//*[text() = '" + historyBeginningTitle + "']"));
        return historyBeginningTitleElement.isDisplayed();
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/history/";
    }

    @Override
    public String getTitle() {
        return "Selenium History";
    }

}

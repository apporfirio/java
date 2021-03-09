package components;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected Page(WebDriver driver) {
        this(driver, TestConfig.getWebDriverTimeout());
    }

    protected Page(WebDriver driver, int waitTimeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, waitTimeout);
    }

    public String getURL() {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public void open() {
        driver.get(getURL());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

}

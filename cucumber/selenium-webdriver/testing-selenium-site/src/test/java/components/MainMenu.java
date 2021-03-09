package components;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainMenu {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "navbar")
    private WebElement menu;

    public MainMenu(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TestConfig.getWebDriverTimeout());
        PageFactory.initElements(driver, this);
    }

    public void selectOption(String menuOptionText) {
        getMenuOption(menuOptionText).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    private WebElement getMenuOption(String menuOptionText) {
        return menu.findElement(By.linkText(menuOptionText));
    }

}

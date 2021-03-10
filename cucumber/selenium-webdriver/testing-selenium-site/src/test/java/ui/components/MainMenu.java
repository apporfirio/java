package ui.components;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;

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

    public void toggleMenuGroup(String menuGroupName) {
        WebElement menuGroup = menu.findElement(By.xpath("//*[@onclick='toggle" + menuGroupName + "Nav()']"));
        menuGroup.click();
    }

    public void selectOption(String menuOptionText) {
        WebElement option = menu.findElement(By.linkText(menuOptionText));
        openMenuOption(option);
    }

    public void selectOption(String menuOptionText, String menuGroupName) {
        WebElement menuGroupNav = menu.findElement(By.id(menuGroupName.toLowerCase() + "Subnav"));
        WebElement option = menuGroupNav.findElement(By.linkText(menuOptionText));
        openMenuOption(option);
    }

    private void openMenuOption(WebElement option) {
        option.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }
}

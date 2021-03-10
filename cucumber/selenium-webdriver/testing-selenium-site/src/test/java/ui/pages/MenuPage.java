package ui.pages;

import org.openqa.selenium.WebDriver;

public abstract class MenuPage extends Page {

    protected MenuPage(WebDriver driver) {
        super(driver);
    }

    public String getMenuPath() {
        return null;
    }

}

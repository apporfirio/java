package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.URLChecker;

import java.util.List;

public class BlogPage extends MenuPage {

    @FindBy(css = ".filter-container .blog-filter")
    private WebElement blogFilter;

    public BlogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean checkCategoriesLinksAreDisplayed(List<String> categories) {
        WebElement categoriesLinksContainer = getCategoriesLinksContainer();

        for (String category : categories) {
            WebElement categoryLink = categoriesLinksContainer.findElement(By.linkText(category));
            if (!categoryLink.isDisplayed()) return false;
        }

        return true;
    }

    public boolean checkCategoriesLinksAreWorking(List<String> categories) {
        WebElement categoriesLinksContainer = getCategoriesLinksContainer();

        for (String category : categories) {
            WebElement categoryLink = categoriesLinksContainer.findElement(By.linkText(category));
            if (!URLChecker.checkStatusOk(categoryLink.getAttribute("href"))) return false;
        }

        return true;
    }

    private WebElement getCategoriesLinksContainer() {
        WebElement sectionTitle = blogFilter.findElement(By.xpath(".//*[text() = 'Categories']"));
        return sectionTitle.findElement(By.xpath(".//following-sibling::*[@class = 'links-container']"));
    }

    @Override
    public String getMenuPath() {
        return "Blog";
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/blog/";
    }

    @Override
    public String getTitle() {
        return "Selenium Blog";
    }

}

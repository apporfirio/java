package components;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProjectsPage extends MenuPage {

    private static final String BASE_URL = TestConfig.getSeleniumWebsiteURL();

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkProjects(List<String> expectedProjects) {
        for (String expectedProject : expectedProjects) {
            WebElement projectTitle = driver.findElement(By.xpath("//h2[text() = '" + expectedProject + "']"));

            if (!projectTitle.isDisplayed()) return false;
        }

        return true;
    }

    @Override
    public String getMenuPath() {
        return "Projects";
    }

    @Override
    public String getURL() {
        return BASE_URL + "/projects/";
    }

    @Override
    public String getTitle() {
        return "Selenium Projects";
    }
}

package ui.pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProjectsPage extends MenuPage {

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkProjects(List<String> expectedProjects) {
        for (String expectedProject : expectedProjects) {
            WebElement projectTitle = driver.findElement(By.id(convertProjectNameToID(expectedProject)));

            if (!projectTitle.isDisplayed()) return false;
        }

        return true;
    }

    private String convertProjectNameToID(String projectName) {
        String[] nameParts = projectName.split(" ");
        String projectID = "";

        for (String namePart : nameParts) {
            if (projectID.isEmpty())
                projectID += namePart.toLowerCase();
            else
                projectID += "-" + namePart.toLowerCase();
        }

        return projectID;
    }

    @Override
    public String getMenuPath() {
        return "Projects";
    }

    @Override
    public String getURL() {
        return TestConfig.getSeleniumWebsiteURL() + "/projects/";
    }

    @Override
    public String getTitle() {
        return "Selenium Projects";
    }
}

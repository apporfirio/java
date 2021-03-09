package steps;

import components.*;
import config.TestConfig;
import config.WebDriverConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenuSteps {

    private WebDriver driver;

    @Before
    public void setUpScenario() {
        driver = WebDriverConfig.getWebDriver();
    }

    @After
    public void tearDownScenario() {
        driver.quit();
    }

    @Given("I open Selenium's website")
    public void openWebSite() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
    }


    @When("I select {string} on main menu")
    public void selectMenuOption(String menuOptionText) {
        MainMenu mainMenu = new MainMenu(driver);
        mainMenu.selectOption(menuOptionText);
    }

    @Then("I have access to all previous releases of Selenium")
    public void checkAccessToDownloadsPage() {
        DownloadsPage downloadsPage = new DownloadsPage(driver);
        assertEquals(downloadsPage.getTitle(), driver.getTitle());
        assertTrue(downloadsPage.checkPreviousReleasesLinkIsDisplayed());
        assertTrue(downloadsPage.checkPreviousReleasesLinkIsWorking());
    }

    @Then("I can read about the following projects:")
    public void checkAccessToProjectsPage(List<String> expectedProjects) {
//        ProjectsPage projectsPage = new ProjectsPage(driver);
//        assertEquals(projectsPage.getTitle(), driver.getTitle());
//        assertTrue(projectsPage.checkProjects(expectedProjects));
    }

    @Then("I can read how to install Selenium")
    public void checkAccessToDocumentationPage() {
//        DocumentationPage documentationPage = new DocumentationPage(driver);
//        assertEquals(documentationPage.getTitle(), driver.getTitle());
//        assertTrue(documentationPage.checkSeleniumInstallationLink());
    }

    @Then("I have access to the official user group")
    public void checkAccessToSupportPage() {
//        SupportPage supportPage = new SupportPage(driver);
//        assertEquals(supportPage.getTitle(), driver.getTitle());
//        assertTrue(supportPage.checkOfficialUserGroupLink());
    }

}

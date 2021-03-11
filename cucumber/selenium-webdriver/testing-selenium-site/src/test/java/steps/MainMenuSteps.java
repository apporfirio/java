package steps;

import ui.components.*;
import config.WebDriverConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.pages.*;

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

    @Given("I select {string} option under {string}")
    public void selectSubmenuOption(String menuOptionText, String menuGroupName) {
        MainMenu mainMenu = new MainMenu(driver);
        mainMenu.toggleMenuGroup(menuGroupName);
        mainMenu.selectOption(menuOptionText, menuGroupName);
    }

    @When("I select {string} option")
    public void selectMenuOption(String menuOptionText) {
        MainMenu mainMenu = new MainMenu(driver);
        mainMenu.selectOption(menuOptionText);
    }

    @Then("I can know what Selenium is")
    public void checkAccessToAboutPage() {
        AboutPage aboutPage = new AboutPage(driver);
        assertEquals(aboutPage.getTitle(), driver.getTitle());
        assertTrue(aboutPage.checkDescriptionIsDisplayed());
    }

    @Then("I can know Selenium's governance modal and philosophy")
    public void checkAccessToProjectPage() {
        ProjectStructurePage projectStructurePage = new ProjectStructurePage(driver);
        assertEquals(projectStructurePage.getTitle(), driver.getTitle());
        assertTrue(projectStructurePage.checkGovernanceLinkIsDisplayed());
        assertTrue(projectStructurePage.checkGovernanceLinkIsWorking());
    }

    @Then("I can find about Selenium's meetups around the world")
    public void checkAccessToEventsPage() {
        EventsPage eventsPage = new EventsPage(driver);
        assertEquals(eventsPage.getTitle(), driver.getTitle());
        assertTrue(eventsPage.checkMeetupsLinkIsDisplayed());
        assertTrue(eventsPage.checkMeetupsLinkIsWorking());
    }

    @Then("I can get information on Open Source projects built around Selenium")
    public void checkAccessToEcosystemPage() {
        EcosystemPage ecosystemPage = new EcosystemPage(driver);
        assertEquals(ecosystemPage.getTitle(), driver.getTitle());
        assertTrue(ecosystemPage.checkDescriptionIsDisplayed());
    }

    @Then("I can read Selenium's history")
    public void checkAccessToHistoryPage() {
        HistoryPage historyPage = new HistoryPage(driver);
        assertEquals(historyPage.getTitle(), driver.getTitle());
        assertTrue(historyPage.checkHistoryBeginningIsDisplayed());
    }

    @Then("I can find out how to help Selenium")
    public void checkAccessToGetInvolvedPage() {
        GetInvolvedPage getInvolvedPage = new GetInvolvedPage(driver);
        assertEquals(getInvolvedPage.getTitle(), driver.getTitle());
        assertTrue(getInvolvedPage.checkDescriptionIsDisplayed());
    }

    @Then("I can know which companies sponsors Selenium")
    public void checkAccessToSponsorsPage() {
        SponsorsPage sponsorsPage = new SponsorsPage(driver);
        assertEquals(sponsorsPage.getTitle(), driver.getTitle());
        assertTrue(sponsorsPage.checkCompaniesLogosArePresent());
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
        ProjectsPage projectsPage = new ProjectsPage(driver);
        assertEquals(projectsPage.getTitle(), driver.getTitle());
        assertTrue(projectsPage.checkProjects(expectedProjects));
    }

    @Then("I can read how to install Selenium")
    public void checkAccessToDocumentationPage() {
        DocumentationPage documentationPage = new DocumentationPage(driver);
        assertEquals(documentationPage.getTitle(), driver.getTitle());
        assertTrue(documentationPage.checkInstallationLinkIsDisplayed());
        assertTrue(documentationPage.checkInstallationLinkIsWorking());
    }

    @Then("I have access to the official user group")
    public void checkAccessToSupportPage() {
        SupportPage supportPage = new SupportPage(driver);
        assertEquals(supportPage.getTitle(), driver.getTitle());
        assertTrue(supportPage.checkOfficialUserGroupLinkIsDisplayed());
        assertTrue(supportPage.checkOfficialUserGroupLinkIsWorking());
    }

    @Then("I can find posts related to the following categories:")
    public void checkAccessToBlogPage(List<String> expectedCategories) {
        BlogPage blogPage = new BlogPage(driver);
        assertEquals(blogPage.getTitle(), driver.getTitle());
        assertTrue(blogPage.checkCategoriesLinksAreDisplayed(expectedCategories));
        assertTrue(blogPage.checkCategoriesLinksAreWorking(expectedCategories));
    }

}

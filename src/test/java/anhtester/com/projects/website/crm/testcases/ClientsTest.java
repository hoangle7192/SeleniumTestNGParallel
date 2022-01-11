package anhtester.com.projects.website.crm.testcases;

import anhtester.com.common.BaseTest;
import anhtester.com.common.BaseWeb;
import anhtester.com.driver.DriverManager;
import anhtester.com.helpers.Helpers;
import anhtester.com.utils.WebUI;
import anhtester.com.helpers.Props;
import anhtester.com.projects.website.crm.pages.Clients.ClientPage;
import anhtester.com.projects.website.crm.pages.Dashboard.DashboardPage;
import anhtester.com.projects.website.crm.pages.SignIn.SignInPage;
import anhtester.com.listeners.TestListener;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Epic("Regression Test CRM")
@Feature("Clients Test")
public class ClientsTest extends BaseWeb {

    SignInPage signInPage;
    DashboardPage dashboardPage;
    ClientPage clientPage;

    @Step("Login to CRM system")
    public void SignIn() {
        signInPage = new SignInPage();
        dashboardPage = signInPage.signIn(Props.getValue("emailAdmin"), Helpers.decrypt(Props.getValue("password")));
    }
    
    @BeforeMethod
    public void Setup() {
        SignIn();
    }

    @Test(priority = 1, description = "Add Client")
    @Step("Add Client")
    public void AddClient() {
        webUI.waitForPageLoaded();
        clientPage = dashboardPage.openClientPage();
        webUI.waitForPageLoaded();
        clientPage.openClientTabPage();
        clientPage.addClient();
    }

    @Test(priority = 2, description = "Search Client")
    @Step("Search Client")
    public void SearchClient() {
        webUI.waitForPageLoaded();
        clientPage = dashboardPage.openClientPage();
        webUI.waitForPageLoaded();
        clientPage.openClientTabPage();
        // Search lần 1
        clientPage.enterDataSearchClient("Anh Tester Com 04");
        webUI.checkContainsSearchTableByColumn(2, "Anh Tester Com 04");
        // Search lần 2
        clientPage.enterDataSearchClient("Phamiliar Tech");
        webUI.checkContainsSearchTableByColumn(2, "Phamiliar Tech");
    }

}

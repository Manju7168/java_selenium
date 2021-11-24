package tests.updateaccount;

import common.CommonPage;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.ManageAccountPage;
import utils.TestManager;

public class CreateandUpdateAccoutTest extends TestManager {

    @Test(groups = {"createandupdate"})
    public void createAccountUpdateDetails() {
        HomePage homePage = new HomePage();
        CommonPage commonPage = new CommonPage();
        CreateAccountPage createAccountPage = new CreateAccountPage();
        ManageAccountPage manageAccountPage = new ManageAccountPage();

        setScenario("TC111111_Create Account, update details and verify");

        setSubScenario("Create Account");
        commonPage.loginApplication(getTestData("app_url"));
        homePage.clickRegisterButton();
        createAccountPage.enterEmail(getTestData("user_email"));
        createAccountPage.clickContinueWithEmailButton();
        createAccountPage.enterPwd(getTestData("user_pwd"));
        createAccountPage.clickCreateAccount();
        homePage.verifyAccountCreation();

        setSubScenario("Update Account Details");

        homePage.clickContinue();
        homePage.clickManageAccount();
        manageAccountPage.clickPersonalDetailsLink();
        manageAccountPage.clickEditFields("Title");
        manageAccountPage.selectTitle(getTestData("title"));
        manageAccountPage.clickSaveButtonforFields("Title");

        manageAccountPage.clickEditFields("Name");
        manageAccountPage.enterFirstandLastName(getTestData("firstName"),getTestData("lastName"));
        manageAccountPage.clickSaveButtonforFields("Name");
        homePage.verifyAccountName(getTestData("firstName")+" "+getTestData("lastName"));
    }
}

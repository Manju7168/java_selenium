package tests.login;

import common.CommonPage;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.TestManager;

public class LoginTest extends TestManager {

    @Test(groups = {"logintest"})
    public void loginTest() {
        CommonPage commonPage = new CommonPage();
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        setScenario("TC222222_Login Verification ");

        setSubScenario("Login test with valid credentials");

        commonPage.loginApplication(getTestData("app_url"));
        homePage.clickSigninButton();
        loginPage.enterEmailId(getTestData("user_email"));
        loginPage.clickContinuewithEmail();
        loginPage.enterPwd(getTestData("user_pwd"));
        loginPage.clickSignIn();
        homePage.verifyLoginSuccessful();
        homePage.clickSignoutButton();

        setSubScenario("Login test with invalid credentials");

        commonPage.loginApplication(getTestData("app_url"));
        homePage.clickSigninButton();
        loginPage.enterEmailId(getTestData("user_email"));
        loginPage.clickContinuewithEmail();
        loginPage.enterPwd(getTestData("user_invalid_pwd"));
        loginPage.clickSignIn();
        loginPage.verifyLoginFailed(getTestData("error_msg"));
    }
}

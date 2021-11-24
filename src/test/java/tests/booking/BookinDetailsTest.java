package tests.booking;

import common.CommonPage;
import org.testng.annotations.Test;
import pages.BookingDetailsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestManager;

public class BookinDetailsTest extends TestManager {

    @Test(groups = "bookingtest")
    public void bookingDetailsTest() {
        CommonPage commonPage = new CommonPage();
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
        BookingDetailsPage bookingDetailsPage=new BookingDetailsPage();

        setScenario("TC333333_Booking Details Verification ");

        setSubScenario("Login with valid credentials");

        commonPage.loginApplication(getTestData("app_url"));
        homePage.clickSigninButton();
        loginPage.enterEmailId(getTestData("user_email"));
        loginPage.clickContinuewithEmail();
        loginPage.enterPwd(getTestData("user_pwd"));
        homePage.clickSigninButton();

        setSubScenario("Book airport Taxi");

        homePage.clickAirportTaxiTab();
        homePage.selectOneway();
        homePage.enterPickupLoc(getTestData("pickup"));
        homePage.enterDropLoc(getTestData("drop"));
        homePage.selectPickupDt(getTestData("date"));
        homePage.selectPickupTm(getTestData("time"));
        homePage.selectPassangers(getTestData("passangers"));
        homePage.clickSearch();
        bookingDetailsPage.verifyDropLocation(getTestData("pickup"));
        bookingDetailsPage.verifyPickupLocation(getTestData("drop"));
        bookingDetailsPage.verifyPassangers(getTestData("passangers"));

    }
}

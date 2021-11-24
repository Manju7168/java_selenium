package pages;

import common.BasePage;
import common.CommonConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private final By btnRegister = By.xpath("//span[contains(.,'Register')]");
    private final By btnSignIn = By.xpath("//span[contains(.,'Sign in')]");
    private final By tabAirportTaxi = By.xpath("//span[contains(.,'Airport taxis')]");
    private final By btnSignOut = By.xpath("//span[contains(.,'Sign out')]");
    private final By txtSucessMessage = By.id("genius-onboarding-title");
    private final By btnStartSearching = By.xpath("//span[contains(.,'Start searching')]");
    private final By btnAccount = By.id("profile-menu-trigger--title");
    private final By btnManageAccount = By.xpath("//span[contains(.,'Manage account')]");
    private final By txtName = By.className("bui-avatar-block__title");
    private final By txtTime = By.xpath("//button[@aria-label='pickup time input field']");
    private final By txtDate = By.xpath("//button[@aria-label='pickup date input field']");
    private final By drpPickupHrs = By.id("pickupHour");
    private final By drpPickupMins = By.id("pickupMinute");
    private final By txtDropLoc = By.id("dropoffLocation");
    private final By txtPickupLoc = By.id("pickupLocation");
    private final By drpPassangers = By.id("passengers");
    private final By rdBtnOneway = By.id("returnJourneyNegative");
    private final By btnSearch = By.xpath("//span[contains(.,'Search')]");
    private final By btnConfirm = By.xpath("//span[contains(.,'Confirm')]");

    Logger logger = LoggerFactory.getLogger(HomePage.class);

    public void clickRegisterButton() {
        buttonClick(btnRegister);
        report(CommonConstants.INFO, "Register button clicked");
        waitForPageLoaded();
    }

    public void clickAirportTaxiTab() {
        radiobuttonClick(tabAirportTaxi);
        report(CommonConstants.INFO, "Airport taxi tab clicked");
        waitForPageLoaded();
    }

    public void clickSigninButton() {
        buttonClick(btnSignIn);
        report(CommonConstants.INFO, "Signin button clicked");
        waitForPageLoaded();
    }

    public void clickSignoutButton() {
        buttonClick(btnSignOut);
        report(CommonConstants.INFO, "Signout button clicked");
        waitForPageLoaded();
    }

    public void verifyAccountCreation() {
        String txt = getElementText(txtSucessMessage);
        if (txt.contains("Welcome to Booking.com!")) {
            report(CommonConstants.PASS, "Account Successfully created !");
        } else {
            report(CommonConstants.FAIL, "Account creation failed!");
        }
    }

    public void clickContinue() {
        buttonClick(btnStartSearching);
        waitForPageLoaded();
    }

    public void clickManageAccount() {
        buttonClick(btnAccount);
        buttonClick(btnManageAccount);
        report(CommonConstants.INFO, "Manage Account opened");
    }

    public void verifyAccountName(String name) {
        moveToElement(txtName);
        String txt = getElementText(txtName);
        logger.info("Expected :- " + name + " Actual :- " + txt);
        if (txt.equals(name))
            report(CommonConstants.PASS, "Account Name matched");
        else
            report(CommonConstants.FAIL, "Account Name mismatched ");
    }

    public void verifyLoginSuccessful() {
        buttonClick(btnAccount);
        if (isElementPresent(btnSignOut)) {
            report(CommonConstants.PASS, "Login Successful");
        } else {
            report(CommonConstants.FAIL, "Login Failed");
        }
    }

    public void selectDate(String date) {
        buttonClick(By.xpath("//a[@aria-label='" + date + "']"));
    }

    public void selectOneway() {
        radiobuttonClick(rdBtnOneway);
        report(CommonConstants.INFO, "One way selected");
    }

    public void enterPickupLoc(String loc) {
        enterText(txtPickupLoc, loc);
        waitFor(10);
        sendKeyboardKey(txtPickupLoc, Keys.DOWN);
        sendKeyboardKey(txtPickupLoc, Keys.ENTER);
        report(CommonConstants.INFO, "Pickup location entered");
    }

    public void enterDropLoc(String loc) {
        enterText(txtDropLoc, loc);
        waitFor(10);
        sendKeyboardKey(txtDropLoc, Keys.DOWN);
        sendKeyboardKey(txtDropLoc, Keys.ENTER);
        report(CommonConstants.INFO, "Drop location entered");
    }

    public void selectPickupDt(String dt) {
        buttonClick(txtDate);
        selectDate(dt);
        report(CommonConstants.INFO, "Date selected");
    }

    public void selectPickupTm(String tim) {
        buttonClick(txtTime);
        selectByVisibleText(drpPickupHrs, tim.split(":")[0]);
        selectByVisibleText(drpPickupMins, tim.split(":")[1]);
        buttonClick(btnConfirm);
        report(CommonConstants.INFO, "Time selected");
    }

    public void selectPassangers(String passangers) {
        selectByVisibleText(drpPassangers, passangers);
        report(CommonConstants.INFO, "Passangers selected");
    }

    public void clickSearch() {
        buttonClick(btnSearch);
        report(CommonConstants.INFO, "Search button clicked");
        waitForPageLoaded();
    }

}


package pages;

import common.BasePage;
import common.CommonConstants;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    private final By btnContinuewithEmail = By.xpath("//span[contains(.,'Continue with email')]");
    private final By txtEmail = By.id("username");
    private final By txtPwd = By.id("password");
    private final By btnSignIn = By.xpath("//span[contains(.,'Sign in')]");
    private final By txtError = By.id("password-description");

    Logger logger = LoggerFactory.getLogger(LoginPage.class);


    public void clickSignIn() {
        buttonClick(btnSignIn);
        reportWithoutScreenshot("info", "Signin Button clicked");
        waitForPageLoaded();
    }

    public void clickContinuewithEmail() {
        buttonClick(btnContinuewithEmail);
        report("info", "Email Entered");
        waitForPageLoaded();
    }

    public void enterEmailId(String email) {
        enterText(txtEmail, email);
        report("info", "Email Entered");
    }

    public void enterPwd(String pwd) {
        enterText(txtPwd, pwd);
        report("info", "Password Entered");
    }

    public void verifyLoginFailed(String errorMsg) {
        String txt = getElementText(txtError);
        logger.info("Expected :- " + errorMsg + " Actual:- " + txt);
        if (txt.equals(errorMsg)) {
            report(CommonConstants.PASS, "Login Failed with invalid Credentials");
        } else {
            report(CommonConstants.FAIL, "Login Successful with invalid Credentials");
        }
    }

}

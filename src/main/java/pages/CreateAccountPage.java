package pages;

import common.BasePage;
import common.CommonConstants;
import org.openqa.selenium.By;

public class CreateAccountPage extends BasePage {
    private final By txtEmail = By.id("username");
    private final By btnCntinuewithEmail = By.xpath("//span[contains(.,'Continue with email')]");
    private final By txtpwd = By.id("new_password");
    private final By txtCnfirmedPwd = By.id("confirmed_password");
    private final By btnCreateAccount = By.xpath("//span[contains(.,'Create account')]");

    public void enterEmail(String email) {
        enterText(txtEmail, email);
        report(CommonConstants.INFO, email + " Email Entered");
    }

    public void clickContinueWithEmailButton() {
        buttonClick(btnCntinuewithEmail);
        reportWithoutScreenshot(CommonConstants.INFO, "Continue with email button clicked");
    }

    public void enterPwd(String pwd) {
        enterText(txtpwd, pwd);
        enterText(txtCnfirmedPwd, pwd);
        report(CommonConstants.INFO, " Password Entered");
    }

    public void clickCreateAccount() {
        buttonClick(btnCreateAccount);
        reportWithoutScreenshot(CommonConstants.INFO, "Create Account button clicked");
    }

}



package pages;

import common.BasePage;
import org.openqa.selenium.By;

import static common.CommonConstants.INFO;

public class ManageAccountPage extends BasePage {

    private final By linkPersonalDetails = By.xpath("//h2[contains(.,'Personal details')]");
    private final By drpTitle = By.name("title");
    private final By txtFirstName = By.name("first");
    private final By txtLastName = By.name("last");


    public void clickPersonalDetailsLink() {
        buttonClick(linkPersonalDetails);
        reportWithoutScreenshot(INFO, "Personal details link clicked");
    }

    public void clickEditFields(String field) {
        buttonClick(By.xpath("//h2[contains(.,'" + field + "')]/../following-sibling::div[1]//span[contains(.,'Edit')]"));
        report(INFO, "Edit Button for " + field + " clicked");
    }

    public void clickSaveButtonforFields(String field) {
        buttonClick(By.xpath("//h2[contains(.,'" + field + "')]/../..//div//span[contains(.,'Save')]"));
        report(INFO, "Details of " + field + " saved successfully");
        waitForPageLoaded();
        waitFor(30);
    }

    public void selectTitle(String title) {
        selectByVisibleText(drpTitle, title);
    }

    public void enterFirstandLastName(String firstName, String lastName) {
        enterText(txtFirstName, firstName);
        enterText(txtLastName, lastName);
    }

}

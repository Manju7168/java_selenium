package common;

public class CommonPage extends BasePage{

    public void loginApplication(String url) {
        launchApplication(url);
        waitForPageLoaded();
    }
}

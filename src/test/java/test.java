import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class test extends WebSuiteConfigSetup {

    @Test
    public void firstTest() throws MalformedURLException {
        WebDriver driver = getWebDriver();
        launchSite("web", driver);
    }
}

import global.utilities.DriverManager;
import global.utilities.Libraries;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class WebSuiteConfigSetup extends Libraries {

    public WebDriverWait wait;

    @BeforeSuite(alwaysRun = true)
    public void loadResources() {
        try {
            initializeSuite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void initTest() {

    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {

    }

    public void setBrowserProperties(WebDriver driver) {
//        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(240, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
    }

    public WebDriver getWebDriver() throws MalformedURLException {
        WebDriver driver;
        try {
            driver = DriverManager.getInstance().getDriver();
        } catch (SessionNotCreatedException e) {
            driver = DriverManager.getInstance().getDriver();
        }
        setBrowserProperties(driver);
        return driver;
    }
}

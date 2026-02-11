package global.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class Libraries extends Base {

    public static void initializeSuite() {
        if (!isBeforeSuiteExecuted) {
            isBeforeSuiteExecuted = true;
        }
    }

    public void launchSite(String mode, WebDriver driver) {
        if (!buildNumber.equals("null")) {
            int length = 1920;
            int breath = 1080;
            Dimension dim = new Dimension(length, breath);
            driver.manage().window().setSize(dim);
        } else {
            driver.manage().window().maximize();
        }
        if (mode.equalsIgnoreCase("web")) {
            gotoWebHome(driver);
        } else if (mode.equalsIgnoreCase("admin")) {
            gotoAdminHome(driver);
        }
    }

    public static void gotoWebHome(WebDriver driver) {
        driver.get("https://practicesoftwaretesting.com/");
    }

    public static void gotoAdminHome(WebDriver driver) {
        driver.get("https://practicesoftwaretesting.com/Admin");
    }
}

package global.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverManager extends Base {
    private static DriverManager driverManager = new DriverManager();

    public static DriverManager getInstance() {
        return driverManager;
    }

    public WebDriver getDriver() throws MalformedURLException {
        if (gridMode) {
            return new RemoteWebDriver(new URL(""), getBrowserCapabilities(browser));
        } else {
            switch (browser.toLowerCase()) {
                case "firefox":
                    startGeckoDriver();
                    FirefoxOptions options = new FirefoxOptions();
                    options.setProfile(fireFoxProfile());
                    return new FirefoxDriver(options);
                case "chrome":
                    startChromeDriver();
                    return new ChromeDriver(chromeOptions());
                default:
                    startGeckoDriver();
                    options = new FirefoxOptions();
                    options.setProfile(fireFoxProfile());
                    return new FirefoxDriver(options);
            }
        }

    }

    public WebDriver getDriver(DesiredCapabilities capability) throws MalformedURLException {
        if (gridMode) {
            return new RemoteWebDriver(new URL(""), getBrowserCapabilities(browser, capability));
        } else {
            switch (browser.toLowerCase()) {
                case "firefox":
                    startGeckoDriver();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.merge(capability);
                    return new FirefoxDriver(firefoxOptions);

                case "chrome":
                    startChromeDriver();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.merge(capability);
                    return new ChromeDriver(chromeOptions);

                default:
                    System.out.println("Selecting firefox as default browser.");
                    startGeckoDriver();
                    FirefoxOptions defaultOptions = new FirefoxOptions();
                    defaultOptions.merge(capability);
                    return new FirefoxDriver(defaultOptions);
            }
        }
    }

    public static void removeDriver(WebDriver driver) {
        driver.quit();
    }

    public static void startGeckoDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    public static void startChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public static FirefoxProfile fireFoxProfile() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.dir", testAdminReportPath);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv;application/vnd.ms-excel");
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("geo.enabled", false);
        return firefoxProfile;
    }

    public static ChromeOptions chromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", testAdminReportPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
//        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);
//        options.setCapability("locationContextEnabled", false);
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("disable-infobars");
        return options;
    }

    private static DesiredCapabilities getBrowserCapabilities(String browserType) {
        DesiredCapabilities cap = new DesiredCapabilities();
        switch (browserType) {
            case "firefox":
                System.out.println("Opening firefox driver");
//                cap = DesiredCapabilities.firefox();
                break;
            case "chrome":
                System.out.println("Opening chrome driver");
//                cap = DesiredCapabilities.chrome();
                break;
            default:
                System.out.println("browser : \" + browserType + \" is invalid, Launching Firefox as browser of choice..");
//                cap = DesiredCapabilities.firefox();
                break;
        }
        return cap;
    }

    private static DesiredCapabilities getBrowserCapabilities(String browserType, DesiredCapabilities capability) {
        DesiredCapabilities cap = new DesiredCapabilities();
        switch (browserType) {
            case "firefox":
                System.out.println("Opening firefox driver");
//                cap = DesiredCapabilities.firefox();
                cap.merge(capability);
                break;
            case "chrome":
                System.out.println("Opening chrome driver");
//                cap = DesiredCapabilities.chrome();
                cap.merge(capability);
                break;
            default:
                System.out.println("browser : \" + browserType + \" is invalid, Launching Firefox as browser of choice..");
//                cap = DesiredCapabilities.firefox();
                cap.merge(capability);
                break;
        }
        return cap;
    }
}

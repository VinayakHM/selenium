package global.utilities;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public abstract class Base {
    public static final int retryCount = 3;
    public static WebDriverWait webDriverWait;
    public static Actions actions;
    public static final int explicitWaitTime = 60;
    public static final String operatingSystem = System.getProperty("os.name");
    public static String testAdminReportPath = "null";
    public static boolean isBeforeSuiteExecuted = false;

    /**
     * List of User Parameters
     */
    public static final String groupToExecute;
    public static String browser;
//    public static String serverName;
    public static final boolean isScreenshotRequired;
    public static final String buildNumber;
    public static boolean sendEmail;
    public static final String[] emailTo;
    public static boolean gridMode;
    public static String adminId;
    public static String adminPswd;
    public static HashMap<String, List<String>> wordFreqMap;

    public static String jobName;
//    public static final String trimmedServerName;

    static {
        groupToExecute = System.getProperty("suiteName");
        browser = System.getProperty("browser");
//        serverName = System.getProperty("serverName");
        isScreenshotRequired = Boolean.parseBoolean(System
                .getProperty("isScreenshotRequired"));
        buildNumber = System.getProperty("buildNumber");
        sendEmail = Boolean.parseBoolean(System.getProperty("sendEmail", "false"));
        emailTo = System.getProperty("emailTo", "").split(",");
        gridMode = Boolean.parseBoolean(System.getProperty("gridMode"));
        jobName = System.getProperty("jobName");
//        trimmedServerName = serverName.replace("", "");
        adminId = System.getProperty("adminId");
        adminPswd = System.getProperty("adminPswd");
    }
}

package libraries;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class BassClass {
    final static Logger logger = Logger.getLogger(BassClass.class);

    public static WebDriver driver;
    public static SeleniumLibrary selLibrary;
    private String browser;

    @BeforeClass
    public void beforeAllTests() {
        selLibrary = new SeleniumLibrary();
        // test suite start time
        String tempTestStartTime = selLibrary.getCurrentTime();
        PropertiesManager sessionTimeProp = new PropertiesManager(
                "src/test/resources/sessionConfig.properties");
        sessionTimeProp.setProperty("sessionTime", tempTestStartTime);

        PropertiesManager myProperty = new PropertiesManager("src/test/resources/config.properties");

        String demoModePropValue = myProperty.readProperty("demoMode");
        if (demoModePropValue.contains("On")) {
            selLibrary.setDemoMode(true);
        }
        browser = myProperty.readProperty("browser");
        String headless = myProperty.readProperty("isHeadless");
        if (headless.toLowerCase().contains("on")) {
            selLibrary.setChromeHeadless(true);
        }
    }

    @BeforeMethod
    public void setup() {
        logger.info("Test started...");

        if (!browser.toLowerCase().contains("chrome")) {
            logger.info("Starting default browser as [Chrome].");
        }
        driver = selLibrary.startLocalBrowser(SeleniumLibrary.Browser.CHROME);
    }

    @AfterMethod
    public void close(ITestResult result) {
        try {
            Thread.sleep(5 * 1000);

            if (ITestResult.FAILURE == result.getStatus()) {
                selLibrary.captureScreenshot(result.getName(), "");
            }
            selLibrary.closeBrowsers();
            logger.info("Test is ended...");
        } catch (Exception e) {
            logger.error("Error: ", e);
            selLibrary.closeBrowsers();
        }
    }

}



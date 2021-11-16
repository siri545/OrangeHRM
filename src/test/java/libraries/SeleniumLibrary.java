package libraries;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class SeleniumLibrary extends BassClass {

    private boolean isDemoMode = false;
    private boolean isChromeHeadless = false;
    private boolean isRemote = false;

    public boolean getChromeHeadless() {
        return isChromeHeadless;
    }

    public void setChromeHeadless(boolean _isChromeHeadless) {
        this.isChromeHeadless = _isChromeHeadless;
    }

    public void setDemoMode(boolean isDemoMode) {
        this.isDemoMode = isDemoMode;
    }

    enum Browser {
        CHROME
    }

    public WebDriver startLocalBrowser(Browser browser) {
        try {
            switch (browser) {
                case CHROME:
                    driver = startChromeBrowser();
                    break;
                default:
                    logger.error("Currently we are not supporting this browser type!");
                    logger.error("Default browser set to Chrome.");
                    driver = startChromeBrowser();
                    break;
            }
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return driver;
    }

    private WebDriver startChromeBrowser() {
        try {
            ChromeOptions chromeOps = new ChromeOptions();
            if (isChromeHeadless) {
                chromeOps.setHeadless(true);
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            logger.debug("Maximize the browser");
            driver.manage().window().maximize();
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return driver;
    }

    public WebElement waitForElementVisibility(By by) {
        WebElement elem = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            elem = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return elem;
    }

    public void enterTxt(By by, String inputTxt) {
        try {
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(inputTxt);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public void clickElement(By by) {
        try {
            WebElement elem = driver.findElement(by);
            elem.click();
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public void selectDropDown(By by, String optionValue) {
        try {
            WebElement elem = driver.findElement(by);
            Select dropdown = new Select(elem);
            dropdown.selectByVisibleText(optionValue);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public void getTxtWithoutVerifyPrint(By by, String expectedText) {
        try {
            String actualText = driver.findElement(by).getText();
            assertThat(actualText, equalToIgnoringWhiteSpace(expectedText));
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public void mouseHover(By by) {
        try {
            Actions actions = new Actions(driver);
            WebElement elem = driver.findElement(by);
            actions.moveToElement(elem).build().perform();
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public void enterTxtWithEnter(By by, String inputTxt) {
        try {
            Thread.sleep(500);
            WebElement element = driver.findElement(by);
            element.click();
            Thread.sleep(500);
            element.clear();
            Thread.sleep(500);
            element.sendKeys(inputTxt);
            Thread.sleep(500);
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public void customWait(double inSeconds) {
        try {
            Thread.sleep((long) (inSeconds * 1000));
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public String getCurrentTime() {
        String finalTime = null;
        try {
            Date date = new Date();
            String tempTime = new Timestamp(date.getTime()).toString();
            logger.debug("Time: " + tempTime);
            finalTime = tempTime.replace("-", "").replace(":", "").replace(" ", "").replace(".", "");
            logger.info("getCurrentTime() ---> " + finalTime);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return finalTime;
    }

    public void captureScreenshot(String screenshotFileName, String filePath) {
        String finalScreenshotPath = null;
        try {
            String timeStamp = getCurrentTime();
            if (filePath.isEmpty()) {
                checkDirectory("target/screenshots/");
                finalScreenshotPath = "target/screenshots/" + screenshotFileName + "_" + timeStamp + ".png";
            } else {
                checkDirectory(filePath);
                finalScreenshotPath = filePath + screenshotFileName + "_" + timeStamp + ".png";
            }
            if (isRemote) {
                driver = new Augmenter().augment(driver);
            }
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(scrFile, new File(finalScreenshotPath));
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        String fullPath = getAbsulutePath(finalScreenshotPath);
        logger.info("Screenshot location: " + fullPath);
    }

    private void checkDirectory(String inputPath) {
        File file = new File(inputPath);
        String abPath = file.getAbsolutePath();
        File file2 = new File(abPath);
        try {
            if (!file2.exists()) {
                if (file2.mkdirs()) {
                    logger.info("Directories are created....");
                } else {
                    logger.info("Directories are NOT created...");
                }
            }
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    private String getAbsulutePath(String inputPath) {
        String abPath = null;
        try {
            File file = new File(inputPath);
            abPath = file.getAbsolutePath();
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return abPath;
    }

    public void closeBrowsers() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }



}
package page.performance.configure;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;

public class KPIPage extends BassClass {
    final static Logger logger = Logger.getLogger(KPIPage.class);
    public static final By editText = By.cssSelector("#defineKpi360_keyPerformanceIndicators");
    public static final By kpiElem = By.id("PerformanceHeading");
    public static final By miniRateElem = By.cssSelector("input[name='defineKpi360[minRating]']");
    public static final By maxRateElem = By.cssSelector("input[name='defineKpi360[maxRating]']");
    public static final By clickOnSave = By.className("addbutton");

    public KPIPage userLandOnKPIsPage() {
        try {
            WebElement KPIsPageElem = selLibrary.waitForElementVisibility(kpiElem);
            assertNotNull(KPIsPageElem);
            assertEquals(KPIsPageElem.getText(), "Key Performance Indicator");
            logger.info("KPIs page is display");
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void editTextIndicatorBox(String text) {
        selLibrary.customWait(3);
        selLibrary.enterTxt(editText, text);
    }

    public void clickOnSave() {
        selLibrary.clickElement(clickOnSave);
    }

    public void enterMinimumRating(String number){
        selLibrary.customWait(1);
        selLibrary.enterTxt(miniRateElem, number);
    }
    public void enterMaximumRating(String number){
        selLibrary.customWait(1);
        selLibrary.enterTxt(maxRateElem,number);

    }



}

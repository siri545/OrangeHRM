package page.performance.configure;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;

public class SearchKPIsPage extends BassClass {
    final static Logger logger = Logger.getLogger(SearchKPIsPage.class);
    public static final By KIPsHead = By.cssSelector("#divFormContainer > div.head");
    public static final By authoredTestsElem = By.cssSelector("tr:nth-child(2) > td:nth-child(2) > a");
    public static final By verifyText = By.cssSelector("tr:nth-child(2) > td:nth-child(2) > a");
    public static final By minRateElem = By.cssSelector("tr:nth-child(2) > td:nth-child(4)");
    public static final By maxRateElem = By.cssSelector(" tr:nth-child(2) > td:nth-child(5)");


    public SearchKPIsPage userLandOnSearchKPIsPage(){
        try{
            WebElement searchKPIsPageElem = selLibrary.waitForElementVisibility(KIPsHead);
            assertNotNull(searchKPIsPageElem);
            assertEquals(searchKPIsPageElem.getText(),"Search Key Performance Indicators");
            logger.info("Search KPIs page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }


    public void clickOnAuthoredTests(){
        selLibrary.clickElement(authoredTestsElem);
    }

    //verify text result after added editTextIndicatorBox from KPI page
    public String verifyEditTextResult(){
            String Elem = null;
            WebElement textElem = selLibrary.waitForElementVisibility(verifyText);
            Elem = textElem.getText();
            return Elem;
        }

        //verify minimum rate result after added 50 from KPI page
    public String verifyMinimumRateResult(){
        String Elem = null;
        WebElement textElem = selLibrary.waitForElementVisibility(minRateElem);
        Elem = textElem.getText();
        return Elem;
    }

    public String verifyMaximumRateResult(){
        String Elem = null;
        WebElement textElem = selLibrary.waitForElementVisibility(maxRateElem);
        Elem = textElem.getText();
        return Elem;
    }

    }












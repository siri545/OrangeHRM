package page.leave.entitlements;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.testng.Assert.*;

public class LeaveEntitlementsPage extends BassClass {
    final static Logger logger = Logger.getLogger(LeaveEntitlementsPage.class);
    public static final By headerElem = By.cssSelector("#search_form > fieldset > ol > li:nth-child(1) > label");
    public static final By entitleElem = By.cssSelector(" tr > td.right > a");


    public LeaveEntitlementsPage userLandOnLeaveEntitlementsPage() {
        try {
            WebElement LEMPageElem = selLibrary.waitForElementVisibility(headerElem);
            assertNotNull(LEMPageElem);
            assertEquals(LEMPageElem.getText(), "Employee");
            logger.info("Leave Entitlement page is display");
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public String verifyAddedEntitleNumberResult(){
        String Elem = null;
        WebElement textElem = selLibrary.waitForElementVisibility(entitleElem);
        Elem = textElem.getText();
        return Elem;
    }










}

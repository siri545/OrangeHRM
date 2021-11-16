package page.leave.entitlements;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;


public class AddLeaveEntitlementPage extends BassClass {
    final static Logger logger = Logger.getLogger(AddLeaveEntitlementPage.class);
    public static final By entitlementElem = By.cssSelector(" div.head > h1");
    public static final By nameBoxElem = By.name("entitlements[employee][empName]");
    public static final By numberElem = By.name("entitlements[entitlement]");
    public static final By saveButton = By.id("btnSave");


    public AddLeaveEntitlementPage userLandOnAddEntitlementPage() {
        try {
            WebElement addEntitlementPageElem = selLibrary.waitForElementVisibility(entitlementElem);
            assertNotNull(addEntitlementPageElem);
            assertEquals(addEntitlementPageElem.getText(), "Add Leave Entitlement");
            logger.info("AddEntitlement page is display");
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void enterNameOnEmployeeBox(String name){
        selLibrary.enterTxtWithEnter(nameBoxElem, name);
    }

    public void enterEntitlementNumber(String number){
        selLibrary.enterTxt(numberElem, number);
    }

    public void clickOnSaveButton(){
        selLibrary.clickElement(saveButton);
    }



}

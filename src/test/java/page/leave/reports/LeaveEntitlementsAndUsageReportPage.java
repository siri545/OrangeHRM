package page.leave.reports;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;

public class LeaveEntitlementsAndUsageReportPage extends BassClass {
    final static Logger logger = Logger.getLogger(LeaveEntitlementsAndUsageReportPage.class);
    private static final By leaveEntitlement = By.cssSelector("#leave-balance-report > div.head > h1");
    public static final By selectLeaveType = By.name("leave_balance[report_type]");
    public static final By clickOnView = By.cssSelector("input#viewBtn");
    public static final By resultElem = By.cssSelector("#report-results > div > table");
    public static final By employeeResult = By.cssSelector("tbody > tr > td:nth-child(3)");
    public static final By chooseLocation = By.name("leave_balance[location]");
    public static final By chooseSubUnit = By.name("leave_balance[sub_unit]");
    public static final By leaveTypeElem = By.xpath("//*[@id=\"leave_balance_leave_type\"]");
    public static final By employeeNameResult = By.cssSelector("tbody > tr > td:nth-child(3)");
    public static final By errorMsgElem = By.cssSelector("li:nth-child(2) > span");
    public static final By employeeName = By.className("ac_input");


    public LeaveEntitlementsAndUsageReportPage userLandOnLeaveEntitlementsPage(){
        try{
            WebElement leaveEntitlementsPageElem = selLibrary.waitForElementVisibility(leaveEntitlement);
            assertNotNull(leaveEntitlementsPageElem);
            assertEquals(leaveEntitlementsPageElem.getText(),"Leave Entitlements and Usage Report");
            logger.info("LeaveEntitlements page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void selectLeaveTypeOnGenerateForBox(String text){
        selLibrary.selectDropDown(selectLeaveType,text);
    }

    public void clickOnViewButton(){
        selLibrary.customWait(1);
        selLibrary.clickElement(clickOnView);
    }

    public boolean verifyLeaveTypeResult(){
        selLibrary.waitForElementVisibility(resultElem);
        return true;
    }

    public void selectLocation(String location){
        selLibrary.selectDropDown(chooseLocation,location);
    }

    public void selectSubUnit(String unit){
        selLibrary.selectDropDown(chooseSubUnit, unit);
    }

    public boolean verifyEmployeeResult(){
        selLibrary.getTxtWithoutVerifyPrint(employeeResult,"Linda Anderson");
        return true;
    }

    public void selectLeaveType(String leaveType){
        selLibrary.selectDropDown(leaveTypeElem,leaveType);
    }

    public boolean verifyEmployeeNameResult(){
        selLibrary.getTxtWithoutVerifyPrint(employeeNameResult,"Melan Peiris");
        return true;
    }

    public String verifyErrorMsg(){
        String errorTxt = null;
        WebElement txtElem = selLibrary.waitForElementVisibility(errorMsgElem);
        errorTxt = txtElem.getText();
        return errorTxt;
    }

    public void enterEmployeeName(String name){
        selLibrary.enterTxtWithEnter(employeeName, name);
    }








}

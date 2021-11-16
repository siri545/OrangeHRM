package page.admid.userManagement;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;


public class SystemUserPage extends BassClass {
    final static Logger logger = Logger.getLogger(SystemUserPage.class);
    public static final By adminPage = By.cssSelector("div.head > h1");
    public static final By verifyEmployeeTxt = By.cssSelector("tr > td:nth-child(4)");
    public static final By searchButton = By.className("searchbutton");
    public static final By selectStatus = By.name("searchSystemUser[status]");
    public static final By employeeName = By.id("searchSystemUser_employeeName_empName");
    public static final By selectUserRole = By.name("searchSystemUser[userType]");
    public static final By enterUserName = By.name("searchSystemUser[userName]");
    public static final By hoverJobTitle = By.cssSelector("#menu_admin_Job");
    public static final By clickElem = By.cssSelector("#menu_admin_viewJobTitleList");
    public static final By clickOnJobTitle = clickElem;


    public SystemUserPage userLandOnAdminPage(){
        try{
            WebElement adminPageElem = selLibrary.waitForElementVisibility(adminPage);
            assertNotNull(adminPageElem);
            assertEquals(adminPageElem.getText(),"System Users");
            logger.info("Admin page is display");
         }catch (Exception e) {
        logger.error("Error: ", e);
        assertTrue(false);
    }
        return this;
    }

    public void enterUserName(String userName){
        selLibrary.enterTxt(enterUserName, userName);
    }

    public void selectUserRole(String userRole){
        selLibrary.selectDropDown(selectUserRole, userRole);
    }

    public void enterEmployeeName(String name){
        selLibrary.enterTxt(employeeName, name);
    }

    public void selectStatus(String status){
        selLibrary.selectDropDown(selectStatus,status);
    }

    public void clickOnSearchButton(){
        selLibrary.clickElement(searchButton);
        selLibrary.customWait(2);
    }

    public String verifyEmployeeSearchResult(){
        String employeeElem = null;
        WebElement employeeName = selLibrary.waitForElementVisibility(verifyEmployeeTxt);
        employeeElem = employeeName.getText();
        return employeeElem;
    }

    public void navigateToJobTitlePageByMouseHover(){
        selLibrary.mouseHover(hoverJobTitle);
        selLibrary.clickElement(clickOnJobTitle);
    }









}

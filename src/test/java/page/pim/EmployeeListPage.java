package page.pim;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;


public class EmployeeListPage extends BassClass {
    final static Logger logger = Logger.getLogger(EmployeeListPage.class);
    private final By EmployeeList = By.id("menu_pim_viewEmployeeList");
    private final By EmployeeID = By.cssSelector("div#profile-pic>h1");
    private final By ErrorMsg = By.cssSelector("li:nth-child(3) > span");


    public EmployeeListPage verifyEmployeeListPageIsDisPlay() {
        try{
        WebElement employeeListElem = selLibrary.waitForElementVisibility(EmployeeList);
        assertNotNull(employeeListElem);
        assertEquals(employeeListElem.getText(), "Employee List");
        logger.info("Employee list page is displays");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public String verifyEmployeeName() {
        String employeeID = null;
        WebElement employeeElem = selLibrary.waitForElementVisibility(EmployeeID);
        employeeID = employeeElem.getText();
        return employeeID;
    }

    public String verifyErrorMsg() {
        String errorMsg = null;
        WebElement errorMsgElem = selLibrary.waitForElementVisibility(ErrorMsg);
        errorMsg = errorMsgElem.getText();
        logger.info("Please enter employee last name");
        return errorMsg;
    }

}

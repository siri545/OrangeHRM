package page.pim;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;


public class AddEmployeePage extends BassClass {

    final static Logger logger = Logger.getLogger(AddEmployeePage.class);
    private final By inputFirstName = By.cssSelector("input#firstName");
    private final By inputLastName = By.cssSelector("input#lastName");
    private final By clickOnSave = By.cssSelector("input#btnSave");
    private final By addEmployee = By.cssSelector("div.head>h1");


    public AddEmployeePage verifyAddEmployeePageIsDisPlay(){
        try{
        WebElement addEmployeeHeader = selLibrary.waitForElementVisibility(addEmployee);
        assertNotNull(addEmployeeHeader);
        assertEquals(addEmployeeHeader.getText(),"Add Employee");
        logger.info("AddEmployee page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void enterEmployeeFirstName(String firstName){
        selLibrary.enterTxt(inputFirstName, firstName);
    }

    public void enterEmployeeLastName(String lastName){
        selLibrary.enterTxt(inputLastName, lastName);
    }
    
    public void clickOnSaveButton(){
        selLibrary.clickElement(clickOnSave);
    }


}

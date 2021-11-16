package page;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.*;


public class DirectoryPage extends BassClass {
    final static Logger logger = Logger.getLogger(DirectoryPage.class);
    public static final By resultBox = By.id("resultBox");
    public static final By locatorElem = By.className("selectableGroupWidget");
    public static final By jobTitleElem = By.name("searchDirectory[job_title]");
    public static final By searchElem = By.id("searchBtn");
    public static final By employeeNameElem = By.name("searchDirectory[emp_name][empName]");
    public static final By directoryPageElem = By.cssSelector("div.head>h1");
    public static final By employeeLocator = By.cssSelector("ul > li:nth-child(1) > b");


    public DirectoryPage userLandOnDirectoryPage(){
        try{
        WebElement directoryElem = selLibrary.waitForElementVisibility(directoryPageElem);
        assertNotNull(directoryElem);
        assertEquals(directoryElem.getText(),"Search Directory");
        logger.info("Directory page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void enterEmployeeName(String employeeName){
        selLibrary.enterTxt(employeeNameElem,employeeName);
    }

    public void selectJobTitle(String title){
        selLibrary.selectDropDown(jobTitleElem, title);
    }

    public void selectLocation(String location){
        selLibrary.selectDropDown(locatorElem, location);
    }

    public void clickOnSearch(){
        selLibrary.clickElement(searchElem);
    }

    public String verifySearchResult(){
        String employeeElem = null;
        WebElement employeeName = selLibrary.waitForElementVisibility(employeeLocator);
        employeeElem = employeeName.getText();
        return employeeElem;
    }

    public boolean verifyResultBox(){
        selLibrary.waitForElementVisibility(resultBox);
        return true;
    }

}

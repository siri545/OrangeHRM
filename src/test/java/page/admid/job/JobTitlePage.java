package page.admid.job;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;

public class JobTitlePage extends BassClass {
    final static Logger logger = Logger.getLogger(JobTitlePage.class);
    public static final By jobTitle = By.cssSelector("div.head > h1");
    public static final By clickOnLink = By.cssSelector("tr:nth-child(1) > td:nth-child(2) > a");
    public static final By clickOnEditButton = By.cssSelector("#btnSave");
    public static final By enterTxtOnJobDescription = By.name("jobTitle[jobDescription]");
    public static final By clickOnSaveButton = By.name("btnSave");
    public static final By getTxtElem = By.cssSelector("tr:nth-child(1) > td:nth-child(3)");
    public static final By addNewJobTitle = By.cssSelector("tr:nth-child(2) > td:nth-child(2) > a");
    public static final By newJobTitle = By.cssSelector("#jobTitle_jobTitle");
    public static final By clickJobTitle = By.id("btnAdd");


    public JobTitlePage userLandOnJobTitlePage(){
        try{
            WebElement jobTitlePageElem = selLibrary.waitForElementVisibility(jobTitle);
            assertNotNull(jobTitlePageElem);
            assertEquals(jobTitlePageElem.getText(),"Job Titles");
            logger.info("Job title page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void clickOnChiefExecutiveOfficerLink(){
        selLibrary.clickElement(clickOnLink);
        selLibrary.clickElement(clickOnEditButton);
    }

    public void enterTxtInJobDescriptionButton(String text){
        selLibrary.enterTxt(enterTxtOnJobDescription, text);
        selLibrary.clickElement(clickOnSaveButton);
    }

    public String verifyJobDescriptionText(){
        String descriptionTxt = null;
        WebElement txtElem = selLibrary.waitForElementVisibility(getTxtElem);
        descriptionTxt = txtElem.getText();
        return descriptionTxt;
    }

    public void clickAddNewJobTitle(){
        selLibrary.clickElement(clickJobTitle);
    }

    public void enterNewJobTitle(String jobTitle){
        selLibrary.enterTxt(newJobTitle, jobTitle);
    }

    public String verifyNewJobTitleIsAdd(){
        String newJobTitleTxt = null;
        WebElement txtElem = selLibrary.waitForElementVisibility(addNewJobTitle);
        newJobTitleTxt = txtElem.getText();
        return newJobTitleTxt;
    }










}

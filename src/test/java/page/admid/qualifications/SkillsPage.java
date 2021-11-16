package page.admid.qualifications;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;


public class SkillsPage extends BassClass {
    final static Logger logger = Logger.getLogger(SkillsPage.class);
    public static final By skillsPage = By.cssSelector("#recordsListDiv > div.head > h1");
    public static final By verifySkillsText = By.cssSelector("tr:nth-child(5) > td:nth-child(3)");
    public static final By verifySkillElem = By.cssSelector("tr:nth-child(1) > td.tdName.tdValue > a");
    public static final By verifyRequiredTxt = By.cssSelector("li:nth-child(1) > span");
    public static final By saveButton = By.id("btnSave");
    public static final By descriptionElem = By.name("skill[description]");
    public static final By skillsName = By.cssSelector("#skill_name");
    public static final By addButtonElem = By.id("btnAdd");

    public SkillsPage userLandOnSkillsPage(){
        try{
            WebElement skillsPageElem = selLibrary.waitForElementVisibility(skillsPage);
            assertNotNull(skillsPageElem);
            assertEquals(skillsPageElem.getText(),"Skills");
            logger.info("Skills page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void clickOnAddButton(){
        selLibrary.clickElement(addButtonElem);
    }

    public void enterSkillsName(String name){
        selLibrary.enterTxt(skillsName, name);
    }

    public void enterDescription(String text){
        selLibrary.enterTxt(descriptionElem,text);
    }

    public void clickOnSaveButton(){
        selLibrary.clickElement(saveButton);
    }

    public boolean verifySkillsNameTxt(){
        selLibrary.getTxtWithoutVerifyPrint(verifySkillsText, "Willingness to learn");
        return true;
    }

    public String verifySkillsNameAfterAdd(){
        String textElem = null;
        WebElement skillsNameElem = selLibrary.waitForElementVisibility(verifySkillElem);
        textElem = skillsNameElem.getText();
        return textElem;
    }

    public String verifyRequiredText(){
        String textElem = null;
        WebElement requiredTextElem = selLibrary.waitForElementVisibility(verifyRequiredTxt);
        textElem = requiredTextElem.getText();
        return textElem;
    }



































}

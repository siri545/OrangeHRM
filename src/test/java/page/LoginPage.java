package page;


import libraries.BassClass;
import libraries.PropertiesManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import static org.testng.Assert.assertTrue;

public class LoginPage extends BassClass {

    final static Logger logger = Logger.getLogger(LoginPage.class);

    private final String Url = "https://opensource-demo.orangehrmlive.com/";
    private final By LoginButton = By.id("btnLogin");
    private final By UserNameTextBox = By.id("txtUsername");
    private final By PasswordTextBox = By.id("txtPassword");
    private final By WarningTextMessage = By.id("spanMessage");

    //this is the 1st page when we log in
    public LoginPage navigateToOrangeHRMLoginPage(){
        try{
            driver.get(Url);
            String actualTitle = driver.getTitle();
            assertThat(actualTitle, is("OrangeHRM"));
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return this;
    }

    public void enterUserName(String userName){
        selLibrary.enterTxt(UserNameTextBox, userName);
    }

    public void enterPassWord(String passWord){
        selLibrary.enterTxt(PasswordTextBox, passWord);
    }

    public void clickOnLoginButton(){
        selLibrary.clickElement(LoginButton);
    }

    public boolean verifyInvalidMsg(){
        selLibrary.getTxtWithoutVerifyPrint(WarningTextMessage,"Invalid credentials");
        return true;
    }



}

package libraries;

import page.LoginPage;

public class HelperMethods extends BassClass {
    LoginPage loginPage = new LoginPage();

    //This Helper Method class is only to reduce a multiple line of code in TestScripts
    public void navigateAndLogin(String UserName, String Password) {
        loginPage.navigateToOrangeHRMLoginPage();
        loginPage.enterUserName(UserName);
        loginPage.enterPassWord(Password);
        loginPage.clickOnLoginButton();
    }


}

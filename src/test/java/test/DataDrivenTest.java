package test;

import libraries.BassClass;
import libraries.ExcelManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.DirectoryPage;
import page.HomePage;
import page.LoginPage;

import static org.testng.Assert.assertEquals;


public class DataDrivenTest extends BassClass {

    private int testCounter = 0;
    private String excelFile = "src/test/resources/tesData/HRM2.xlsx";

    @DataProvider(name = "OrangeHRMTest")
    private Object[][] orangeTestData() {
        Object[][] data = null;
        ExcelManager excelUtil = new ExcelManager(excelFile, 0);
        data = excelUtil.getExcelAllData();
        return data;
    }

    //Test 3 data Rows
    @Test(dataProvider = "OrangeHRMTest")
    public void directoryTest(String username, String password, String name, String jobTitle,String results) {
        testCounter++;
        LoginPage loginPage = new LoginPage();
        HomePage homePage = new HomePage();
        DirectoryPage directPage = new DirectoryPage();
        loginPage.navigateToOrangeHRMLoginPage();
        loginPage.enterUserName(username);
        loginPage.enterPassWord(password);
        loginPage.clickOnLoginButton();
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnDirectory();
        directPage.userLandOnDirectoryPage();
        directPage.enterEmployeeName(name);
        directPage.selectJobTitle(jobTitle);
        directPage.clickOnSearch();
        assertEquals(directPage.verifySearchResult(), results);
    }


}

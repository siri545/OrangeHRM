package test;

import libraries.BassClass;
import libraries.HelperMethods;
import org.testng.annotations.Test;
import page.DirectoryPage;
import page.HomePage;
import page.LoginPage;
import page.admid.job.JobTitlePage;
import page.admid.qualifications.SkillsPage;
import page.admid.userManagement.SystemUserPage;
import page.leave.entitlements.AddLeaveEntitlementPage;
import page.leave.entitlements.LeaveEntitlementsPage;
import page.leave.reports.LeaveEntitlementsAndUsageReportPage;
import page.performance.configure.KPIPage;
import page.performance.configure.SearchKPIsPage;
import page.pim.AddEmployeePage;
import page.pim.EmployeeListPage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestScripts extends BassClass {
    LoginPage loginPage = new LoginPage();
    //public LoginPage loginPage;
    HomePage homePage = new HomePage();
    AddEmployeePage addEmployeePage = new AddEmployeePage();
    EmployeeListPage employeeListPage = new EmployeeListPage();
    DirectoryPage directoryPage = new DirectoryPage();
    HelperMethods helperMethods = new HelperMethods();
    SystemUserPage systemUserPage = new SystemUserPage();
    JobTitlePage jobTitlePage = new JobTitlePage();
    SearchKPIsPage searchKpIsPage = new SearchKPIsPage();
    KPIPage kpiPage = new KPIPage();
    AddLeaveEntitlementPage enTitlePage = new AddLeaveEntitlementPage();
    LeaveEntitlementsPage leaveEntitlementsPage = new LeaveEntitlementsPage();
    LeaveEntitlementsAndUsageReportPage usageReportPage = new LeaveEntitlementsAndUsageReportPage();
    SkillsPage skillsPage = new SkillsPage();

    /***
     *Scenario: Validate login functionality
     *Test case 1.01
     */
    @Test(enabled = true, priority = 1)
    public void userEnterCorrectUserNameAndPassWord(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage(); //verify user navigate to home page
    }

    /***
     *Scenario: Validate login functionality
     *Test case 1.02
     */
    @Test(enabled = true, priority = 2)
    public void userEnterWrongUserNameAndCorrectPassWord(){
        helperMethods.navigateAndLogin("ABCD","admin123");
        assertTrue(loginPage.verifyInvalidMsg());
    }

    /***
     *Scenario: Validate login functionality
     *Test case 1.03
     */
    @Test(enabled = true, priority = 3)
    public void userEnterCorrectUserNameAndWrongPassWord(){
        helperMethods.navigateAndLogin("Admin","1234");
        assertTrue(loginPage.verifyInvalidMsg());
    }

    /***
     *Scenario:  Validate add new employees and add job title on (PIM, Add employee, Employee List)
     *Test case 2.04
     */
    @Test(enabled = true,priority = 4)
    public void addEmployeeFirstNameAndLastName(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnPIM();
        homePage.clickAddEmployee();
        addEmployeePage.verifyAddEmployeePageIsDisPlay();
        addEmployeePage.enterEmployeeFirstName("Jenifer");
        addEmployeePage.enterEmployeeLastName("Lopez");
        addEmployeePage.clickOnSaveButton();
        employeeListPage.verifyEmployeeListPageIsDisPlay();
        assertEquals(employeeListPage.verifyEmployeeName(),"Jenifer Lopez");
    }

    /***
     *Scenario:  Validate add new employees and add job title on (PIM, Add employee, Employee List)
     *Test case 2.05
     */
    @Test(enabled = true, priority = 5)
    public void addEmployeeFirstNameWithOutLastName(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnPIM();
        homePage.clickAddEmployee();
        addEmployeePage.verifyAddEmployeePageIsDisPlay();
        addEmployeePage.enterEmployeeFirstName("Jena");
        addEmployeePage.clickOnSaveButton();
        employeeListPage.verifyEmployeeListPageIsDisPlay();
        assertEquals(employeeListPage.verifyErrorMsg(),"Required");
    }

    /***
     *Scenario: Validate Directory functionality
     *Test case 3.06
     */
    @Test(enabled = true,priority = 6)
    public void userSearchForEmployeeNameJobTitleAndLocation(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnDirectory();
        directoryPage.userLandOnDirectoryPage();
        directoryPage.enterEmployeeName("Peter Mac Anderson");
        directoryPage.selectJobTitle("Chief Financial Officer");
        directoryPage.selectLocation("  United States"); //take time to load
        directoryPage.clickOnSearch();
        assertEquals(directoryPage.verifySearchResult(), "Peter Mac Anderson");
    }

    /***
     *Scenario: Validate Directory functionality
     *Test case 3.07
     */
    @Test(enabled = true,priority = 7)
    public void userSearchOnlyEmployeeName(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnDirectory();
        directoryPage.userLandOnDirectoryPage();
        directoryPage.enterEmployeeName("Lisa Andrews");
        directoryPage.clickOnSearch();
        assertEquals(directoryPage.verifySearchResult(), "Lisa Andrews");
    }

    /***
     *Scenario: Validate Directory functionality
     *Test case 3.08
     */
    @Test(enabled = true,priority = 8)
    public void userSearchOnlyEmployeeJobTitle(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnDirectory();
        directoryPage.userLandOnDirectoryPage();
        directoryPage.selectJobTitle("IT Manager");
        directoryPage.clickOnSearch();
        assertEquals(directoryPage.verifySearchResult(), "Cassidy Hope");
    }

    /***
     *Scenario: Validate Directory functionality
     *Test case 3.09
     */
    @Test(enabled = true,priority = 9)
    public void userSearchOnlyEmployeeLocation(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnDirectory();
        directoryPage.userLandOnDirectoryPage();
        directoryPage.selectLocation("    Texas R&D"); //take time to load
        directoryPage.clickOnSearch();
        assertTrue(directoryPage.verifyResultBox());
    }

    /***
     *Scenario: Validate search for employees information on (Admin, User management, Job title)
     *Test case 4.10
     */
    @Test(enabled = true,priority = 10)
    public void enterEmployeeSystemUsersInformation(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        systemUserPage.userLandOnAdminPage();
        systemUserPage.enterUserName("Aaliyah.Haq");
        systemUserPage.selectUserRole("ESS");
        systemUserPage.enterEmployeeName("Aaliyah Haq");
        systemUserPage.selectStatus("Enabled");
        systemUserPage.clickOnSearchButton();
        assertEquals(systemUserPage.verifyEmployeeSearchResult(),"Aaliyah Haq");
    }

    /***
     *Scenario: Validate search for employees information on (Admin, User management, Job title)
     *Test case 4.11
     */
    @Test(enabled = true,priority = 11)
    public void enterOnlyUserNameOnSystemUser(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        systemUserPage.userLandOnAdminPage();
        systemUserPage.enterUserName("David.Morris");
        systemUserPage.clickOnSearchButton();
        assertEquals(systemUserPage.verifyEmployeeSearchResult(),"David Morris");
    }

    /***
     *Scenario: Validate search for employees information on (Admin, User management, Job title)
     *Test case 4.12
     */
    @Test(enabled = true,priority = 12)
    public void enterJobDescription(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        systemUserPage.navigateToJobTitlePageByMouseHover();
        jobTitlePage.userLandOnJobTitlePage();
        jobTitlePage.clickOnChiefExecutiveOfficerLink();
        jobTitlePage.enterTxtInJobDescriptionButton("This Job Is Close");
        assertEquals(jobTitlePage.verifyJobDescriptionText(),"This Job Is Close");
    }

    /***
     *Scenario: Validate search for employees information on (Admin, User management, Job title)
     *Test case 4.13
     */
    @Test(enabled = true,priority = 13)
    public void addNewJobTitle(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        systemUserPage.navigateToJobTitlePageByMouseHover();
        jobTitlePage.userLandOnJobTitlePage();
        jobTitlePage.clickAddNewJobTitle();
        jobTitlePage.enterNewJobTitle("Accountant");
        jobTitlePage.enterTxtInJobDescriptionButton("I Love This Job");
        assertEquals(jobTitlePage.verifyNewJobTitleIsAdd(),"Accountant");
    }

    /***
     *Scenario: Validate add key, min rate, max rate on (Performance, Configure, KPIs)
     * Test case 5.14
     */
    @Test(enabled = true,priority = 14)
    public void editTextKeyPerformanceIndicator(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverPerformanceTap();
        homePage.clickOnConfigure();
        homePage.clickOnKPIsButton();
        searchKpIsPage.userLandOnSearchKPIsPage();
        searchKpIsPage.clickOnAuthoredTests();
        kpiPage.userLandOnKPIsPage();
        kpiPage.editTextIndicatorBox("Automation Testing");
        kpiPage.clickOnSave();
        assertEquals(searchKpIsPage.verifyEditTextResult(),"Automation Testing");
    }

    /***
     *Scenario: Validate add key, min rate, max rate on (Performance, Configure, KPIs)
     * Test case 5.15
     */
    @Test(enabled = true,priority = 15)
    public void enterMinimumRateOnPerformanceIndicator(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverPerformanceTap();
        homePage.clickOnConfigure();
        homePage.clickOnKPIsButton();
        searchKpIsPage.userLandOnSearchKPIsPage();
        searchKpIsPage.clickOnAuthoredTests();
        kpiPage.userLandOnKPIsPage();
        kpiPage.enterMinimumRating("50");
        kpiPage.clickOnSave();
        assertEquals(searchKpIsPage.verifyMinimumRateResult(),"50");
    }

    /***
     *Scenario: Validate add key, min rate, max rate on (Performance, Configure, KPIs)
     * Test case 5.16
     */
    @Test(enabled = true,priority = 16)
    public void enterMaximumRateOnPerformanceIndicator(){
        helperMethods.navigateAndLogin("Admin","admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverPerformanceTap();
        homePage.clickOnConfigure();
        homePage.clickOnKPIsButton();
        searchKpIsPage.userLandOnSearchKPIsPage();
        searchKpIsPage.clickOnAuthoredTests();
        kpiPage.userLandOnKPIsPage();
        kpiPage.enterMaximumRating("80");
        kpiPage.clickOnSave();
        assertEquals(searchKpIsPage.verifyMaximumRateResult(),"80");
    }

    /***
     *Scenario: Validate add entitlement number on (Leave, Entitlements, Add entitlements)
     * Test case 6.17
     */
    @Test(enabled = true,priority = 17)
    public void enterEmployeeNameOnAddLeaveEntitlement() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnLeave();
        homePage.mouseHoverEntitlement();
        homePage.clickOnAddEntitlement();
        enTitlePage.userLandOnAddEntitlementPage();
        enTitlePage.enterNameOnEmployeeBox("Lisa Andrews");
        enTitlePage.enterEntitlementNumber("500");
        enTitlePage.clickOnSaveButton();
        leaveEntitlementsPage.userLandOnLeaveEntitlementsPage();
        assertEquals(leaveEntitlementsPage.verifyAddedEntitleNumberResult(), "500.00");

    }

    /***
     *Scenario: Validate add entitlement number on (Leave, Entitlements, Add entitlements)
     * Test case 6.18
     */
    @Test(enabled = true,priority = 18)
    public void selectLeaveTypeOnGenerateBox(){
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnLeave();
        homePage.mouseHoverReport();
        homePage.clickOnUsageReport();
        usageReportPage.userLandOnLeaveEntitlementsPage();
        usageReportPage.selectLeaveTypeOnGenerateForBox("Leave Type");
        usageReportPage.clickOnViewButton();
        assertTrue(usageReportPage.verifyLeaveTypeResult());
    }

    /***
     *Scenario: Validate add entitlement number on (Leave, Entitlements, Add entitlements)
     * Test case 6.19
     */
    @Test(enabled = true,priority = 19)
    public void selectLocationAndSubunit() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnLeave();
        homePage.mouseHoverReport();
        homePage.clickOnUsageReport();
        usageReportPage.userLandOnLeaveEntitlementsPage();
        usageReportPage.selectLeaveTypeOnGenerateForBox("Leave Type");
        usageReportPage.selectLocation("  Canada"); //take time to load
        usageReportPage.selectSubUnit("Administration");
        usageReportPage.clickOnViewButton();
        assertTrue(usageReportPage.verifyEmployeeResult());
    }

    /***
     *Scenario: Validate add entitlement number on (Leave, Entitlements, Add entitlements)
     * Test case 6.20
     */
    @Test(enabled = true,priority = 20)
    public void selectLeaveTypeLocationAndSubunit() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnLeave();
        homePage.mouseHoverReport();
        homePage.clickOnUsageReport();
        usageReportPage.userLandOnLeaveEntitlementsPage();
        usageReportPage.selectLeaveTypeOnGenerateForBox("Leave Type");
        usageReportPage.selectLeaveType("CAN - FMLA");
        usageReportPage.selectLocation("  United States"); //take time to load
        usageReportPage.selectSubUnit("  Marketing"); //take time to load
        usageReportPage.clickOnViewButton();
        assertTrue(usageReportPage.verifyEmployeeNameResult());
    }

    /***
     *Scenario: Validate add entitlement number on (Leave, Entitlements, Add entitlements)
     * Test case 6.21
     */
    @Test(enabled = true,priority = 21)
    public void selectEmployeeOnGenerateBox() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnLeave();
        homePage.mouseHoverReport();
        homePage.clickOnUsageReport();
        usageReportPage.userLandOnLeaveEntitlementsPage();
        usageReportPage.selectLeaveTypeOnGenerateForBox("Employee");
        usageReportPage.clickOnViewButton();
        assertEquals(usageReportPage.verifyErrorMsg(),"Required");
    }

    /***
     *Scenario: Validate add entitlement number on (Leave, Entitlements, Add entitlements)
     * Test case 6.22
     */
    @Test(enabled = true,priority = 22)
    public void enterEmployeeNameAfterSelectEmployeeType() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.mouseHoverOnLeave();
        homePage.mouseHoverReport();
        homePage.clickOnUsageReport();
        usageReportPage.userLandOnLeaveEntitlementsPage();
        usageReportPage.selectLeaveTypeOnGenerateForBox("Employee");
        usageReportPage.enterEmployeeName("Anthony Nolan");
        usageReportPage.clickOnViewButton();
        assertTrue(usageReportPage.verifyLeaveTypeResult());
    }

    /***
     *Scenario: Validate add skills and skills description on (Admin, Qualification, Skills)
     * Test case 7.23
     */
    @Test(enabled = true,priority = 23)
    public void addSkillAndDescription() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        homePage.mouseHoverQualifications();
        homePage.clickOnSkills();
        skillsPage.userLandOnSkillsPage();
        skillsPage.clickOnAddButton();
        skillsPage.enterSkillsName("Database management");
        skillsPage.enterDescription("Willingness to learn");
        skillsPage.clickOnSaveButton();
        assertTrue(skillsPage.verifySkillsNameTxt());
    }

    /***
     *Scenario: Validate add skills and skills description on (Admin, Qualification, Skills)
     * Test case 7.24
     */
    @Test(enabled = true,priority = 24)
    public void addOnlySkillName() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        homePage.mouseHoverQualifications();
        homePage.clickOnSkills();
        skillsPage.userLandOnSkillsPage();
        skillsPage.clickOnAddButton();
        skillsPage.enterSkillsName("Adobe Software Suite");
        skillsPage.clickOnSaveButton();
        assertEquals(skillsPage.verifySkillsNameAfterAdd(),"Adobe Software Suite");

    }

    /***
     *Scenario: Validate add skills and skills description on (Admin, Qualification, Skills)
     * Test case 7.25
     */
    @Test(enabled = true,priority = 25)
    public void addDescriptionWithoutSkills() {
        helperMethods.navigateAndLogin("Admin", "admin123");
        homePage.verifyUserLandOnHomePage();
        homePage.clickOnAdminTap();
        homePage.mouseHoverQualifications();
        homePage.clickOnSkills();
        skillsPage.userLandOnSkillsPage();
        skillsPage.clickOnAddButton();
        skillsPage.enterDescription("Effective communication");
        skillsPage.clickOnSaveButton();
        assertEquals(skillsPage.verifyRequiredText(),"Required");
    }
}





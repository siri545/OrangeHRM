package page;

import libraries.BassClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.*;

public class HomePage extends BassClass {
    final static Logger logger = Logger.getLogger(HomePage.class);

    public static final By performanceElem = By.id("menu__Performance");
    public static final By configureElem = By.id("menu_performance_Configure");
    public static final By clickKPIs = By.cssSelector("#menu_performance_searchKpi");
    public static final By hoverLeave = By.id("menu_leave_viewLeaveModule");
    public static final By hoverEntitlementElem = By.id("menu_leave_Entitlements");
    public static final By addEntitlementElem = By.id("menu_leave_addLeaveEntitlement");
    public static final By reportElem = By.id("menu_leave_Reports");
    public static final By clickUsegeReport = By.cssSelector("#menu_leave_viewLeaveBalanceReport");
    public static final By qualificationsElem = By.id("menu_admin_Qualifications");
    public static final By skillsElem = By.id("menu_admin_viewSkills");
    public static final By DashboardPageHeader = By.className("head");
    public static final By adminTap = By.className("main-menu-first-level-list-item");
    public static final By mouseHoverPIM = By.id("menu_pim_viewPimModule");
    public static final By clickAddEmployee = By.id("menu_pim_addEmployee");
    public static final By directoryElem = By.id("menu_directory_viewDirectory");
    public static final By Title = By.cssSelector("div.head>h1");

    //Called Home page instead of Dashboard page
    // this is the 2nd second page after the login page
    // Jen was using {HomePage verifyUserLandOnHomePage()} as a misunderstanding that it is the
    // constructor as the synchronization page but, it was not. it can be just VOID
    // it should be public Homepage() only
    //

    public void verifyUserLandOnHomePage(){
        try{
            WebElement homePageElem = selLibrary.waitForElementVisibility(DashboardPageHeader);
            assertNotNull(homePageElem);
            WebElement homePageTitle = selLibrary.waitForElementVisibility(Title);
            assertEquals(homePageTitle.getText(),"Dashboard");
            logger.info("Home page is display");
        }catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }

    }

    public void mouseHoverOnPIM(){
        selLibrary.mouseHover(mouseHoverPIM);

    }

    public void clickAddEmployee(){
        selLibrary.clickElement(clickAddEmployee);
    }

    public void clickOnDirectory(){
        selLibrary.clickElement(directoryElem);
    }

    public void clickOnAdminTap(){
        selLibrary.clickElement(adminTap);
    }

    public void mouseHoverPerformanceTap(){
        selLibrary.mouseHover(performanceElem);
    }

    public void clickOnConfigure(){
        selLibrary.clickElement(configureElem);
    }

    public void clickOnKPIsButton(){
        selLibrary.clickElement(clickKPIs);
    }

    public void mouseHoverOnLeave(){
        selLibrary.mouseHover(hoverLeave);
    }

    public void mouseHoverEntitlement(){
        selLibrary.mouseHover(hoverEntitlementElem);
    }

    public void clickOnAddEntitlement(){
        selLibrary.clickElement(addEntitlementElem);
    }

    public void mouseHoverReport(){
        selLibrary.mouseHover(reportElem);
    }

    public void clickOnUsageReport(){
        selLibrary.clickElement(clickUsegeReport);
    }

    public void mouseHoverQualifications(){
        selLibrary.mouseHover(qualificationsElem);
    }

    public void clickOnSkills(){
        selLibrary.clickElement(skillsElem);
    }








}

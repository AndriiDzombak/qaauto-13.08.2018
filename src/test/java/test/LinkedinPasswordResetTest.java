package test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;

import java.util.ArrayList;
import static java.lang.Thread.sleep;

public class LinkedinPasswordResetTest extends LinkedinBaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"mineqabox@gmail.com", "qualityassurance123", "qualityassurance1234" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void resetPasswordTest(String userEmail,String newUserPsw, String confirmUserPsw){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinPasswordResetPage = linkedinLoginPage.resetPassword();
        Assert.assertTrue(linkedinPasswordResetPage.isPageLoaded());



        linkedinPleaseCheckEmailPage =  linkedinPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(linkedinPleaseCheckEmailPage.isPageLoaded());
        ((JavascriptExecutor)driver).executeScript("window.open()");
        try {
            sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.get("");
        linkedinYourPasswordResetPage = new LinkedinYourPasswordResetPage(driver);
        Assert.assertTrue(linkedinYourPasswordResetPage.isPageLoaded());

        linkedinHomePage = linkedinYourPasswordResetPage.submitNewPassword(newUserPsw,confirmUserPsw);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),"Home page is not loaded");

    }
}

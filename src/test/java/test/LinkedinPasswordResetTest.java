package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LinkedinPasswordResetTest extends LinkedinBaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"mineqabox@gmail.com", "qualityassurance1", "qualityassurance1" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void resetPasswordTest(String userEmail,String newUserPsw, String confirmUserPsw){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinPasswordResetPage = linkedinLoginPage.resetPassword();
        Assert.assertTrue(linkedinPasswordResetPage.isPageLoaded());

        linkedinPleaseCheckEmailPage =  linkedinPasswordResetPage.findAccount(userEmail);
        Assert.assertTrue(linkedinPleaseCheckEmailPage.isPageLoaded(), "Please check email page is not loaded");

        linkedinYourPasswordResetPage = linkedinPleaseCheckEmailPage.navigateToPswReset();
        Assert.assertTrue(linkedinYourPasswordResetPage.isPageLoaded(),"Your Password reser page is not loaded");

        linkedinSuccessfulPswChangePage = linkedinYourPasswordResetPage.submitNewPassword(newUserPsw,confirmUserPsw);
        Assert.assertTrue(linkedinSuccessfulPswChangePage.isPageLoaded(),"Successful page is not loaded");

        linkedinHomePage = linkedinSuccessfulPswChangePage.goToHome();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded");

    }
}

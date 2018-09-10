package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinLoginTest extends LinkedinBaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"mineqabox@gmail.com", "qualityassurance123" },
                {"Mineqabox@gmail.com", "qualityassurance123" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail,String userPsw){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinHomePage = linkedinLoginPage.logIn(userEmail,userPsw);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),"Home page is not loaded");
    }

    @DataProvider
    public Object[][] negativeDataProvider() {
        return new Object[][]{
                {"a@b.c", "inva", "Please enter a valid email address.", "The password you provided must have at least 6 characters.", "email&password" },//invalid user email and user password
                {"a@b.com", "invalid", "Hmm, we don't recognize that email. Please try again.", "",  "email" },//invalid user email
                {"myqabox@gmail.com", "invalid", "", "Hmm, that's not the right password. Please try again or request a new one.", "password" },//invalid user password
                {"", "", "" ,"", ""}//emty user email and user password
        };
    }

    @Test(dataProvider = "negativeDataProvider")
    public void negativeLoginTest(String userEmail,String userPsw,String emailAlertMsg,String pswAlertMsg, String inputName){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        if(inputName.equals("email&password")) {
            linkedinSubmitPage = linkedinLoginPage.logIn(userEmail,userPsw);
            Assert.assertTrue(linkedinSubmitPage.isPageLoaded(), "Login-sumit page is not loaded");
            Assert.assertTrue(linkedinSubmitPage.checkCommonAlertMessage());
            Assert.assertEquals(linkedinSubmitPage.getAlertMessages(inputName),emailAlertMsg + " " + pswAlertMsg, "Email or Password alert message does not match");
        }
        else if(inputName.equals("email")){
            linkedinSubmitPage = linkedinLoginPage.logIn(userEmail,userPsw);
            Assert.assertTrue(linkedinSubmitPage.isPageLoaded(), "Login-sumit page is not loaded");
            Assert.assertTrue(linkedinSubmitPage.checkCommonAlertMessage());
            Assert.assertEquals(linkedinSubmitPage.getAlertMessages(inputName),emailAlertMsg, "Email alert message does not match");
        }
        else if(inputName.equals("password")){
            linkedinSubmitPage = linkedinLoginPage.logIn(userEmail,userPsw);
            Assert.assertTrue(linkedinSubmitPage.isPageLoaded(), "Login-sumit page is not loaded");
            Assert.assertTrue(linkedinSubmitPage.checkCommonAlertMessage());
            Assert.assertEquals(linkedinSubmitPage.getAlertMessages(inputName),pswAlertMsg, "Password alert message does not match");
        }
            else {
            linkedinLoginPage.logIn(userEmail,userPsw);
            Assert.assertTrue(!linkedinLoginPage.getSignInBtn().isEnabled(),"Sign in button is still enabled");
        }

    }

}

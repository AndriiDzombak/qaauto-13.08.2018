import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinLoginTest {

    WebDriver driver;
    ChromeOptions chromeOptions;
    LinkedinLoginPage linkedinLoginPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSubmitPage linkedinSubmitPage;

    @BeforeMethod
    public void setUp(){
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension",false);
//      chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void aftermethod(){
      driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"myqabox@gmail.com", "qualityassurance123" },
                {"Myqabox@gmail.com", "qualityassurance123" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail,String userPsw){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinHomePage = linkedinLoginPage.logIn(userEmail,userPsw);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),"Home page is not loaded");
    }

    @Test
    public void negativeLoginTest(){

        String emailAlertMsg = "Please enter a valid email address.";
        String pswAlertMsg = "The password you provided must have at least 6 characters.";

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinSubmitPage = linkedinLoginPage.logIn("a@b.c","inva");
        Assert.assertTrue(linkedinSubmitPage.isPageLoaded(),"Login-sumit page is not loaded");
        Assert.assertTrue(linkedinSubmitPage.checkAlertMessages(emailAlertMsg,pswAlertMsg),"Alert message does not match");

    }

    @Test
    public void negativeEmailLoginTest(){

        String emailAlertMsg = "Hmm, we don't recognize that email. Please try again.";

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinSubmitPage = linkedinLoginPage.logIn("a@b.com","invalid");
        Assert.assertTrue(linkedinSubmitPage.isPageLoaded(),"Login-sumit page is not loaded");
        Assert.assertTrue(linkedinSubmitPage.checkAlertMessage(emailAlertMsg,"email"));

    }

    @Test
    public void negativePswLoginTest(){

        String pswAlertMsg = "Hmm, that's not the right password. Please try again or request a new one.";

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinLoginPage.logIn("myqabox@gmail.com","invalid");
        LinkedinSubmitPage linkedinSubmitPage = new LinkedinSubmitPage(driver);
        Assert.assertTrue(linkedinSubmitPage.isPageLoaded(),"Login-sumit page is not loaded");
        Assert.assertTrue(linkedinSubmitPage.checkAlertMessage(pswAlertMsg,"password"));

    }

    @Test
    public void negativeEmptyLoginTest(){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinLoginPage = linkedinLoginPage.logIn("","");
        Assert.assertFalse(linkedinLoginPage.getSignInBtn().isEnabled(),"Sign in button is still enabled");
    }
}

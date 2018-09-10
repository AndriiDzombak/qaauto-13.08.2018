import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class LinkedinPasswordResetTest {
    WebDriver driver;
    ChromeOptions chromeOptions;
    LinkedinLoginPage linkedinLoginPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSubmitPage linkedinSubmitPage;
    LinkedinPasswordResetPage linkedinPasswordResetPage;
    LinkedinPleaseCheckEmailPage linkedinPleaseCheckEmailPage;
    LinkedinYourPasswordResetPage linkedinYourPasswordResetPage;

    @BeforeMethod
    public void setUp(){
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension",false);
//        chromeOptions.addArguments("start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void aftermethod(){
        //driver.quit();
    }

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

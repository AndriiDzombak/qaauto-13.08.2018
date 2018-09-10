package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.*;

public class LinkedinBaseTest {

    WebDriver driver;
    ChromeOptions chromeOptions;
    LinkedinLoginPage linkedinLoginPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSubmitPage linkedinSubmitPage;
    LinkedinSearchPage linkedinSearchPage;
    LinkedinPasswordResetPage linkedinPasswordResetPage;
    LinkedinPleaseCheckEmailPage linkedinPleaseCheckEmailPage;
    LinkedinYourPasswordResetPage linkedinYourPasswordResetPage;

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
}

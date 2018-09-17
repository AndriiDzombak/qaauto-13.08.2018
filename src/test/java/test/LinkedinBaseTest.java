package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    LinkedinSuccessfulPswChangePage linkedinSuccessfulPswChangePage;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.firefoxdriver().setup();
       // chromeOptions = new ChromeOptions();
       // chromeOptions.setExperimentalOption("useAutomationExtension",false);
//      chromeOptions.addArguments("start-maximized");
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void aftermethod(){
       driver.quit();
    }

}

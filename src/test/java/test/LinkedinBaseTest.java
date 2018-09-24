package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.*;

public class LinkedinBaseTest {

    WebDriver driver;
    ChromeOptions chromeOptions;
    InternetExplorerOptions internetExplorerOptions;
    LinkedinLoginPage linkedinLoginPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSubmitPage linkedinSubmitPage;
    LinkedinSearchPage linkedinSearchPage;
    LinkedinPasswordResetPage linkedinPasswordResetPage;
    LinkedinPleaseCheckEmailPage linkedinPleaseCheckEmailPage;
    LinkedinYourPasswordResetPage linkedinYourPasswordResetPage;
    LinkedinSuccessfulPswChangePage linkedinSuccessfulPswChangePage;

    @BeforeMethod
    @Parameters({"browser", "environmentUrl"})
    public void setUp(@Optional("chrome") String browser,@Optional("https://www.linkedin.com/") String environmentUrl) throws Exception {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
//              chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                internetExplorerOptions = new InternetExplorerOptions();
                internetExplorerOptions.ignoreZoomSettings();
                driver = new InternetExplorerDriver(internetExplorerOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "opera":
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                break;

            default:
                throw new Exception("Browser " + browser + " is not supported");
        }

        driver.get(environmentUrl);
        linkedinLoginPage = new LinkedinLoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }

}

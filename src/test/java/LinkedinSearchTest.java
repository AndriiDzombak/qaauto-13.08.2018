import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LinkedinSearchTest {

    WebDriver driver;
    ChromeOptions chromeOptions;
    LinkedinLoginPage linkedinLoginPage;
    LinkedinHomePage linkedinHomePage;
    LinkedinSearchPage linkedinSearchPage;

    @BeforeMethod
    public void setUp(){
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension",false);
        chromeOptions.addArguments("start-maximized");
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
                {"myqabox@gmail.com", "qualityassurance123", 10, "hr" }
        };
    }

    @Test(dataProvider="validDataProvider")
    public void successfulSearchTest(String userEmail, String userPsw, int resultsAmount, String searchStatement){

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        linkedinHomePage = linkedinLoginPage.logIn(userEmail,userPsw);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),"Home page is not loaded");

        linkedinSearchPage = linkedinHomePage.searchStatement(searchStatement);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(),"Search page doesn't exist");
        Assert.assertEquals(linkedinSearchPage.getSearchResultsAmount(),resultsAmount, "Wrong total amount of results");

        List<String> resultsTextList = linkedinSearchPage.getResultsTextList();
        for(String resultsText: resultsTextList){
            try {
                Assert.assertTrue(resultsText.toLowerCase().contains(searchStatement),"Search statement:" + " " + searchStatement + " is not found: \n" + resultsText);
            }catch (AssertionError e){
                e.printStackTrace();
            }

        }

    }
}

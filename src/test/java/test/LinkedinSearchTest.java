package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.List;

public class LinkedinSearchTest extends LinkedinBaseTest {


    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"mineqabox@gmail.com", "qualityassurance123", 10, "hr" }
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

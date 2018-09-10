package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchPage extends LinkedinBasePage {

    @FindBy(xpath = "//li[contains(@class,'search-result__occluded-item')]")
    private List<WebElement> foundResults;

    @FindBy(xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement searchResultsTotal;

    public LinkedinSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded(){
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getCurrentUrl().contains("/search/results/")
                && getCurrentTitle().contains("| Search | LinkedIn")
                && searchResultsTotal.isDisplayed();
    }


    public List<String> getResultsTextList(){
        List<String > resultsTextList = new ArrayList<String>();
        for(WebElement foundResult: foundResults){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",foundResult);
            resultsTextList.add(foundResult.getText());
        }
        return resultsTextList;
    }

    public int getSearchResultsAmount() {
        return foundResults.size();
    }
}

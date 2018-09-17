package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;


/**
 * LinkedinBase Page object class.
 */
public class LinkedinBasePage {

    protected WebDriver driver;
    protected GMailService gMailService;


    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getCurrentTitle() {
        return driver.getTitle();
    }

    /**
     * Explicit wait till WebElement visibility becomes true
     *
     * @param element - WebElement expected on the Page
     * @param timeOutInSec - int time for waiting in seconds
     * @return WebElement
     */
    protected WebElement waitUntilElementVisable(WebElement element, int timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected boolean isUrlContains(String partialUrl, int timeOutSec){
        WebDriverWait wait = new WebDriverWait(driver, timeOutSec);

       try{
           return wait.until(ExpectedConditions.urlContains(partialUrl));
       }catch (TimeoutException e){
          return false;
       }
    }

    protected void assertElementIsVisable(WebElement webElement, int timeOutInSec, String message){
        try {
            waitUntilElementVisable(webElement, timeOutInSec);
        }catch (TimeoutException e){
            throw new AssertionError(message);
        }
    }
}

package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;


public class LinkedinBasePage {

    protected WebDriver driver;
    protected GMailService gMailService;


    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getCurrentTitle() {
        return driver.getTitle();
    }

}

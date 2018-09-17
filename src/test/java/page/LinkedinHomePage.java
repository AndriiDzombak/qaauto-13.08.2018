package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * LinkedinHome Page object class.
 */
public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[@placeholder and @role='combobox']")
    private WebElement searchInputField;

    /**
     * Constructor for LinkedinHomePage
     *
     * @param driver - driver instance from tests
     */
    public LinkedinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assertElementIsVisable(searchInputField, 10,"Home page is not loaded");
    }

    /**
     * Checks if LinkedinHomePage has been loaded
     *
     * @return true - loaded, false - not loaded
     */
    public boolean isPageLoaded(){

        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().contains("LinkedIn")
                && searchInputField.isDisplayed();
    }

    /**
     * User login by username/password
     *
     * @param statement - String with search term
     * @param <T> - generic type to return different PageObjects
     * @return corresponding PageObject: LinkedinSearchPage
     */
    public <T> T searchStatement(String statement){

            searchInputField.sendKeys(statement);
            searchInputField.sendKeys(Keys.ENTER);

        return (T) new LinkedinSearchPage(driver);
    }

}

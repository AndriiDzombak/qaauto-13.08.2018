package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[@placeholder and @role='combobox']")
    private WebElement searchInputField;

    public LinkedinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded(){

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().contains("(1) LinkedIn")
                && profileNavItem.isDisplayed();
    }

    public <T> T searchStatement(String statement){

            searchInputField.sendKeys(statement);
            searchInputField.sendKeys(Keys.ENTER);

        return (T) new LinkedinSearchPage(driver);
    }

}

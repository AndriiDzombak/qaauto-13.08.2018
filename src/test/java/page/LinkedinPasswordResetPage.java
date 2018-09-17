package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;


public class LinkedinPasswordResetPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public LinkedinPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assertElementIsVisable(findAccountButton,10,"Reset Password page is not loaded");
    }

    public boolean isPageLoaded(){

        return getCurrentUrl().contains("/request-password-reset")
                && getCurrentTitle().equals("Reset Password | LinkedIn");

    }

    public <T> T findAccount(String email){
        gMailService = new GMailService();
        gMailService.connect();

        userEmailField.sendKeys(email);
        findAccountButton.click();

        return (T) new LinkedinPleaseCheckEmailPage(driver,gMailService);
    }
}

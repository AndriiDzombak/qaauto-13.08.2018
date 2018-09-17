package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LinkedinYourPasswordResetPage extends LinkedinBasePage{

    @FindBy(xpath = "//input[@name='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@name='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    public LinkedinYourPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assertElementIsVisable(submitButton,10,"Reset your Password page is not loaded");
    }

    public boolean isPageLoaded(){

        return getCurrentUrl().contains("/password-reset")
                && getCurrentTitle().equals("Reset Your Password | LinkedIn")
                && newPasswordField.isDisplayed();

    }

    public <T> T submitNewPassword(String newPsw, String confirmPsw){
        newPasswordField.sendKeys(newPsw);
        confirmPasswordField.sendKeys(confirmPsw);
        submitButton.click();

        return (T) new LinkedinSuccessfulPswChangePage(driver);
    }
}



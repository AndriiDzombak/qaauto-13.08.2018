package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

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
    }

    public boolean isPageLoaded(){
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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



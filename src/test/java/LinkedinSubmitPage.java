import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinSubmitPage extends LinkedinBasePage {

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement alertEmail;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement alertPassword;

    public LinkedinSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded(){

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit")
                && getCurrentTitle().equals("Sign In to LinkedIn");

    }

    public Boolean checkCommonAlertMessage(){
        return alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.");
    }

    public String getEmailAlertMessageText(){
        return alertEmail.getText();
    }

    public String getPasswordAlertMessageText(){
        return alertPassword.getText();
    }

    public String getAlertMessages(String inputFieldType){

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(inputFieldType.equals("email")){
            return getEmailAlertMessageText();
        }
        else if(inputFieldType.equals("password")){
           return getPasswordAlertMessageText();
        }
            else {
            return getEmailAlertMessageText() + " " + getPasswordAlertMessageText();
        }

    }
}

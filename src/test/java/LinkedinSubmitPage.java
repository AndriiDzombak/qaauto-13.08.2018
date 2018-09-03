import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinSubmitPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement alertEmailMessage;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement alertPswMessage;

    public LinkedinSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
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

    public boolean checkAlertMessages(String emailMsg, String passwordMsg, String inputFieldType){

        String actualEmailMsg;
        String actualPswMsg;
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(inputFieldType.contains("email")){
            actualEmailMsg = alertEmailMessage.getText();
            return alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
                    && actualEmailMsg.equals(emailMsg);
        }
        else if(inputFieldType.contains("password")){
            actualPswMsg = alertPswMessage.getText();
            return alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
                    && actualPswMsg.equals(passwordMsg);
        }
            else {
            actualEmailMsg = alertEmailMessage.getText();
            actualPswMsg = alertPswMessage.getText();
            return alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
                    && actualEmailMsg.equals(emailMsg)
                    && actualPswMsg.equals(passwordMsg);
        }

    }
}

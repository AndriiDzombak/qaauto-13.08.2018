import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LinkedinSubmitPage {
    private WebDriver driver;
    private WebElement alertMessage;
    private WebElement alertEmailMessage;
    private WebElement alertPswMessage;

    public LinkedinSubmitPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        alertEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        alertPswMessage = driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
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

    public boolean checkAlertMessages(String emailMsg, String passwordMsg){

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
                && alertEmailMessage.getText().equals(emailMsg)
                && alertPswMessage.getText().equals(passwordMsg);

    }

    public boolean checkAlertMessage(String expectedMsg, String inputFieldName){

        String actualMsg;
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(inputFieldName.contains("email")){
             actualMsg = alertEmailMessage.getText();
        } else {
            actualMsg = alertPswMessage.getText();
        }

        return alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
                && actualMsg.equals(expectedMsg);

    }
}

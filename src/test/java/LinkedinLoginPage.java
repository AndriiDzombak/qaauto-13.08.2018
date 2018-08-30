import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static java.lang.Thread.sleep;

public class LinkedinLoginPage {

    private WebDriver driver;
    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInBtn;
    private WebElement cookieMessage;

    public LinkedinLoginPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }
    private void initElements() {
        userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
        signInBtn = driver.findElement(By.xpath("//input[@id='login-submit']"));
        cookieMessage = driver.findElement(By.xpath("//button[@id='dismiss-alert']"));
}

    public void logIn(String userEmail, String userPassword){
        cookieMessage.click();
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInBtn.click();
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

        return getCurrentUrl().equals("https://www.linkedin.com/")
            && getCurrentTitle().equals("LinkedIn: Log In or Sign Up")
            && signInBtn.isDisplayed();
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }
}

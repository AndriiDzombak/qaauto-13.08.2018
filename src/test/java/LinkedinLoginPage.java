import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInBtn;

    @FindBy(xpath = "//button[@id='dismiss-alert']")
    private WebElement cookieMessage;

    public LinkedinLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public <T> T logIn(String userEmail, String userPassword) {

        cookieMessage.click();
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInBtn.click();
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (getCurrentUrl().contains("/feed")) {
            return (T) new LinkedinHomePage(driver);
        } else if (getCurrentUrl().contains("/login-submit")) {
            return (T) new LinkedinSubmitPage(driver);
        } else {
            return (T) this;
//          return (T) PageFactory.initElements(driver, LinkedinLoginPage.class);
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

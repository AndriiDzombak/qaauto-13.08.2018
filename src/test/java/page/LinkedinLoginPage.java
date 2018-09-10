package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@id='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInBtn;

    @FindBy(xpath = "//button[@id='dismiss-alert']")
    private WebElement cookieMessage;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgetPasswordLink;

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
//          return (T) PageFactory.initElements(driver, page.LinkedinLoginPage.class);
        }
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

    public <T> T resetPassword(){
        forgetPasswordLink.click();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (T) new LinkedinPasswordResetPage(driver);
    }
 }

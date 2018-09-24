package page;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * LinkedinLogin Page object class.
 */
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

    /**
     * Constructor for LinkedinLoginPage
     *
     * @param driver - driver instance from tests
     */
    public LinkedinLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisable(signInBtn, 10,"Login page is not loaded");
    }


    /**
     * User login by username/password
     *
     * @param userEmail - String with userEmail
     * @param userPassword - String with userPassword
     * @param <T> - generic type to return different PageObjects
     * @return one of corresponding PageObjects: LinkedinHomePage | LinkedinSubmitPage | LinkedinLoginPage
     */
    public <T> T logIn(String userEmail, String userPassword) {
        if(cookieMessage.isEnabled()) {
            cookieMessage.click();
        }
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInBtn.click();

        if (isUrlContains("/feed",5)) {
            return (T) new LinkedinHomePage(driver);
        } else if (isUrlContains("/login-submit",5)) {
            return (T) new LinkedinSubmitPage(driver);
        } else {
            return (T) this;
//          return (T) PageFactory.initElements(driver, page.LinkedinLoginPage.class);
        }
    }

    /**
     * Checks if LinkedinLoginPage has been loaded
     *
     * @return true - loaded, false - not loaded
     */
    public boolean isPageLoaded(){

        return getCurrentUrl().contains("https://www.linkedin.com/")
            && getCurrentTitle().contains("LinkedIn: Log In or Sign Up")
            && signInBtn.isDisplayed();
    }

    /**
     * Method for returning private WebElement signInBtn
     *
     * @return
     */
    public WebElement getSignInBtn() {
        return signInBtn;
    }

    /**
     * User navigates to Linkedin Password Reset Page
     *
     * @param <T> generic type to return different PageObjects
     * @return one corresponding PageObject: LinkedinPasswordResetPage
     */
    public <T> T resetPassword(){
        forgetPasswordLink.click();

        return (T) new LinkedinPasswordResetPage(driver);
    }
 }

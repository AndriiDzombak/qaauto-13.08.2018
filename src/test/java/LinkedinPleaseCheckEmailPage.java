import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinPleaseCheckEmailPage extends LinkedinBasePage {

    @FindBy(xpath = "//button[@id='resend-url']")
    public WebElement resendUrlButton;

    public LinkedinPleaseCheckEmailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded(){
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getCurrentUrl().contains("/request-password-reset-submit")
                && getCurrentTitle().equals("Please check your mail for reset password link. | LinkedIn")
                && resendUrlButton.isDisplayed();

    }
}

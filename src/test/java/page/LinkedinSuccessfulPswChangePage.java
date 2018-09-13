package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinSuccessfulPswChangePage extends LinkedinBasePage {

    @FindBy(xpath = "//button[text() = 'Go to homepage']")
    private WebElement goHomeButton;

    public LinkedinSuccessfulPswChangePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded(){
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getCurrentUrl().contains("/checkpoint/rp/password-reset-submit")
                && getCurrentTitle().equals("You've successfully reset your password. | LinkedIn");

    }
    public LinkedinHomePage goToHome(){

            goHomeButton.click();

        return  new LinkedinHomePage(driver);
    }
}

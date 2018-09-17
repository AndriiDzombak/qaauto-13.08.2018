package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LinkedinSuccessfulPswChangePage extends LinkedinBasePage {

    @FindBy(xpath = "//button[text() = 'Go to homepage']")
    private WebElement goHomeButton;

    public LinkedinSuccessfulPswChangePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assertElementIsVisable(goHomeButton,10,"Successful Password Change page is not loaded");
    }

    public boolean isPageLoaded(){

        return getCurrentUrl().contains("/checkpoint/rp/password-reset-submit")
                && getCurrentTitle().equals("You've successfully reset your password. | LinkedIn");

    }
    public LinkedinHomePage goToHome(){
            goHomeButton.click();
        return  new LinkedinHomePage(driver);
    }
}

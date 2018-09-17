package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LinkedinPleaseCheckEmailPage extends LinkedinBasePage {

    private static final String HTML_HREF_TAG_PATTERN = "href=*(\"([^\"]*)\")";
    private Matcher mLink;
    private Pattern pLink;
    private String message;
    private String messageSubject = "here's the link to reset your password";
    private String messageTo = "mineqabox@gmail.com";
    private String messageFrom = "security-noreply@linkedin.com";
    private String link;

    @FindBy(xpath = "//button[@id='resend-url']")
    public WebElement resendUrlButton;

    public LinkedinPleaseCheckEmailPage(WebDriver driver, GMailService gMailService) {
        this.driver = driver;
        this.gMailService = gMailService;
        PageFactory.initElements(driver,this);
        assertElementIsVisable(resendUrlButton,10,"Please check email page is not loaded");
        message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 120);
    }

    public boolean isPageLoaded(){

        return getCurrentUrl().contains("/request-password-reset-submit")
                && getCurrentTitle().equals("Please check your mail for reset password link. | LinkedIn")
                && resendUrlButton.isDisplayed();

    }

    public <T> T navigateToPswReset(){

        ((JavascriptExecutor)driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        driver.get(getLinkfromEmail());

        return (T) new LinkedinYourPasswordResetPage(driver);
    }

    public String getLinkfromEmail() {

        pLink = Pattern.compile(HTML_HREF_TAG_PATTERN);
            mLink = pLink.matcher(message);
            while (mLink.find()) {
                link = mLink.group(2);
                if(link.contains("/e/v2")){
                   break;
                }
            }
          link = link.replace("amp;", "");

        return link;

   }
}

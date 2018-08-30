import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LinkedinHomePage {

    private WebDriver driver;
    private WebElement profileName;
    private WebElement profileNavItem;

    public LinkedinHomePage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    private void initElements() {
        profileName = driver.findElement(By.xpath("//span[text()='Welcome, Andrii!']"));
        profileNavItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
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

        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().contains("(1) LinkedIn")
                && profileName.isDisplayed()
                && profileNavItem.isDisplayed();
    }

}

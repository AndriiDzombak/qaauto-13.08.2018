import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LinkedinLoginTest {

    @Test
    public void successfulLoginTest(){
        //Navigate to Linkedin.com
        //Verify that login page is loaded
        //Enter userEmail
        //Enter userpassword
        //Click on 'Sign in button'
        //Verify Home page is displayed.
        //myqabox@gmail.com
        //qualityassurance123

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "Linkedin","Login page Title is wrong");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement userEmail = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInBtn = driver.findElement(By.xpath("//input[@id='login-submit']"));

        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        Assert.assertTrue(userEmail.isDisplayed(), "email input form does not exist");

        userEmail.sendKeys("myqabox@gmail.com");
        userPassword.sendKeys("qualityassurance123");
        signInBtn.click();

        WebElement profileName = driver.findElement(By.xpath("//span[text()='Andrii Koshman']"));
        WebElement profileNavItem = driver.findElement(By.xpath("li[@id='profile-nav-item']"));
        Assert.assertTrue(profileNavItem.isDisplayed(), "profileNavItem does not exist");
        try{
            Assert.assertTrue(profileName.isDisplayed(), "Such Profile does not exist");
        }catch (NoSuchElementException e){
            System.out.println("No such element");
        }

    }
    @Test
    public void negativeLoginTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "Linkedin","Login page Title is wrong");
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement userEmail = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInBtn = driver.findElement(By.xpath("//input[@id='login-submit']"));

        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        Assert.assertTrue(userEmail.isDisplayed(), "email input form does not exist");

        userEmail.sendKeys("a@b.c");
        userPassword.sendKeys("invalid");
        signInBtn.click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        Assert.assertEquals(alertMessage.getText(),"","Alert message text does not match");
    }
}

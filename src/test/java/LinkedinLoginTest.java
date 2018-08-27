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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LinkedinLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void aftermethod(){
        driver.quit();
    }

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

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "Linkedin","Login page Title is wrong");

        WebDriverWait wait = new WebDriverWait(driver,30);
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

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page Title is wrong");

        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement userEmail = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInBtn = driver.findElement(By.xpath("//input[@id='login-submit']"));
        WebElement cookiePolicy = driver.findElement(By.xpath("//button[@id='dismiss-alert']"));

        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        Assert.assertTrue(userEmail.isDisplayed(), "email input form does not exist");
        cookiePolicy.click();

        userEmail.sendKeys("a@b.c");
        userPassword.sendKeys("inva");
        signInBtn.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn","Login page Title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        WebElement alertEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        WebElement alertPswMessage = driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        wait.until(ExpectedConditions.visibilityOf(alertMessage));

        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(alertMessage.getText(),"There were one or more errors in your submission. Please correct the marked fields below.","Alert message text does not match");
        Assert.assertEquals(alertEmailMessage.getText(),"Please enter a valid email address.");
        Assert.assertEquals(alertPswMessage.getText(),"The password you provided must have at least 6 characters.","Alert message text does not match");

    }

    @Test
    public void negativeEmailLoginTest(){

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page Title is wrong");
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement userEmail = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInBtn = driver.findElement(By.xpath("//input[@id='login-submit']"));
        WebElement cookiePolicy = driver.findElement(By.xpath("//button[@id='dismiss-alert']"));

        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        Assert.assertTrue(userEmail.isDisplayed(), "email input form does not exist");
        cookiePolicy.click();

        userEmail.sendKeys("a@b.com");
        userPassword.sendKeys("indfcafaf");
        signInBtn.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn","Login page Title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        WebElement alertEmailMessage = driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        wait.until(ExpectedConditions.visibilityOf(alertMessage));

        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(alertMessage.getText(),"There were one or more errors in your submission. Please correct the marked fields below.","Alert message text does not match");
        Assert.assertEquals(alertEmailMessage.getText(),"Hmm, we don't recognize that email. Please try again.");

    }

    @Test
    public void negativePswLoginTest(){

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page Title is wrong");

        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement userEmail = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPassword = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement signInBtn = driver.findElement(By.xpath("//input[@id='login-submit']"));
        WebElement cookiePolicy = driver.findElement(By.xpath("//button[@id='dismiss-alert']"));

        wait.until(ExpectedConditions.visibilityOf(signInBtn));
        Assert.assertTrue(userEmail.isDisplayed(), "email input form does not exist");
        cookiePolicy.click();

        userEmail.sendKeys("myqabox@gmail.com");
        userPassword.sendKeys("invalid");
        signInBtn.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit","Login page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn","Login page Title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        WebElement alertPswMessage = driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
        wait.until(ExpectedConditions.visibilityOf(alertMessage));

        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(alertMessage.getText(),"There were one or more errors in your submission. Please correct the marked fields below.","Alert message text does not match");
        Assert.assertEquals(alertPswMessage.getText(),"Hmm, that's not the right password. Please try again or request a new one.");

    }
}

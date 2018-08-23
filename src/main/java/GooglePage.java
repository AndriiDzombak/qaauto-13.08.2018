import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GooglePage {

    public WebDriver driver;


    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void googleSearch(String find) {
       WebElement input = driver.findElement(By.id("lst-ib"));
       input.sendKeys(find);
       input.sendKeys(Keys.RETURN);
    }

    public List<WebElement> searchedResults(){
        List <WebElement> list = driver.findElements(By.xpath("//div[@class='srg']//div[@class='g']"));
        return list;
    }

}


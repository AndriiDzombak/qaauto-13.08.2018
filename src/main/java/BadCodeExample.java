import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {

    public static void main(String args[]){
        WebElement input;
        List<WebElement> list;

        WebDriver driver = new ChromeDriver();
        GooglePage page = new GooglePage(driver);
        driver.get("https://www.google.com/");
        page.googleSearch("selenium");
        list = page.searchedResults();
        Integer size = list.size();
        if (size == 10){
            System.out.println("Search results count is correct: " + size );
        } else {
            System.out.println("Search results count is incorrect: " + size );
        }
        System.out.println(size);
        for(WebElement element: list){

            String elementText = element.getText().toLowerCase();
            if(elementText.contains("selenium")){
                System.out.println("Selenium was found");
            }else{
                System.out.println("Selenium was not found");
            }
        }


    }
}

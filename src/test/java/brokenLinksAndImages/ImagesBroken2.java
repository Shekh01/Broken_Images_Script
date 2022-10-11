package brokenLinksAndImages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImagesBroken2 {
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("https://www.rugartisan.com/sitemap-cms.xml");
		
		List<WebElement> images = driver.findElements(By.tagName("loc"));
        System.out.println("Total number of Images on the Page are " + images.size());
        
        for(int index=0;index<images.size();index++)
        {
        	 WebElement image= images.get(index);
             String imageURL= image.getAttribute("image");
             System.out.println("URL of Image " + (index+1) + " is: " + imageURL);
             verifyLinks(imageURL);
             
             try {
                 boolean imageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript("return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);", image);
                 if (imageDisplayed) {
                     System.out.println("DISPLAY - OK");
                 }else {
                      System.out.println("DISPLAY - BROKEN");
                 }
             } 
             catch (Exception e) {
             	System.out.println("Error Occured");
             }
         }
        driver.quit();
        }
	public static void verifyLinks(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=200)
            {
            	System.out.println("HTTP STATUS - " + httpURLConnect.getResponseMessage() + "is a broken link");
            }    
       
            //Fetching and Printing the response code obtained
            else{
                System.out.println("HTTP STATUS - " + httpURLConnect.getResponseMessage());
            }
        }catch (Exception e) {
      }
   }
	}



package brokenLinksAndImages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Images3 {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		Thread.sleep(5000);
		
		driver.get("https://www.rugartisan.com/sitemap-categories.xml");
		//driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/div[2]/butoon")).click();
		
		Thread.sleep(20000);
		
		List<WebElement> list = driver.findElements(By.tagName("loc"));
		
		System.out.println("Total number of Images on webpage:------->>"+ list.size());
		
		for(WebElement ele : list) {
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(ele.getAttribute("image")).openConnection();
				conn.setRequestMethod("GET");
				int responseCode = conn.getResponseCode();
				if(responseCode !=200) {
					System.out.println("Broken Image:------>>" +ele.getAttribute("image"));
				}
				else {
					System.out.println("Fine Image:-------->>"+ele.getAttribute("image"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		driver.close();

	}

}

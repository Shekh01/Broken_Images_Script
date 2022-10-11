package brokenLinksAndImages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenImagesTest1 {
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://www.rugartisan.co.uk/collection/ombre");
		
		List<WebElement>links = driver.findElements(By.tagName("loc"));
		
		System.out.println("Total links are " +links.size());
		
		for(int i=0;i<links.size();i++) {
			WebElement ele = links.get(i);
			
			String url = ele.getAttribute("img");
			
			verifylinkActive(url);
		}
	}
	public static void verifylinkActive(String linkurl) {
		try
		{
			URL url = new URL(linkurl);
			
			HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();
			httpURLConnect.setConnectTimeout(6000);
			httpURLConnect.connect();
			
			if(httpURLConnect.getResponseCode()==200)
			{
				System.out.println(linkurl+" - "+httpURLConnect.getResponseMessage());
			}
			if(httpURLConnect.getResponseCode()==httpURLConnect.HTTP_NOT_FOUND) {
				System.out.println(linkurl+" - "+httpURLConnect.getResponseMessage() +" - "+httpURLConnect.HTTP_NOT_FOUND);
			}
			
		}catch(Exception e) {
			
		}
	}

}

package brokenLinksAndImages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import graphql.relay.Connection;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinkTest {

	
	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		driver.get("https://www.rugartisan.co.uk/");
		
		//links are assoiciate with a tag
		//images are associated with img tag
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		
		List<WebElement> linksList = driver.findElements(By.tagName("a"));
		
		linksList.addAll(driver.findElements(By.tagName("img")));
		
		System.out.println("size of full links and images---->" + linksList.size());
		List<WebElement> activelinks = new ArrayList<WebElement>();
		
		for(int i=0; i<linksList.size(); i++) {
			if(linksList.get(i).getAttribute("href") !=null) {
				activelinks.add(linksList.get(i));
			}
		}
		
		//get the size of active links list
		System.out.println("size of active links and images---->" + activelinks.size());

		//check the href url with httpconnection api
		
		for(int j=0;j<activelinks.size();j++) {
			
			HttpURLConnection connecion = (HttpURLConnection)new URL(activelinks.get(j).getAttribute("href")).openConnection();
			
			connecion.connect();
			
			connecion.getResponseMessage();  //ok
			connecion.disconnect();
			System.out.println(activelinks.get(j).getAttribute("href") +"---->" );
		}
	}

}

package brokenLinksAndImages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenImagesTest4 {
	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://www.rugartisan.co.uk/collection/ombre");
		
		List<WebElement>links = driver.findElements(By.tagName("a"));
		
		System.out.println("All links :"+ links.size());
		
		Set<String>s = new HashSet<>();
		for(WebElement e : links) {
			String link = e.getAttribute("href");
			
			if(link !=null && !(link.isEmpty())) {
				s.add(link);
			}
		}
		System.out.println(s.size());
		
		HttpURLConnection connection;
		int noOfBrokenLinks = 0;
		for(String url : s) {
		connection = (HttpURLConnection) new URL (url).openConnection();
		connection.connect();
		
		int code = connection.getResponseCode();
		
		if(code>=200) {
			noOfBrokenLinks++;
			System.out.println("Broken Link : " +url);
		}
	}
		System.out.println("Number of Broken Links : "+ noOfBrokenLinks);
		
		driver.close();

}
}

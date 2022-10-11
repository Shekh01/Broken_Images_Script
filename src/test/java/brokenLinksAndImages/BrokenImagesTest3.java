package brokenLinksAndImages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenImagesTest3 {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://www.rugartisan.com/");

		List<WebElement> links = driver.findElements(By.tagName("a"));
		links.addAll(driver.findElements(By.tagName("img")));
		System.out.println("Total no of Links and Images are:" + links.size());

		// 2. Extract active links as well as images
		List<WebElement> activelinks = new ArrayList<WebElement>();

		for (int i = 0; i < links.size(); i++) {
			if (links.get(i).getAttribute("href") != null) {
				activelinks.add(links.get(i));
			}
		}
		// 3 count no of active links as well as inactive link
		System.out.println("Total active links are:" + (activelinks.size()));

		System.out.println("Total Inactive links are:" + (links.size() - activelinks.size()));

		// active links responses

		for (int j = 0; j < activelinks.size(); j++) {

			HttpURLConnection connecion = (HttpURLConnection) new URL(activelinks.get(j).getAttribute("href"))
					.openConnection();

			connecion.connect();

			String response = connecion.getResponseMessage(); // ok
			connecion.disconnect();
			System.out.println(activelinks.get(j).getAttribute("href") + "---->"+response);
			
		}
	}

}

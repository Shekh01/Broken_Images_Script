package brokenLinksAndImages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckAllLinks {
	WebDriver driver;

	@Test
	public void check() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		driver.get("https://www.rugartisan.com/sitemap-products-2.xml");

		List<WebElement> links = driver.findElements(By.tagName("loc"));
		System.out.println(links.size());

		for (int i = 0; i < links.size(); i++) {
			// Thread.sleep(200);
			System.out.println(i + "." + links.get(i).getText());
		}

	}

}

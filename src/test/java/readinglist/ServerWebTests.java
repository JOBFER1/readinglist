package readinglist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReadinglistApplication.class)
public class ServerWebTests {

//	private FirefoxDriver browser;
	private ChromeDriver browser; 

	@BeforeEach
	public void openBrowser() {

//		browser = new FirefoxDriver();
		browser = new ChromeDriver();
        browser.manage().window().maximize();
	}

	@AfterEach
	public void closeBrowser() {
		browser.quit();
	}

	@Test
	public void addBookToEmptyList() {
		
		String baseUrl = "http://localhost:8089/api/pepe";
		browser.get(baseUrl);
		assertEquals("You have no books in your book list", browser.findElement(By.tagName("div")).getText());
		
		browser.findElement(By.name("title")).sendKeys("BOOK TITLE");
		browser.findElement(By.name("author")).sendKeys("BOOK AUTHOR");
		browser.findElement(By.name("isbn")).sendKeys("1234567890");
		browser.findElement(By.name("description")).sendKeys("DESCRIPTION");
		browser.findElement(By.tagName("form")).submit();
		
		WebElement dl = browser.findElement(By.cssSelector("dt.bookHeadline"));
		assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)", dl.getText());
		WebElement dt = browser.findElement(By.cssSelector("dd.bookDescription"));
		assertEquals("DESCRIPTION", dt.getText());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
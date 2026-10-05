package tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

	protected WebDriver driver;

	@BeforeEach
	public void abrirNavegador() {
		ChromeOptions opcoes = new ChromeOptions();
		if (System.getProperty("headless") != null) {
			opcoes.addArguments("--headless=new", "--window-size=1920,1080");
		}
		driver = new ChromeDriver(opcoes);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://automationexercise.com");
	}

	@AfterEach
	public void fecharNavegador() {
		driver.quit();
	}
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContaPage {

	private WebDriver driver;

	@FindBy(css = "h2[data-qa='account-created']")
	private WebElement contaCriada;

	@FindBy(css = "h2[data-qa='account-deleted']")
	private WebElement contaDeletada;

	@FindBy(css = "a[data-qa='continue-button']")
	private WebElement botaoContinuar;

	public ContaPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String mensagemContaCriada() {
		return contaCriada.getText();
	}

	public String mensagemContaDeletada() {
		return contaDeletada.getText();
	}

	public HomePage continuar() {
		botaoContinuar.click();
		return new HomePage(driver);
	}
}

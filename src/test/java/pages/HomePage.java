package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;

	@FindBy(css = "a[href='/login']")
	private WebElement botaoSignupLogin;

	@FindBy(xpath = "//a[contains(text(),'Logged in as')]")
	private WebElement usuarioLogado;

	@FindBy(css = "a[href='/logout']")
	private WebElement botaoLogout;

	@FindBy(css = "a[href='/delete_account']")
	private WebElement botaoDeletarConta;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean estaVisivel() {
		return driver.getTitle().equals("Automation Exercise");
	}

	public LoginPage irParaLogin() {
		botaoSignupLogin.click();
		return new LoginPage(driver);
	}

	public String usuarioLogado() {
		return usuarioLogado.getText();
	}

	public LoginPage sair() {
		botaoLogout.click();
		return new LoginPage(driver);
	}

	public ContaPage deletarConta() {
		botaoDeletarConta.click();
		return new ContaPage(driver);
	}
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	private WebDriver driver;

	@FindBy(xpath = "//h2[text()='Login to your account']")
	private WebElement tituloLogin;

	@FindBy(css = "input[data-qa='login-email']")
	private WebElement campoEmailLogin;

	@FindBy(css = "input[data-qa='login-password']")
	private WebElement campoSenhaLogin;

	@FindBy(css = "button[data-qa='login-button']")
	private WebElement botaoLogin;

	@FindBy(xpath = "//form[@action='/login']/p")
	private WebElement mensagemErroLogin;

	@FindBy(xpath = "//h2[text()='New User Signup!']")
	private WebElement tituloCadastro;

	@FindBy(css = "input[data-qa='signup-name']")
	private WebElement campoNome;

	@FindBy(css = "input[data-qa='signup-email']")
	private WebElement campoEmailCadastro;

	@FindBy(css = "button[data-qa='signup-button']")
	private WebElement botaoCadastro;

	@FindBy(xpath = "//form[@action='/signup']/p")
	private WebElement mensagemErroCadastro;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean tituloLoginVisivel() {
		return tituloLogin.isDisplayed();
	}

	public boolean tituloCadastroVisivel() {
		return tituloCadastro.isDisplayed();
	}

	public void logar(String email, String senha) {
		campoEmailLogin.sendKeys(email);
		campoSenhaLogin.sendKeys(senha);
		botaoLogin.click();
	}

	public String mensagemErroLogin() {
		return mensagemErroLogin.getText();
	}

	public boolean loginBloqueadoPeloNavegador() {
		return temValidacao(campoEmailLogin) || temValidacao(campoSenhaLogin);
	}

	public void cadastrar(String nome, String email) {
		campoNome.sendKeys(nome);
		campoEmailCadastro.sendKeys(email);
		botaoCadastro.click();
	}

	public String mensagemErroCadastro() {
		return mensagemErroCadastro.getText();
	}

	public boolean cadastroBloqueadoPeloNavegador() {
		return temValidacao(campoNome) || temValidacao(campoEmailCadastro);
	}

	public boolean continuaNaPaginaDeLogin() {
		return driver.getCurrentUrl().contains("/login");
	}

	private boolean temValidacao(WebElement campo) {
		String mensagem = campo.getDomProperty("validationMessage");
		return mensagem != null && !mensagem.isEmpty();
	}
}

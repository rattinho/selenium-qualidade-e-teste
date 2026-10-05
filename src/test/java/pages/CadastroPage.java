package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CadastroPage {

	private WebDriver driver;

	@FindBy(xpath = "//b[text()='Enter Account Information']")
	private WebElement titulo;

	@FindBy(id = "id_gender1")
	private WebElement opcaoMr;

	@FindBy(id = "password")
	private WebElement campoSenha;

	@FindBy(id = "days")
	private WebElement selectDia;

	@FindBy(id = "months")
	private WebElement selectMes;

	@FindBy(id = "years")
	private WebElement selectAno;

	@FindBy(id = "newsletter")
	private WebElement checkNewsletter;

	@FindBy(id = "optin")
	private WebElement checkOfertas;

	@FindBy(id = "first_name")
	private WebElement campoPrimeiroNome;

	@FindBy(id = "last_name")
	private WebElement campoSobrenome;

	@FindBy(id = "company")
	private WebElement campoEmpresa;

	@FindBy(id = "address1")
	private WebElement campoEndereco;

	@FindBy(id = "address2")
	private WebElement campoEndereco2;

	@FindBy(id = "country")
	private WebElement selectPais;

	@FindBy(id = "state")
	private WebElement campoEstado;

	@FindBy(id = "city")
	private WebElement campoCidade;

	@FindBy(id = "zipcode")
	private WebElement campoCep;

	@FindBy(id = "mobile_number")
	private WebElement campoCelular;

	@FindBy(css = "button[data-qa='create-account']")
	private WebElement botaoCriarConta;

	public CadastroPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean tituloVisivel() {
		return titulo.isDisplayed();
	}

	public void preencherDadosConta(String senha) {
		opcaoMr.click();
		campoSenha.sendKeys(senha);
		new Select(selectDia).selectByValue("10");
		new Select(selectMes).selectByValue("5");
		new Select(selectAno).selectByValue("2000");
	}

	public void marcarNewsletterEOfertas() {
		checkNewsletter.click();
		checkOfertas.click();
	}

	public void preencherEndereco() {
		campoPrimeiroNome.sendKeys("Felipe");
		campoSobrenome.sendKeys("Silva");
		campoEmpresa.sendKeys("UFF");
		campoEndereco.sendKeys("Rua Passo da Patria, 156");
		campoEndereco2.sendKeys("Bloco E");
		new Select(selectPais).selectByVisibleText("Canada");
		campoEstado.sendKeys("Ontario");
		campoCidade.sendKeys("Toronto");
		campoCep.sendKeys("24210240");
		campoCelular.sendKeys("21999999999");
	}

	public ContaPage criarConta() {
		botaoCriarConta.click();
		return new ContaPage(driver);
	}
}

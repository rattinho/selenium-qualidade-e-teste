package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pages.CadastroPage;
import pages.ContaPage;
import pages.HomePage;
import pages.LoginPage;

public class RegistrarUsuarioTest extends BaseTest {

	private String gerarEmail() {
		return "aluno" + System.currentTimeMillis() + "@teste.com";
	}

	private HomePage criarConta(String nome, String email) {
		HomePage home = new HomePage(driver);
		assertTrue(home.estaVisivel());

		LoginPage login = home.irParaLogin();
		assertTrue(login.tituloCadastroVisivel());

		login.cadastrar(nome, email);

		CadastroPage cadastro = new CadastroPage(driver);
		assertTrue(cadastro.tituloVisivel());

		cadastro.preencherDadosConta("senha123");
		cadastro.marcarNewsletterEOfertas();
		cadastro.preencherEndereco();

		ContaPage conta = cadastro.criarConta();
		assertEquals("ACCOUNT CREATED!", conta.mensagemContaCriada());

		home = conta.continuar();
		assertEquals("Logged in as " + nome, home.usuarioLogado());
		return home;
	}

	private void deletarConta(HomePage home) {
		ContaPage conta = home.deletarConta();
		assertEquals("ACCOUNT DELETED!", conta.mensagemContaDeletada());
		conta.continuar();
	}

	@ParameterizedTest(name = "{index} - nome: [{0}]")
	@CsvSource({
		"Felipe",
		"F",
		"Felipe Gomes Rodrigues da Silva Santos Oliveira"
	})
	public void registrarUsuarioValido(String nome) {
		HomePage home = criarConta(nome, gerarEmail());
		deletarConta(home);
	}

	@ParameterizedTest(name = "{index} - nome: [{0}] email: [{1}]")
	@CsvSource({
		"'', aluno@teste.com",
		"Felipe, emailinvalido",
		"Felipe, ''"
	})
	public void registrarComDadosInvalidos(String nome, String email) {
		HomePage home = new HomePage(driver);
		assertTrue(home.estaVisivel());

		LoginPage login = home.irParaLogin();
		assertTrue(login.tituloCadastroVisivel());

		login.cadastrar(nome, email);

		assertTrue(login.cadastroBloqueadoPeloNavegador());
		assertTrue(login.continuaNaPaginaDeLogin());
	}

	@Test
	public void registrarComEmailJaCadastrado() {
		String email = gerarEmail();
		HomePage home = criarConta("Felipe", email);

		LoginPage login = home.sair();
		login.cadastrar("Outro Nome", email);
		assertEquals("Email Address already exist!", login.mensagemErroCadastro());

		login.logar(email, "senha123");
		deletarConta(new HomePage(driver));
	}
}

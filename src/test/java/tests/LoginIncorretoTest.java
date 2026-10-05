package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pages.HomePage;
import pages.LoginPage;

public class LoginIncorretoTest extends BaseTest {

	@ParameterizedTest(name = "{index} - email: [{0}] senha: [{1}] -> {2}")
	@CsvSource({
		"naoexiste@teste.com, senha123, ERRO",
		"a@b.co, 1, ERRO",
		"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@teste.com, senha123, ERRO",
		"emailsemarroba, senha123, BLOQUEADO",
		"'', senha123, BLOQUEADO",
		"naoexiste@teste.com, '', BLOQUEADO"
	})
	public void loginComDadosIncorretos(String email, String senha, String esperado) {
		HomePage home = new HomePage(driver);
		assertTrue(home.estaVisivel());

		LoginPage login = home.irParaLogin();
		assertTrue(login.tituloLoginVisivel());

		login.logar(email, senha);

		if (esperado.equals("ERRO")) {
			assertEquals("Your email or password is incorrect!", login.mensagemErroLogin());
		} else {
			assertTrue(login.loginBloqueadoPeloNavegador());
			assertTrue(login.continuaNaPaginaDeLogin());
		}
	}
}

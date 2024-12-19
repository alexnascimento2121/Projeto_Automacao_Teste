package br.com.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.core.BaseTeste;
import br.com.page.HomePage;
import br.com.page.MenuPage;

public class SaldoTest extends BaseTeste {
	HomePage page = new HomePage();
	MenuPage menu = new MenuPage();

	@Test
	public void testSaldoConta(){
		menu.acessarTelaPrincipal();
		Assert.assertEquals("534.00", page.obterSaldoConta("Conta para saldo"));
	}
}

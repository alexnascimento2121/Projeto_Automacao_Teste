package br.com.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.core.BaseTeste;
import br.com.page.ContasPage;
import br.com.page.MenuPage;

public class RemoverMovimentacaoContaTest extends BaseTeste {
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();

	@Test
	public void testExcluirContaComMovimentacao(){
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta("Conta com movimentacao");
		
		Assert.assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}

}

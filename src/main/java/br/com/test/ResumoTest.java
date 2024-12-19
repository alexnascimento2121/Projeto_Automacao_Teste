package br.com.test;

import static br.com.core.DriverFactory.getDriver;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.core.BaseTeste;
import br.com.core.DriverFactory;
import br.com.page.MenuPage;
import br.com.page.ResumoPage;

public class ResumoTest extends BaseTeste {
	
	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();

	@Test
	public void test1ExcluirMovimentacao(){
		menuPage.acessarTelaResumo();
		
		resumoPage.excluirMovimentacao();
		
		Assert.assertEquals("Movimentação removida com sucesso!",
				resumoPage.obterMensagemSucesso());
	}
	
//	@Test(expected=NoSuchElementException.class) pode pegar qualquer outro erro sem que seja o que desejo. falso positivo
	@Test
	public void test2ResumoMensal(){
		menuPage.acessarTelaResumo();
		
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
		
//		DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		
//		try{
//			DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
//			Assert.fail();
//		} catch (NoSuchElementException e) {
//			
//		}
		
		resumoPage.selecionarAno("2016");
		resumoPage.buscar();
		
		List<WebElement> elementosEncontrados = 
				DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		Assert.assertEquals(0, elementosEncontrados.size());
		
	}

}

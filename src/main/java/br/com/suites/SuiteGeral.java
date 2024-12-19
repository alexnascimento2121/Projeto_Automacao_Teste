package br.com.suites;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.core.DriverFactory;
import br.com.page.LoginPage;
import br.com.test.ContaTest;
import br.com.test.MovimentacaoTest;
import br.com.test.RemoverMovimentacaoContaTest;
import br.com.test.ResumoTest;
import br.com.test.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	RemoverMovimentacaoContaTest.class,
	SaldoTest.class,
	ResumoTest.class
})
public class SuiteGeral {	
private static LoginPage page = new LoginPage();
	
	@BeforeClass
	public static void reset(){
		page.acessarTelaInicial();
		
		page.setEmail("axpraise1515@gmail.com");
		page.setSenha("123456");
		page.entrar();
		
		page.resetar();
		
		DriverFactory.killDriver();
	}

}

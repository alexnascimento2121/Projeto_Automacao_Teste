package br.com.page;

import org.openqa.selenium.By;

import br.com.core.BasePage;
import br.com.core.DriverFactory;

public class LoginPage extends BasePage {
	
	public void acessarTelaInicial(){
		DriverFactory.getDriver().get("https://seubarriga.wcaquino.me/login");
	}
	
	public void setEmail(String email) {
		escrever("email", email);
	}
	
	public void setSenha(String senha) {
		escrever("senha", senha);
	}
	
	public void entrar(){
		//clicarBotaoPorTexto("Entrar");
		clicarBotao(By.xpath("//button[.='Entrar']"));
	}
	
	public void logar(String email, String senha) {
		setEmail(email);
		setSenha(senha);
		entrar();
	}
	
	public void resetar(){
		clicarLink("reset");
	}

}

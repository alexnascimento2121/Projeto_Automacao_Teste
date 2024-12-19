package br.com.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.core.Propriedades.TipoExecucao;

public class DriverFactory {
	//private static WebDriver driver;
	
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
		@Override
		protected synchronized WebDriver initialValue(){
			return initDriver();
		}
	};
	
	private DriverFactory() {}
	
	public static WebDriver getDriver(){
		return threadDriver.get();
	}
	
	@SuppressWarnings({ "rawtypes", "incomplete-switch" })
	public static WebDriver initDriver(){
		WebDriver driver = null;
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {
			switch (Propriedades.BROWSER) {
				case FIREFOX: driver = new FirefoxDriver(); break;
				case CHROME: driver = new ChromeDriver(); break;
			}
		}
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			AbstractDriverOptions cap = null;
			switch (Propriedades.BROWSER) {
				case FIREFOX: cap = new FirefoxOptions(); break;
				case CHROME: cap = new ChromeOptions(); break;
			}
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.1.100:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
			AbstractDriverOptions cap = null;
			switch (Propriedades.BROWSER) {
				case FIREFOX: cap = new FirefoxOptions(); break;
				case CHROME: cap = new ChromeOptions(); break;
				case IE: cap = new EdgeOptions(); break;
			}
			cap.setPlatformName("Windows 11");
			cap.setBrowserVersion("latest");
			Map<String, Object> sauceOptions = new HashMap<String, Object>();
			sauceOptions.put("username", "oauth-a.n.goncalves.me-43949");
			sauceOptions.put("accessKey", "*****1177");
			sauceOptions.put("build", "selenium-build-TXV9E");
			sauceOptions.put("name", "suites #todos");
			cap.setCapability("sauce:options", sauceOptions);
			try {
				URL url = new URL("https://oauth-a.n.goncalves.me-43949:e1fbe9a9-c16a-4ab4-b29d-d43876211177@ondemand.us-west-1.saucelabs.com:443/wd/hub");
				driver = new RemoteWebDriver(url, cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com a Saucelabs");
				e.printStackTrace();
			}
		}
		driver.manage().window().setSize(new Dimension(1200, 765));			
		return driver;
	}

	public static void killDriver(){
		WebDriver driver = getDriver();
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		if(threadDriver != null) {
			threadDriver.remove();
		}
	}

}

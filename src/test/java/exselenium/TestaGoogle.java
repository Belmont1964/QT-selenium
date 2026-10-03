package exselenium; 



import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestaGoogle {

	protected WebDriver driver;
	
    @BeforeEach
    public void createDriver() {  
    
		driver = new ChromeDriver();
        driver.get("https://www.google.com.br");
        // faz o teste aguardar até 5 segundos para a pagina carregar antes de acusar um erroTest
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
    }	

	@Test
	public void test() {
		WebElement pesquisa = driver.findElement(By.name("q"));
		pesquisa.sendKeys("fafafafafa");
	}
	
	@Test
	public void testaTituloBuscaGoogle() throws InterruptedException {
		WebElement search = driver.findElement(By.name("q"));
		search.sendKeys("teste de software");
		search.submit();		
		assertTrue( driver.getTitle().contentEquals("teste de software - Pesquisa Google"));		
	}
	
	
    @AfterAll
    public static void quitDriver() {
      // driver.quit();
    }
}

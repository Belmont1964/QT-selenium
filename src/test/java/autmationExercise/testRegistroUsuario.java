
package autmationExercise;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Duration;

public class testRegistroUsuario {
	
	protected WebDriver navegador;
		
	@BeforeEach
    public void createNavegador() {  
    
		navegador = new ChromeDriver();  // abre o navegador google
        navegador.get("http://automationexercise.com");  //  carrega a página
        // faz o teste aguardar até 5 segundos para a pagina carregar antes de acusar um erroTest
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));   
    }
	
	
	@Test
	@Order(1)
	@DisplayName("Verificar abertura da página corretamente")
	public void testAberturaPagina() {
		String nome = navegador.findElement(By.cssSelector("a[href='/login']")).getText();
		Assertions.assertTrue(nome.contains("Signup / Login"));
	}
	
	@Test
	@Order(2)
	@DisplayName("Verificar abertura da página de Login corretamente")
	public void testPaginaLogin() {
		navegador.findElement(By.cssSelector("a[href='/login']")).click(); // encontra o menu login na pg principal e clica nele
		String nome = navegador.findElement(By.name("email")).getAttribute("name"); // captura a caixa de entrada de email
		Assertions.assertEquals("email", nome);
	}
	
	@Test
	@Order(3)
	@DisplayName("Verificar login com email incorreto")
	public void testFalhaDeLogin() {
		navegador.findElement(By.cssSelector("a[href='/login']")).click();
		navegador.findElement(By.cssSelector("[data-qa='login-email']")).sendKeys("j1belmont@id.uff.br");
		navegador.findElement(By.cssSelector("[data-qa='login-password']")).sendKeys("1234");
		navegador.findElement(By.cssSelector("[data-qa='login-button']")).click();
		String mensagem = navegador.findElement(By.xpath("//p[text()='Your email or password is incorrect!']")).getText();
		Assertions.assertEquals("Your email or password is incorrect!", mensagem);		
	}
	
	/*
	@Test
	@DisplayName("Verificar entrada em pg de signup")
	public void testEntrarPgSignup() {
		navegador.findElement(By.cssSelector("a[href='/login']")).click();
		navegador.findElement(By.cssSelector("[data-qa='signup-email']")).sendKeys("x3@id.uff.br");
		navegador.findElement(By.cssSelector("[data-qa='signup-name']")).sendKeys("Jose Augusto");
		navegador.findElement(By.cssSelector("[data-qa='signup-button']")).click();
		String mensagem = navegador.findElement(By.xpath("//h2/b[text()='Enter Account Information']")).getText();
		Assertions.assertEquals("ENTER ACCOUNT INFORMATION",mensagem);
	}
	*/
	
	@Test
	@Order(4)
	@DisplayName("Verificar entrada na pagina de signup e criação de conta")
	public void testCriacaoDeConta() {
				
	    // Gera string aleatória de 10 caracteres para email
	    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    StringBuilder stringAleatoria = new StringBuilder();
	    Random random = new Random();
	
	    for (int i = 0; i < 10; i++) {
	        int index = random.nextInt(caracteres.length());
	        stringAleatoria.append(caracteres.charAt(index));
	    }
	    String email = stringAleatoria.toString() + "@id.uff.br";
	    
		
		navegador.findElement(By.cssSelector("a[href='/login']")).click();
		navegador.findElement(By.cssSelector("[data-qa='signup-email']")).sendKeys(email);
		navegador.findElement(By.cssSelector("[data-qa='signup-name']")).sendKeys("Jose Augusto");
		navegador.findElement(By.cssSelector("[data-qa='signup-button']")).click();
		String mensagem = navegador.findElement(By.xpath("//h2/b[text()='Enter Account Information']")).getText();
		Assertions.assertEquals("ENTER ACCOUNT INFORMATION",mensagem); // verifica entrada na pg de criação de conta
		
		navegador.findElement(By.id("id_gender1")).click();
		navegador.findElement(By.id("password")).sendKeys("123");
		WebElement dias = navegador.findElement(By.id("days"));
		Select dia = new Select(dias);
		dia.selectByVisibleText("29");
		WebElement meses = navegador.findElement(By.id("months"));
		Select mes = new Select(meses);
		mes.selectByVisibleText("March");
		WebElement anos = navegador.findElement(By.id("years"));
		Select ano = new Select(anos);
		ano.selectByVisibleText("1964");
		navegador.findElement(By.id("optin")).click();
		navegador.findElement(By.id("first_name")).sendKeys("Jose Augusto");
		navegador.findElement(By.id("last_name")).sendKeys("Belmont");
		navegador.findElement(By.id("company")).sendKeys("Marinha do Brasil");
		navegador.findElement(By.id("address1")).sendKeys("Rua Justina Bulhoes 23");
		navegador.findElement(By.id("address2")).sendKeys("ap 1301");
		WebElement paises = navegador.findElement(By.id("country"));
		Select pais = new Select(paises);
		pais.selectByVisibleText("United States");
		navegador.findElement(By.id("state")).sendKeys("Rio de Janeiro");
		navegador.findElement(By.id("city")).sendKeys("Niteroi");
		navegador.findElement(By.id("zipcode")).sendKeys("24210-455");
		navegador.findElement(By.id("state")).sendKeys("Rio de Janeiro");
		navegador.findElement(By.id("mobile_number")).sendKeys("21-981810966");
		navegador.findElement(By.cssSelector("[data-qa='create-account']")).click();
		mensagem = navegador.findElement(By.xpath("//h2/b[text()='Account Created!']")).getText();
		Assertions.assertEquals("ACCOUNT CREATED!",mensagem);
		navegador.findElement(By.cssSelector("[data-qa='continue-button']")).click();
		mensagem = navegador.findElement(By.xpath("//a[contains(., 'Logged in as')]")).getText();
		Assertions.assertEquals("Logged in as Jose Augusto", mensagem);  // Verifica se o usuário está logado
			
	}
	
	@Test
	@Order(5)
	@DisplayName ("Testa correto logout da conta")
	public void testLogout () {
		navegador.findElement(By.cssSelector("a[href='/login']")).click();
		String mensagem = navegador.findElement(By.xpath("//h2[text()='Login to your account']")).getText();
		Assertions.assertEquals("Login to your account",mensagem);		
	}
	
	@Test
	@Order(6)
	@DisplayName ("Testa login com email existente")
	public void testLoginExistente () {
		navegador.findElement(By.cssSelector("a[href='/login']")).click();
		navegador.findElement(By.cssSelector("[data-qa='login-email']")).sendKeys("jbelmont@id.uff.br");
		navegador.findElement(By.cssSelector("[data-qa='login-password']")).sendKeys("123");
		navegador.findElement(By.cssSelector("[data-qa='login-button']")).click();
		String mensagem = navegador.findElement(By.xpath("//a[contains(., 'Logged in as')]")).getText();
		Assertions.assertEquals("Logged in as Jose Augusto", mensagem);  // Verifica se o usuário está logado		
	}
	
	@Test
	@Order(7)
	@DisplayName ("Testa signup com email existente")
	public void testSignupErro () {
		navegador.findElement(By.cssSelector("a[href='/login']")).click();
		navegador.findElement(By.cssSelector("[data-qa='signup-email']")).sendKeys("jbelmont@id.uff.br");
		navegador.findElement(By.cssSelector("[data-qa='signup-name']")).sendKeys("Jose Augusto");
		navegador.findElement(By.cssSelector("[data-qa='signup-button']")).click();
		String mensagem = navegador.findElement(By.xpath("//p[text()='Email Address already exist!']")).getText();
		Assertions.assertEquals("Email Address already exist!", mensagem);		
	}
	
	
	@AfterEach
    public void quitNavegador() {
      navegador.quit();
    }


	
}

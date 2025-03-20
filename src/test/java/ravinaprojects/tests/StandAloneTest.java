package ravinaprojects.tests;


import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ravinaprojects.pageobject.LandingPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ZARA COAT 3";
		//WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landing = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("Ravina@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products =   driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animation
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection h3"));
		
	Boolean match =	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
		
		
		// below code is normal code 
		
		//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("IND");
		
		//Thread.sleep(3000);
		
		//List<WebElement> options =driver.findElements(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted']"));
		
		//for(WebElement option: options)
		//{
		//	if(option.getText().equalsIgnoreCase("INDIA"))
		//	{
		//		option.click();
		//		break;
		//	}
			
		//}
		
		// this is the same code for above but using Action class
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "IND").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage =  driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		
		
	}

}

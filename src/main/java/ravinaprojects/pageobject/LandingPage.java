package ravinaprojects.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravinaprojects.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;

		public LandingPage(WebDriver driver)
		{
			//initialization
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver,this); // all the pagefactory elements will be initialized using this driver object you got  .
		}
		
		//WebElement userEmail = driver.findElement(By.id("userEmail")); or the below code using pagefactory
		
		//PageFactory
		
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement passwordEle;
		
		@FindBy(id="login")
		WebElement submit;
		
		//div[@class='ng-tns-c4-18 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
		@FindBy(css="[class*='flyInOut']")
		WebElement ErrorMessage;
		
	
		
		public ProductCatalogue loginApplication(String email,String password )
		{
			userEmail.sendKeys(email);
			passwordEle.sendKeys(password);
			submit.click();
			ProductCatalogue productcataloge = new ProductCatalogue(driver);
			return productcataloge;
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(ErrorMessage);
			return ErrorMessage.getText();
			
		}
		
		
		public void goTo()
		
		{
			driver.get("https://rahulshettyacademy.com/client/");
			
		}
		
}
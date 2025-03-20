package ravinaprojects.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ravinaprojects.TestComponents.Basetest;
import ravinaprojects.pageobject.CartPage;
import ravinaprojects.pageobject.CheckoutPage;
import ravinaprojects.pageobject.ProductCatalogue;
import ravinaprojects.pageobject.confirmationPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends Basetest {

	// TODO Auto-generated method stub
	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException , InterruptedException{

		
		//String productName = "ZARA COAT 3";
		landingPage.loginApplication("Ravina@gmail.com", "Passwyord@123"); // Entered Username and Password
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	

	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	
	String productName = "ZARA COAT 3";
	ProductCatalogue productcatalogue = landingPage.loginApplication("Yadav@gmail.com", "Password@123"); // Entered Username and Password 	
	List<WebElement> products=  productcatalogue.getProductList(); //Get list of all products in a List
	productcatalogue.addProductToCart(productName); // calling getProductbyname and adding product to cart
	CartPage cartpage = productcatalogue.goToCartPage();

	Boolean match = cartpage.verifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
	
	
}
	
	
}
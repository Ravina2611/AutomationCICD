package ravinaprojects.tests;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ravinaprojects.TestComponents.Basetest;
import ravinaprojects.pageobject.CartPage;
import ravinaprojects.pageobject.CheckoutPage;
import ravinaprojects.pageobject.OrderPage;
import ravinaprojects.pageobject.ProductCatalogue;
import ravinaprojects.pageobject.confirmationPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends Basetest{

	String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		@Test(dataProvider="getData",groups = {"purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
		{
		
		
		ProductCatalogue productcatalogue = landingPage.loginApplication(input.get("email"), input.get("password")); // Entered Username and Password 	
		List<WebElement>products=  productcatalogue.getProductList(); //Get list of all products in a List
		productcatalogue.addProductToCart(input.get("product")); // calling getProductbyname and adding product to cart
		CartPage cartpage = productcatalogue.goToCartPage();
		
		Boolean match = cartpage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartpage.goToCheckout();
		checkoutPage.selectCounty("india");
		confirmationPage confirmationpage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}

		// To verify ZARA COAT 3 is displaying in orders page
		
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest()
		{
			//"ZARA COAT 3";
			ProductCatalogue productcatalogue = landingPage.loginApplication("ravina@gmail.com", "Password@123");
			OrderPage ordersPage = productcatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
			
		}
		
		
		@DataProvider
		public Object[] [] getData() throws  IOException
		{
			
//			HashMap<String,String>map = new HashMap();
//			map.put("email","ravina@gmail.com");
//			map.put("password","Password@123");
//			map.put("product", "ZARA COAT 3");
//			
//			
//			HashMap<String,String>map1 = new HashMap();
//			map1.put("email","yadav@gmail.com");
//			map1.put("password","Password@123");
//			map1.put("product", "ADDIDAS ORIGINAL");
			
			//List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
//			
			List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ravinaprojects//data//PurchaseOrder.json");
			return new Object[][]  {{data.get(0)}, {data.get(1) } };
			
			
//			  {
//			    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//			    
//			  }
			
			
		}
		
		
}




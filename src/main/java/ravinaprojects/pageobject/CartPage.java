package ravinaprojects.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ravinaprojects.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // all the pagefactory elements will be initialized using this driver
												// object you got .
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> Items;

	@FindBy(css = ".totalRow .btn-primary")
	WebElement checkout;

	public Boolean verifyProductDisplay(String productName) {

		Boolean match = Items.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}

}

package ravinaprojects.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ravinaprojects.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submitbutton;

	// button[contains(@class,'ta-item')])[2]

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCounty(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();

	}

	public confirmationPage submitOrder() {
		submitbutton.click();
		return new confirmationPage(driver);

	}

}

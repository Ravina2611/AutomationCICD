package ravinaprojects.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ravinaprojects.AbstractComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent {

	WebDriver driver;

	public confirmationPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // all the pagefactory elements will be initialized using this driver
												// object you got .
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	public String getConfirmationMessage() {
		return confirmationMessage.getText();

	}

}

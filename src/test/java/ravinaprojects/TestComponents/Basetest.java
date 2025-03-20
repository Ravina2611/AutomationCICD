package ravinaprojects.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ravinaprojects.pageobject.LandingPage;

public class Basetest {

	public WebDriver driver;

	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// Properties class

		Properties prop = new Properties();

		FileInputStream Fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java/ravinaprojects//resources//GlobalData.properties");
		prop.load(Fis); // takes argument as input stream so file needs to be converted into stream
						// beofre passing here so hence the above step
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		//above is java ternary operator if condition is true second argument will execute if condition is false then Third argument will execute

		if (browserName.contains("chrome")) {
			// WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); // full screen run from backend also should
			
		} else if (browserName.equalsIgnoreCase("firfox")) {
			// firfox

		} else if (browserName.equalsIgnoreCase("edge")) {
			// edge
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		// read json to string
		// System.out.println("File path: " + System.getProperty("user.dir") +
		// "//src//test//java//ravinaprojects//data//PurchaseOrder.json");
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);

		// convert String to hashmap Jackson Databind (Jackson databind dependency heps
		// you to convert string content into hashmap

		ObjectMapper mapper = new ObjectMapper();

		// readValue method can help to read the String value and convert it into
		// hashmaps
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

		// {map, map}

	}
	
	
	
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver; //giving driver knowledge to take screenshot
		File source = ts.getScreenshotAs(OutputType.FILE);
		System.out.println(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
		File file = new File(System.getProperty("user.dir")+ "/reports/" + testCaseName + ".png");
		FileUtils.copyFile(source, file); // copying the screenshot file into source folder and destinationpath
		return System.getProperty("user.dir")+ "/reports/" + testCaseName + ".png";
		
	}
	
	

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo(); // Open URL
		return landingPage;
	}

	@AfterMethod
	public void tearDown() {
		driver.close();

	}

}

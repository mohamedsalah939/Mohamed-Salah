package Portal;

import static org.testng.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.FacebookHomePage;
import resources.DataSource;
import resources.base;

public class FacebookLoginTest extends base {
	public WebDriver driver;

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeMethod
	public void initialize() throws IOException, SQLException {
		driver = initializeDriver();

	}

	@Test(dataProvider = "genericDataProviderMethod", dataProviderClass = DataSource.class)
	public void FacebookLoginPostiveFlow(String name, String email)
			throws IOException, InterruptedException, SQLException {
		driver.manage().window().maximize();
		FacebookHomePage fhp = new FacebookHomePage(driver);
		Properties prop = uploadProp();
		String url = prop.getProperty("url");
		String password = prop.getProperty("loginpassword");
		driver.get(url);

		fhp.getEmail().sendKeys(email);
		fhp.getPassword().sendKeys(password);
		fhp.getLogin().click();
		Thread.sleep(3000);
		if (!driver.findElement(By.xpath("//span[text()='" + name + "']")).isDisplayed()) {
			fail("Failed to login");
		}
	}

	@Test(dataProvider = "genericDataProviderMethod", dataProviderClass = DataSource.class)
	public void FacebookLoginNegativeFlow(String email, String password)
			throws IOException, InterruptedException, SQLException {
		driver.manage().window().maximize();
		FacebookHomePage fhp = new FacebookHomePage(driver);
		driver.get("https://www.facebook.com/");

		if (!email.equals("null")) {
			fhp.getEmail().sendKeys(email);
		}
		if (password.equals("null")) {
			fhp.getPassword().sendKeys(password);
		}
		fhp.getLogin().click();
		Thread.sleep(3000);
		if (!fhp.getEmail().isDisplayed()) {
			fail("False login");
		}
	}

	@AfterMethod
	public void teardown() throws SQLException, IOException {
		driver.close();
	}

}

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
import pageObjects.FacebookSignUpPage;
import resources.DataSource;
import resources.base;

public class FacebookSignupTest extends base {
	public WebDriver driver;

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeMethod
	public void initialize() throws IOException, SQLException {
		driver = initializeDriver();

	}

	@Test(dataProvider = "genericDataProviderMethod", dataProviderClass = DataSource.class)

	public void FacebookSignupPositiveFlow(String firstname, String lastname, String email, String password,
			String gender, String year) throws IOException, InterruptedException, SQLException {
		driver.manage().window().maximize();

		FacebookHomePage fhp = new FacebookHomePage(driver);
		FacebookSignUpPage fsp = new FacebookSignUpPage(driver);
		Properties prop = uploadProp();
		String url = prop.getProperty("url");
		driver.get(url);
		fhp.getNewAccount().click();
		fsp.getFirstname().sendKeys(firstname);
		fsp.getLastname().sendKeys(lastname);
		fsp.getRegEmail().sendKeys(email);
		fsp.getRegEmailConfirmation().sendKeys(email);
		fsp.getRegPassword().sendKeys(password);
		fsp.getYear().click();
		driver.findElement(By.xpath("//option[@value='"+year+"']")).click();
		if (gender.equals("male")) {
			fsp.getSex().get(1).click();
		} else {
			fsp.getSex().get(2).click();
		}
		fsp.getWebSubmit().click();
		Thread.sleep(3000);
		if (!fsp.getSignupOTP().isDisplayed()) {
			fail("Fail sign up");
		}

	}

	@Test(dataProvider = "genericDataProviderMethod", dataProviderClass = DataSource.class)

	public void FacebookSignupNegativeFlow(String firstname, String lastname, String email, String password,
			String gender, String year) throws IOException, InterruptedException, SQLException {
		driver.manage().window().maximize();
		FacebookHomePage fhp = new FacebookHomePage(driver);
		FacebookSignUpPage fsp = new FacebookSignUpPage(driver);
		Properties prop = uploadProp();
		String url = prop.getProperty("url");
		driver.get(url);
		fhp.getNewAccount().click();
		if (!firstname.equals("null")) {
			fsp.getFirstname().sendKeys("test");
		}
		if (!lastname.equals("null")) {
			fsp.getLastname().sendKeys("test");
		}
		if (!email.equals("null")) {
			fsp.getRegEmail().sendKeys("test");
		}
		if (!password.equals("null")) {
			fsp.getRegPassword().sendKeys("test");
		}
		if(!year.equals("null")) {
			fsp.getYear().click();
			driver.findElement(By.xpath("//option[@value='"+year+"']")).click();
		}
		if (!gender.equals("null")) {
			fsp.getSex().get(1).click();
		}
		fsp.getWebSubmit().click();
		Thread.sleep(3000);
		if (!fsp.getWebSubmit().isDisplayed()) {
			fail("Wrong signup");
		}
	}

	@AfterMethod
	public void teardown() throws SQLException, IOException {
		driver.close();
	}

}

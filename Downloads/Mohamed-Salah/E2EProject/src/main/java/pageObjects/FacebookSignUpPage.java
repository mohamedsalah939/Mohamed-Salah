package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookSignUpPage {

	public WebDriver driver;

	private By firstname = By.xpath("//input[@name='firstname']");
	private By lastname = By.xpath("//input[@name='lastname']");
	private By regEmail = By.xpath("//input[@name='reg_email__']");
	private By regEmailConfirmation = By.xpath("//input[@name='reg_email_confirmation__']");
	private By regPassword = By.id("password_step_input");
	private By year = By.id("year");
	private By sex = By.xpath("//*[@name='sex']");
	private By webSubmit = By.xpath("//button[@name='websubmit']");
	private By signupOTP = By.id("recovery_code_entry");
	
	public FacebookSignUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getFirstname() {
		return driver.findElement(firstname);
	}

	public WebElement getLastname() {
		return driver.findElement(lastname);
	}

	public WebElement getRegEmail() {
		return driver.findElement(regEmail);
	}
	
	public WebElement getRegEmailConfirmation() {
		return driver.findElement(regEmailConfirmation);
	}

	public WebElement getRegPassword() {
		return driver.findElement(regPassword);
	}
	
	public WebElement getYear() {
		return driver.findElement(year);
	}

	public List<WebElement> getSex() {
		return driver.findElements(sex);
	}

	public WebElement getWebSubmit() {
		return driver.findElement(webSubmit);
	}
	
	public WebElement getSignupOTP() {
		return driver.findElement(signupOTP);
	}

}

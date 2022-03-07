package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookHomePage {

	public WebDriver driver;

private	By email = By.id("email");
private	By password = By.id("pass");
private	By login = By.xpath("//button[@name='login']");
private	By newAccount = By.xpath("//a[@data-testid='open-registration-form-button']");
	
	public FacebookHomePage(WebDriver driver) {
			this.driver = driver;
	}

	public WebElement getEmail() {
		return driver.findElement(email);
	}

	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getLogin() {
		return driver.findElement(login);
	}
	
	public WebElement getNewAccount() {
		return driver.findElement(newAccount);
	}

}

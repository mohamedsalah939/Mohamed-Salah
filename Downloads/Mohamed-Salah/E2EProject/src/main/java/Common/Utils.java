package Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
	private WebDriver driver;

	public Utils (WebDriver driver) {
	    this.driver = driver;
	} 

	public void matSelectInput(String id, String value) {
	    driver.findElement(By.id(id)).click();
	    driver.findElement(By.id(value)).click();
	}
	
	public void waitForWebElement(WebElement webElement){
		try {
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(webElement));
		}
		catch(Exception ex){

		        System.out.println("WebElement "+webElement.toString() +" not found");
		    }

		}
}

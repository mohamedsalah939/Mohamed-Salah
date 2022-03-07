package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class base {

	public WebDriver driver;

	public Properties uploadProp() throws IOException {
		Properties prop;
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);

		return prop;
	}
 
	public WebDriver initializeDriver() throws IOException {
		BasicConfigurator.configure();
		Properties prop = uploadProp();
		String browserName = prop.getProperty("browser");
	//	 System.setProperty("webdriver.chrome.whitelistedIps", "");
		String downloadPath=System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--incognito");
		options.setExperimentalOption("prefs", chromePrefs);
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver(options);
			// execute in chrome driver
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
			// firefox code
		} else if (browserName.equals("IE")) {
           //  IE code
		}

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return driver;

	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;

	}

}

package com.qa.orangehrm.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.orangehrm.errors.AppError;
import com.qa.orangehrm.exceptions.BrowserException;

public class DriverFactory {

	// private WebDriver driver;
	private Properties prop;
	private OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> thLocalDriver = new ThreadLocal<WebDriver>();

	private static final Logger log = LogManager.getLogger(DriverFactory.class);

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		// System.out.println("Entered browser is " + browserName);
		log.info("Entered browser is: " + browserName);

		optionsManager = new OptionsManager(prop);
		
		boolean remoteExecution = Boolean.parseBoolean(prop.getProperty("remote"));

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			
			if(remoteExecution) {
				init_remoteDriver("chrome");
			}
			else {
				thLocalDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if(remoteExecution) {
				init_remoteDriver("firefox");
			}
			else {
				thLocalDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
			}
			break;
		case "edge":
			if(remoteExecution) {
				init_remoteDriver("edge");
			}
			else {
				thLocalDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		default:
			// System.out.println(AppError.INVALID_BROWSER_ERROR_MESG);
			log.error(AppError.INVALID_BROWSER_ERROR_MESG);
			throw new BrowserException("===INVALID BROWSER===");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static WebDriver getDriver() {
		return thLocalDriver.get();
	}

	private void init_remoteDriver(String browserName) {
		log.info("Running tests on selenium grid --"+ browserName);
		try {
			switch (browserName) {
			case "chrome":
				thLocalDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;
				
			case "firefox":
				thLocalDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFireFoxOptions()));
				break;
				
			case "edge":
				thLocalDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;
				
			default:
				log.error("Plz supply the right browser name for selenium grid....");
				log.error(AppError.INVALID_BROWSER_ERROR_MESG);
				log.error("Exception occurred while initializing driver: ");
				throw new BrowserException("=====INVALID BROWSER====");
			}
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	public Properties initProp() {
		prop = new Properties();
		FileInputStream io = null;

		String envName = System.getProperty("env");
		log.info("env passed ========>"+ envName);

		try {
			if (envName == null || envName.trim().toLowerCase().equals("qa")) {
				log.warn("no env is passed, hence running test cases on QA env");
				io = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				switch (envName.trim().toLowerCase()) {
				case "uat":
					io = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;

				case "stage":
					io = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
					
				case "dev":
					io = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;

				default:
					log.error("Invalid env passed, Please pass UAT or no env(default is QA)");
					throw new BrowserException("===INVALID ENVIRONMENT====");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		try {
			prop.load(io);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * 
	 * @return
	 */
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir
	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir
	}

}

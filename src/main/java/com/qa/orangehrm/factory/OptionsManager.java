package com.qa.orangehrm.factory;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private ChromeOptions chOptions;
	private FirefoxOptions ffOptions;
	private EdgeOptions edOptions;
	private Properties prop;
	
	private static final Logger log = LogManager.getLogger(OptionsManager.class);
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {

		chOptions = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			log.info("Test cases are running on headless mode");
			chOptions.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			log.info("Test cases are running on incognito mode");
			chOptions.addArguments("--incognito");
		}
		return chOptions;
	}

	public FirefoxOptions getFireFoxOptions() {

		ffOptions = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			log.info("Test cases are running on headless mode");
			ffOptions.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			log.info("Test cases are running on incognito mode");
			ffOptions.addArguments("--incognito");
		}
		return ffOptions;
	}

	public EdgeOptions getEdgeOptions() {

		edOptions = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			log.info("Test cases are running on headless mode");
			edOptions.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			log.info("Test cases are running on incognito mode");
			edOptions.addArguments("--inPrivate");
		}
		return edOptions;
	}
}

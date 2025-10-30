package com.qa.orangehrm.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.orangehrm.factory.DriverFactory;
import com.qa.orangehrm.pages.DashboardPg;
import com.qa.orangehrm.pages.LoginPg;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	private DriverFactory df;
	protected Properties prop;
	private WebDriver driver;
	protected LoginPg loginPg;
	protected DashboardPg dashboardPg;

	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(@Optional("edge") String browserName) {
		df = new DriverFactory();
		prop = df.initProp();

		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginPg = new LoginPg(driver);
	}
	
	/*
	 * @AfterMethod public void attachScreenShot(ITestResult result) {
	 * if(!result.isSuccess()) {
	 * ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png"); } }
	 */
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		driver = null;
	}
}

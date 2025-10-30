package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.constants.AppConstant;
import com.qa.orangehrm.utils.ElementUtils;

public class DashboardPg {

	private WebDriver driver;
	private ElementUtils eleUtils;
	
	public DashboardPg(WebDriver driver) {
		this.driver = driver;
		eleUtils = new ElementUtils(driver);
	}
	
	private final By dashboardPgHeader = By.cssSelector("h6.oxd-text");
	
	public String getDashboardHeaderText() {
		return eleUtils.waitForElementVisible(dashboardPgHeader, AppConstant.MEDIUM_TIMEOUT).getText();
	}
}

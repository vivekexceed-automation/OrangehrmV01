package com.qa.orangehrm.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.constants.AppConstant;
import com.qa.orangehrm.factory.DriverFactory;
import com.qa.orangehrm.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPg {

	private WebDriver driver;
	private ElementUtils eleUtils;
	
	private static final Logger log = LogManager.getLogger(LoginPg.class);
	
	public LoginPg(WebDriver driver) {
		this.driver = driver;
		eleUtils = new ElementUtils(driver);
	}
	
	private final By loginPgHeader = By.xpath("//h5[text()='Login']");
	private final By username = By.xpath("//input[@name='username']");
	private final By password = By.xpath("//input[@name='password']");
	private final By loginBtn = By.cssSelector("button[type='submit']");
	private final By forgotPwdLink = By.cssSelector("form.oxd-form > div.orangehrm-login-forgot > p.oxd-text");
	private final By shop = By.id("sss");	
	
	public String getLoginPageHeaderText() {
		log.info("Getting login page Header");
		return eleUtils.getElementText(loginPgHeader, AppConstant.LONG_TIMEOUT);
	}
	
	public boolean isForgotPasswordLinkDisplayed() {
		log.info("Checking forgot pwd link is displayed on the page");
		return eleUtils.isElementDisplayed(forgotPwdLink, AppConstant.LONG_TIMEOUT);
	}
	
	@Step("login with correct username: {0} and password: ********")
	public DashboardPg doLogin(String userName, String userPassword) {
		log.info("Entering username: "+ userName + " :: " + "Password: ********");
		eleUtils.enterText(username, AppConstant.LONG_TIMEOUT, userName);
		eleUtils.enterText(password, userPassword);
		eleUtils.clickElement(loginBtn);
		return new DashboardPg(driver);
	}
}

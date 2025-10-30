package com.qa.orangehrm.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ElementUtils {

	private WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public String getElementText(By locator, int timeout) {
		String text = waitForElementVisible(locator, timeout).getText();
		return text;
	}

	@Step("checking the element :{0} is displayed on the page.. ")
	public boolean isElementDisplayed(By locator, int timeout) {
		try {
			waitForElementVisible(locator, timeout).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void enterText(By locator, int timeout, String value) {
		waitForElementVisible(locator, timeout).sendKeys(value);
		;
	}

	public void enterText(By locator, String value) {
		getElement(locator).sendKeys(value);
		;
	}

	public void clickElement(By locator) {
		getElement(locator).click();
	}
	
	@Step("waiting for page title with expected value: {0}")
	public String waitForTitleIs(String expectedTitleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		try {
			wait.until(ExpectedConditions.titleIs(expectedTitleValue));
		} catch (TimeoutException e) {
			System.out.println("expected title value : " + expectedTitleValue + " is not present");
		}

		return driver.getTitle();
	}

	@Step("waiting for element :{0} visible within the timeout: {1}")
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
}

package com.qa.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstant;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPgTest extends BaseTest {


	@Test
	public void verifyLoginPageHeaderText() {
		String actLoginPgHeaderText = loginPg.getLoginPageHeaderText();
		Assert.assertEquals(actLoginPgHeaderText, AppConstant.EXP_LOGIN_PG_HEADER);
	}

	@Severity(SeverityLevel.NORMAL)
	@Owner("Vivek Mohadikar")
	@Test
	public void verifyForgotPwdLinkIsDisplayed() {
		Assert.assertTrue(loginPg.isForgotPasswordLinkDisplayed());
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Integer.MAX_VALUE)
	public void LoginTest() {
		dashboardPg = loginPg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(dashboardPg.getDashboardHeaderText(), AppConstant.EXP_DASHBOARD_PG_HEADER);
	}
}

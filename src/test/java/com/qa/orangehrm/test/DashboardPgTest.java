package com.qa.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BaseTest;
import com.qa.orangehrm.constants.AppConstant;

public class DashboardPgTest extends BaseTest{

	@BeforeClass
	public void setUpDashboardPg() {
		dashboardPg = loginPg.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyDashboardPgHeaderTest() {
		String actDashboardPgHeaderText = dashboardPg.getDashboardHeaderText();
		Assert.assertEquals(actDashboardPgHeaderText, AppConstant.EXP_DASHBOARD_PG_HEADER);
	}
}

package com.qa.opencart.tests;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ProductInfoPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic-200:Open cart apllication  Accounts page design")
@Story("US-201:Design  Accountspage features")
public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void acSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
    @Description("accPageTitleTest")
    @Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accPageTitleTest() {
		System.out.println("1st test");
		String actPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actPageTitle, AppConstants.ACC_PAGE_TITLE);
	}
    @Description("accPageUrlTest")
    @Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageUrlTest() {
		System.out.println("2nd test");
		Assert.assertTrue(accPage.getAccPageUrl());
	}
    
    @Description("searchExistTest")
    @Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void searchExistTest() {
		System.out.println("3rd test");
		Assert.assertTrue(accPage.isSearchExist());
	}
    @Description("logoutLinkExistTest")
    @Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		System.out.println("4th test");
		Assert.assertTrue(accPage.isLogoutLinklExist());
	}
    @Description("accPageHeadersTest")
    @Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 5)
	public void accPageHeadersTest() {
		System.out.println("5th test");
		ArrayList<String> actHeadersList = accPage.getAccSecHeadersList();
		System.out.println("Act AccPage Headers :" + actHeadersList);
	}

	@DataProvider(name = "getProductKey")
	public Object[][] getProductKey() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Samsung" }, };

	}
	
	
	@Description("accPage search check Test")
    @Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider="getProductKey",priority = 6)
	public void searchCheckTest(String getProductKey) {
		searchResultsPage = accPage.performSearch(getProductKey);
		Assert.assertTrue(searchResultsPage.isSearchSucessful());
	}

	@DataProvider(name="getProductData")
	public Object[][] getProductData()
	{
		return new Object[][] { { "Macbook", "MacBook Pro" },
			{ "Macbook", "MacBook Air" },
			{ "iMac", "iMac" },
				{ "Samsung", "Samsung SyncMaster 941BW" },
				{ "Samsung", "Samsung Galaxy Tab 10.1" } };

	}
	
	@Description("accPage product search Test")
    @Severity(SeverityLevel.BLOCKER)
	@Test(dataProvider = "getProductData",priority = 7)
	public void searchTest(String searchKey, String mainProductName) {
		searchResultsPage = accPage.performSearch(searchKey);
		if (searchResultsPage.isSearchSucessful()) {
			productInfoPage = searchResultsPage.selectProduct(mainProductName);
			String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
		}

	}

}

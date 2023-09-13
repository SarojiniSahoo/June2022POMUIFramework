package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class productPageTest extends BaseTest {
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		searchResultsPage = accPage.performSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		String actPageHeader = productInfoPage.getProductHeader("MacBook Pro");
		Assert.assertEquals(actPageHeader, "MacBook Pro");
	}

	@DataProvider(name = "getProductInfoData")
	public Object[][] getProductInfoData() {
		return new Object[][] { { "Macbook", "MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT },
				{ "Macbook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT },
				{ "iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT },

		};
	}

	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainProduct, int imagesCount) {
		searchResultsPage = accPage.performSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(mainProduct);
		int actProductImages = productInfoPage.getProductImagesCount();
		System.out.println("act product images :" + actProductImages);
		Assert.assertEquals(actProductImages, imagesCount);
	}
	@Test
	public void productMetadataTest() {
		searchResultsPage = accPage.performSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String>actMetaDataMap=productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}
	

}

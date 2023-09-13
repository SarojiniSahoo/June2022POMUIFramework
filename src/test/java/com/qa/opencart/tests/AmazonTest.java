package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class AmazonTest {
	WebDriver driver;
	By amazonData=By.xpath("//div[@id=\"content\"]//ul[@class=\"list-unstyled\"][1]/li/a");
	@Test
	public void test1(WebDriver driver) {
		List<WebElement> ele = driver.findElements(By.xpath("//div[@id=\\\"content\\\"]//ul[@class=\\\"list-unstyled\\\"][1]/li/a"));
		
		for(int i=ele.size();i>=0;i--) {
			String str = ele.get(i).getText();
			System.out.println(str);
		}
	}

}

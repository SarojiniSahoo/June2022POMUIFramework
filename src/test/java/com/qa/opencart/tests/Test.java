package com.qa.opencart.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class Test {
	@BeforeSuite
	public void test1() {
		System.out.println("Before Suite");
	}
	@BeforeTest
	public void test2() {
		System.out.println("Before test");
	}
	@BeforeClass
	public void test3() {
		System.out.println("Before class");
	}
	@BeforeMethod
	public void test4() {
		System.out.println("Before method");
	}
	@org.testng.annotations.Test
	public void test5() {
	System.out.println("test5");	
	}
	
	@org.testng.annotations.Test
	public void test6() {
		System.out.println("test6");
	}
	
	@AfterMethod
	public void test7() {
		System.out.println("After Method");
	}
	@AfterClass
	public void test8() {
		System.out.println("After Class");
	}
	@AfterTest
	public void test9() {
		System.out.println("After Test");
	}
	@AfterSuite
	public void test10() {
		System.out.println("After Suit");
	}
	
	
}

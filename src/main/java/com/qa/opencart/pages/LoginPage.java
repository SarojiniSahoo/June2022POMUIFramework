package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	// By locator:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By logInBtn = By.xpath("//input[@value='Login']");
	private By logoImg = By.cssSelector("img[title='naveenopencart']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
//page const....

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
//page actions:
	@Step("waiting for login page title or fetching the title")
	public String loginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("login page title :" + title);
		return title;
	}
	
	@Step("waiting for login page url or fetching the url")
	public boolean getLoginPageUrl() {
String url=eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);	
		System.out.println("login page url:" + url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	@Step("checking forgot pwd link is displayed on login page")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doElementIsDisplayed(forgotPwdLink);
	}
	@Step("login with username:{0} and password:{1}")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("user creds are:" + username + ":" + pwd);
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_LARGE_TIME_OUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(logInBtn);
		return new AccountsPage(driver);
	}
	@Step("navigating to the register page")
	public RegisterPage navigateToRegisterPage() {
		System.out.println("navigate To Register Page.....");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}

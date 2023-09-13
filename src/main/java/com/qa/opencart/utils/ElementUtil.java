package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
private	WebDriver driver;
private Select select;
private Actions act;
private JavaScriptUtill jsUtil;
	private static final String EKEMENT_NOT_FOUND_ERROR="element is not available on the page";
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		act=new Actions(driver);
		jsUtil=new JavaScriptUtill(driver);
	}
	
	
	public void doSendKeys(By locator,String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public  WebElement getElement(By locator) {
		
		WebElement ele=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
		};
		
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	public 
	String doGetText(By locator) {
		return getElement(locator).getText();
	}
	public String grAttribute(By locator,String atrName) {
		return getElement(locator).getAttribute(atrName);
	}
	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	public boolean isSingleElementPage(By locator) {
		List<WebElement>list=getElements(locator);
		System.out.println(list.size());
		if(list.size()==1) {
	    	System.out.println("single search element is present on the page");
	    	return true;
	    }
	    else {
	    	System.out.println("no search or multiple search is present on the page");
	    	return false;
	    }
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	public  int getElementCount(By locator) {
		return getElements(locator).size();
	}
	public ArrayList<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> eleTextList = new ArrayList<String>();
		
		for(WebElement e : eleList) {
			String text = e.getText();
				if(text.length()!=0) {
					eleTextList.add(text);
				}
		}
		
		return eleTextList;
	}
	public  void doSelectDropDownByIndex(By locator,int index) {
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);
	}
	public  void doSelectDropDownByVisibleText(By locator,String text) {
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	public  void doSelectDropDownByValue(By locator,String value) {
		Select select=new Select(getElement(locator));
		select.selectByValue(value);
	}
	public  void doSelectValueFromDropDown(By locator,String value) {
		List<WebElement> optionsList=getElements(locator);
		System.out.println(optionsList.size());
		for(WebElement e:optionsList) {
			String text=e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
		}	
	}
	public  void doSearch(By searchLocator,String searchKey,By suggLocator, String value) throws InterruptedException {
		getElement(searchLocator).sendKeys(searchKey);
		Thread.sleep(3000);
		List<WebElement> sugglist=getElements(suggLocator);
		System.out.println(sugglist.size()-1);
		for(WebElement e:sugglist) {
			String text=e.getText();
			System.out.println(text);
			if(text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	public void doSearch(String tagName,String text) {
		By suggLocator=By.xpath("//"+tagName+"['text"+text+"']");
		getElement(suggLocator).click();
	}
	public boolean checkElementIsMandatory(String jsScript) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String man_text = js.executeScript(jsScript).toString();

		System.out.println(man_text);

		if (man_text.contains("*")) {
			System.out.println("ele is a mandatory field");
			return true;
		} else {
			System.out.println("FN is not a mandatory field");
			return false;
		}
	
}
	public void handleLevel1MenuItems(By parentMenu, By childMenu) throws InterruptedException {
		act.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(3000);
		// getElement(childMenu).click();
		doClick(childMenu);
	}

	public void doActionsClick(By locator) {
		act.click(getElement(locator)).build().perform();
	}

	public void doActionsSendkeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).build().perform();
	}
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void doSendKeysWithWait(By locator, int timeOut, String value) {	
		waitForElementPresence(locator, timeOut).sendKeys(value);
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForElementPresence(locator, timeOut).click();
	}

	public String getElementTextWithWait(By locator, int timeOut) {
		return waitForElementPresence(locator, timeOut).getText();
	}
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void doSendKeysWithVisibleElement(By locator, int timeOut, String value) {
		waitForElementVisible(locator, timeOut).sendKeys(value);
	}

	public void doClickWithVisibleElement(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	public String getElementTextWithVisibleElement(By locator, int timeOut) {
		return waitForElementVisible(locator, timeOut).getText();
	}

	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void alertSendKeys(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);
	}

	public String waitForTitleContains(int timeOut, String titleFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForTitleIs(int timeOut, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(titleValue))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForUrlContains(int timeOut, String urlFraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	public String waitForUrlIs(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

}
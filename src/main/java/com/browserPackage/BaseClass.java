package com.browserPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.Utility;


public class BaseClass {

	public Properties prop;
	public static WebDriver driver;
	public HashMap<String,Object> eventInfo;
	
	public BaseClass() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/env/config.properties");
		prop.load(fis);
		eventInfo = new HashMap<String, Object>();
	}

	public void initiliazeWebDriver() throws IOException {
		String browser = prop.getProperty("browser");
		String completeURL = Utility.getConfigProperty("loginUrl");
		ChromeOptions chromeOptions = new ChromeOptions();
		if (driver == null) {
			if (browser.equals("chrome") && (System.getProperty("os.name").contains("Win")|| System.getProperty("os.name").contains("win"))) {
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\browser\\chromedriver\\chromedriver.exe");
				chromeOptions.addArguments("--start-maximized");
				//chromeOptions.addArguments("--incognito");
				driver = new ChromeDriver(chromeOptions);
				Log.LogInfo("Browser is opened");
			}
			else if  (browser.equals("chrome") && (System.getProperty("os.name").contains("Mac")|| System.getProperty("os.name").contains("mac"))) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();

				System.setProperty("webdriver.chrome.driver","browser/chromedriver/chromedriver");
				//chromeOptions.addArguments("incognito");// uncomment if the user wants to run in incognito mode
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				driver = new ChromeDriver(chromeOptions);
				Log.LogInfo("browser opened");
			}
			driver.manage().window().maximize();
			driver.get(completeURL);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
			
		}
	}


	public void threadSleep(int timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Common explicit waits
	public WebElement waitTillElementVisible(WebElement webElement, int timeoutInSeconds) {
		WebDriverWait eWait = new WebDriverWait(driver, timeoutInSeconds);
		eWait.until(ExpectedConditions.visibilityOf(webElement));
		return webElement;
	}
	
	public WebElement waitTillElementVisible(WebElement webElement) {
		return waitTillElementVisible(webElement, 30);
	}
    
	public WebElement waitTillElementClickable(WebElement webElement, int timeoutInSeconds) {
		WebDriverWait eWait = new WebDriverWait(driver, timeoutInSeconds);
		eWait.until(ExpectedConditions.elementToBeClickable(webElement));
		return webElement;
	}
	
	public WebElement waitTillElementClickable(WebElement webElement) {
		return waitTillElementClickable(webElement, 30);
	}

	public WebElement waitTillTextToBePresent(WebElement webElement, String text, int timeoutInSeconds) {
		WebDriverWait eWait = new WebDriverWait(driver, timeoutInSeconds);
		eWait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
		return webElement;
	}

	public WebElement waitTillTextToBePresent(WebElement webElement, String text) {
		return waitTillTextToBePresent(webElement, text, 30);
	}

	public WebElement waitTillElementLocated(By locator, int timeoutInSeconds) {
		WebDriverWait eWait = new WebDriverWait(driver, timeoutInSeconds);
		eWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
	}

	public WebElement waitTillElementLocated(By locator) {
		return waitTillElementLocated(locator, 30);
	}

	public List<WebElement> waitTillElementsVisible(List<WebElement> webElements, int timeoutInSeconds) {
		WebDriverWait eWait = new WebDriverWait(driver, timeoutInSeconds);
		eWait.until(ExpectedConditions.visibilityOfAllElements(webElements));
		return webElements;
	}

	public List<WebElement> waitTillElementsVisible(List<WebElement> webElements) {
		return waitTillElementsVisible(webElements, 30);
	}

	public void waitTillURLPresent(String url, int timeoutInSeconds) {
		WebDriverWait eWait = new WebDriverWait(driver, timeoutInSeconds);
		eWait.until(ExpectedConditions.urlContains(url));
	}

	public void waitTillURLPresent(String url) {
		waitTillURLPresent(url, 30);
	}

	public void implicitWait(int timeoutInSeconds) {
		driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
	}

	// Common selenium webdriver manipulation methods

	public void sendKeys(WebElement webElement, String text, int timeoutInSeconds) {
		waitTillElementVisible(webElement, timeoutInSeconds);
		webElement.sendKeys(text);
	}
	
	public void sendKeys(WebElement webElement, Keys key, int timeoutInSeconds) {
		waitTillElementVisible(webElement, timeoutInSeconds);
		webElement.sendKeys(key);
	}

	public void sendKeys(WebElement webElement, String text) {
		sendKeys(webElement, text, 30);
	}
	
	public void sendKeys(WebElement webElement, Keys key) {
		sendKeys(webElement, key, 30);
	}

	public void click(WebElement webElement, int timeoutInSeconds) {
		int retries=1;
		while(retries<=10) {
			try {
				waitTillElementClickable(webElement, timeoutInSeconds);
				if (retries == 5) { jsScroll(webElement); }
				moveToElement(webElement).click();
				return;
			}
			catch (ElementClickInterceptedException ce) {
				System.out.println("ElementClickInterceptedException Found - Retrying Click# " + retries);
			}
			catch (StaleElementReferenceException se) {
				System.out.println("StaleElementException Found - Retrying Click# " + retries);
			}
			retries++;
			threadSleep(2);
		}
		Assert.fail("Failed on clicking the web element");
	}

	public void click(WebElement webElement) {
		click(webElement, 30);
	}
	
	public void retryClickByActionsClass(WebElement webElement, int retryCount) {
		for (int count = 0; count<retryCount; count++) {
			Actions action = new Actions(driver);
			action.moveToElement(webElement).click().build().perform();
		}
	}
	
	public String getText(WebElement webElement) {
		waitTillElementVisible(webElement, 60);
		return webElement.getText().trim();
	}
		
	public WebElement createDynamicLocator(String locatorType, String locator, String dynamicValue) {
		//TODO - Need to handle all type of locators....
		WebElement element = null;
		locator = locator.replace("$regex$", dynamicValue);
		switch(locatorType) {
		case "xpath":
			waitTillElementVisible(driver.findElement(By.xpath(locator)), 60);
			element = driver.findElement(By.xpath(locator));
			break;
		}
		return element;
	}

	public List<WebElement> createDynamicLocators(String locatorType, String locator, String dynamicValue) {
		//TODO - Need to handle all type of locators....
		List<WebElement> elements = null;
		locator = locator.replace("$regex$", dynamicValue);
		switch(locatorType) {
		case "xpath":
			waitTillElementVisible(driver.findElement(By.xpath(locator)), 60);
			elements = driver.findElements(By.xpath(locator));
			break;
		}
		return elements;
	}
	
	public WebElement moveToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.build().perform();
		return element;
	}
	
	public WebElement jsScroll(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", element);
		threadSleep(2);
		return element;
	}
	
	public void jsScrollXAndY(String xAxis, String yAxis) {
		((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy("+xAxis+","+yAxis+")");
	}
	
	public String getCssValue(WebElement element, String propertyName) {
		return element.getCssValue(propertyName).trim();
	}
	
	public boolean isElementPresent(List<WebElement> elements) {
		implicitWait(5);
		boolean elementPresent = (elements.size() > 0);
		implicitWait(10);
		return elementPresent;
	}
	
	public boolean isElementPresent(List<WebElement> elements, int waitTime) {
		implicitWait(waitTime);
		boolean elementPresent = (elements.size() > 0);
		implicitWait(10);
		return elementPresent;
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);
		threadSleep(1);
	}
	
	public void navigateToURL(String URL){
		driver.navigate().to(URL);
	}


}

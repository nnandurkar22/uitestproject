package pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import resources.BaseTest;


public class PaymentConfirmationPage extends BaseTest {
	
	public WebDriver driver;
	
	public PaymentConfirmationPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static final By byPageHeader=By.xpath("//h2");
	public static final By byMessage=By.className("text-justify");
	

	public void verifyPageHeader(String expHeader)
	{
//		waitUtils.waitForElementToLoad(ExpectedConditions.visibilityOfElementLocated(byPageHeader));
		String actualHeader=driver.findElement(byPageHeader).getText();
		Assert.assertTrue(actualHeader.equals(expHeader), "Payment Success page header is not displayed correctly. Expected header is: "+expHeader+" and Actual header is: "+actualHeader);
	}
	
	public void verifyDisplayeMessage(String expMessage)
	{
		String actuaMessage=driver.findElement(byMessage).getText();
		Assert.assertTrue(actuaMessage.equals(expMessage),"Payment Success Message is not displayed correctly. Expected message is: ["+expMessage+"] and Actual message is: ["+actuaMessage+"]");
	}
	
	
}

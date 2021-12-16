package pageobjects;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import resources.BaseTest;


public class CheckoutPage extends BaseTest {
	
	public WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static final By byPageHeader=By.xpath("//h2");
	public static final By byGridHeaders=By.xpath("//table/thead/tr/th");
	public static final By byGridRows=By.xpath("//table/tbody/tr");
	public static final By byTotalPrice=By.id("total");
	public static final By byPayButton=By.xpath("//button[span[contains(text(),'Pay with Card')]]");
	
	
	

	public void verifyPageHeader(String expHeader)
	{
		String actualHeader=driver.findElement(byPageHeader).getText();
		try {
		Assert.assertTrue(actualHeader.equals(expHeader), "Payment Success page header is not displayed correctly. Expected header is: "+expHeader+" and Actual header is: "+actualHeader);
		}
		catch(Exception e)
		{
			
		}
		}
	
	public void verifyCartGridValuesAndTotal(Map<String,Long> map)
	{
		Long expTotal=0L;
		for(Long n:map.values())
		{
			expTotal=expTotal+n;
		}
		
		Long actualTotal=Long.parseLong(driver.findElement(byTotalPrice).getText().replaceAll("[^0-9]",""));
		System.out.println("actualTotal "+actualTotal);
		System.out.println("expTotal "+expTotal);
		
		
		Assert.assertTrue(map.size() == getRowCount(),"Rows other than selected products are also displayed.");
		boolean result=false;
		List<String> incorrectName=new LinkedList<String>();
		List<Long> incorrectPrice=new LinkedList<Long>();
		
		for(int i=0;i<getRowCount();i++)
		{
			String itemName=getCellValue(i,0);
			Long price= Long.parseLong(getCellValue(i,1)) ;
			
			if(map.containsKey(itemName) && map.get(itemName).equals(price))
			{
				result=true;
			}
			else
			{
				result=false;
				incorrectName.add(itemName);
				incorrectPrice.add(price);
				break;
			}
		}
		try {
		Assert.assertTrue(result,"Few items are incorrectly displayed. These are: "+incorrectName);
		}
		catch(Exception e)
		{}
		try {
		Assert.assertTrue(expTotal.equals(actualTotal), "Total price is not displayed correctly on Checkout page.");
		}
		catch(Exception e)
		{}
		
	}
	
	public void verifyCartGridHeaders(List<String> expHeaders)
	{
		List<String> actualHeaders=new LinkedList<String>();
		for(WebElement e:driver.findElements(byGridHeaders))
		{
			actualHeaders.add(e.getText());
		}
		
		
		Assert.assertTrue(actualHeaders.containsAll(expHeaders),"Cart Grid Headers are not displayed correctly");
		
	}
	
	public String getCellValue(int row, int col)
	{
		List<WebElement> rows=driver.findElements(byGridRows);
		String val=rows.get(row).findElement(By.xpath("td["+(col+1)+"]")).getText();
		return val;
	}
	
	public int getRowCount()
	{
		return driver.findElements(byGridRows).size();
	}
	
	public int getColumnCount()
	{
		return driver.findElements(byGridRows).get(0).findElements(By.xpath("td")).size();
	}
	
	public void clickPayWithCardButton()
	{
		waitUtils.get().waitForElementToLoad(ExpectedConditions.elementToBeClickable(byPayButton));
//		driver.findElement(byPayButton);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(byPayButton));
				
	}
	
	
}

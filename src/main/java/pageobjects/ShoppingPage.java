package pageobjects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import resources.BaseTest;


public class ShoppingPage extends BaseTest {
	
	public WebDriver driver;
	
	public ShoppingPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static final By byCartButton=By.xpath("//button[contains(text(),'Cart')]");
	public static final By byAddButtons=By.xpath("//button[@class='btn btn-primary']");
	public static final By byProdPriceList=By.xpath("//p[contains(text(),'Price')]");
	public static final By byProdNames=By.xpath("//p[contains(text(),'Price')]/preceding-sibling::p");
	public static final By byCartButtonText=By.xpath("//button[contains(text(),'Cart')]/span");
	public static final By byPageHeader=By.xpath("//h2");
	public static final By byFirstAddButton=By.xpath("//button[@class='btn btn-primary']");
	
	
	
	
	private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map)
	{
		List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
		
	}
	
	public void verifyPageHeader(String expHeader)
	{
		String actualHeader=driver.findElement(byPageHeader).getText();
		Assert.assertTrue(actualHeader.equals(expHeader), "Payment Success page header is not displayed correctly. Expected header is: "+expHeader+" and Actual header is: "+actualHeader);
	}
	
	public Map<String,Long> addCheapestProductsToCart(String prodName, Map<String,Integer> prodQtyMap)
	{
		Map<String,Long> finalMap=new LinkedHashMap<String,Long>();
		try {
			for(Entry<String, Integer> ent:prodQtyMap.entrySet())
			{
			Map<String,Long> prodToPriceMap=new LinkedHashMap<String,Long>();
			List<WebElement> prodNameList=driver.findElements(byProdNames);
			
			
			for(WebElement e:prodNameList)
			{
				String val1=e.getText();
				String val2=e.findElement(By.xpath("following-sibling::p")).getText().replaceAll("[^0-9]","");
				if(val1.toLowerCase().contains(ent.getKey().toLowerCase()))
				{
				prodToPriceMap.put(e.getText(),Long.parseLong(val2));
				}
				
			}
			
			prodToPriceMap=sortByValue(prodToPriceMap);
			System.out.println(prodToPriceMap);
			int i=0;
			
			for(Entry<String,Long> e:prodToPriceMap.entrySet())
			{
				if(i>=ent.getValue())
				{
					break;
				}
				driver.findElement(By.xpath("//p[contains(text(),'"+e.getKey()+"')]/following-sibling::button[text()='Add']")).click();
				finalMap.put(e.getKey(),e.getValue());
				i++;
			}
			
			Assert.assertFalse(driver.findElement(byCartButtonText).getText().equalsIgnoreCase("empty"), "Failed to add items to the cart from "+prodName+" page.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
			return finalMap;
	}
	
	public void clickOnCartButton()
	{
		driver.findElement(byCartButton).click();
	}
}

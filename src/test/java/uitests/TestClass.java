package uitests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.ShoppingPage;
import resources.BaseTest;

public class TestClass extends BaseTest {
	
	@BeforeClass
	public void setup()
	{
		initializeDriver();
		initializePageObjects();
		openTestURL();
	}
	
	@Test
	public void endToEndTest()
	{
		
		String currTemp=landingPage.get().getCurrentTemperature();
		System.out.println(currTemp);
		int currTempInt=Integer.parseInt(currTemp);
		Map<String,Integer> map=new HashMap<String,Integer>();
		Map<String,Long> outMap=new HashMap<String,Long>();
		
		  if(currTempInt<19) 
		  { 
			  System.out.println("Temperature is less than 19 degree hence, shop for Moisturizers");
			  landingPage.get().clickMoisturizersButton();
			  shoppingPage.get().verifyPageHeader("Moisturizers");
			  waitUtils.get().waitForElementToLoad(ExpectedConditions.visibilityOfElementLocated(ShoppingPage.byFirstAddButton));
			  map.clear();
			  map.put("Aloe", 1);
			  map.put("Almond", 1);
			  outMap.clear();
			  outMap=shoppingPage.get().addCheapestProductsToCart("Moisturizers", map);
			  System.out.println("outMap: "+outMap);
			  shoppingPage.get().clickOnCartButton();
			  checkoutPage.get().verifyPageHeader("Checkout");
			  checkoutPage.get().verifyCartGridHeaders(Arrays.asList("Item","Price"));
			  checkoutPage.get().verifyCartGridValuesAndTotal(outMap);
			  checkoutPage.get().clickPayWithCardButton();
			  paymentWithCardPage.get().fillPaymentDetailsAndSubmit(0);
			  paymentConfirmationPage.get().verifyPageHeader("PAYMENT SUCCESS");
			  paymentConfirmationPage.get().verifyDisplayeMessage("Your payment was successful. You should receive a follow-up call from our sales team.");
			  
		  }
		  
		  else if (currTempInt>34) 
		  { 
			  System.out.println("Temperature is greater than 34 degree hence, shop for Sunscreens");
			  landingPage.get().clickSunscreensButton(); 
			  shoppingPage.get().verifyPageHeader("Sunscreens");
			  waitUtils.get().waitForElementToLoad(ExpectedConditions.visibilityOfElementLocated(ShoppingPage.byFirstAddButton));
			  map.clear();
			  map.put("SPF-50", 1);
			  map.put("SPF-30", 1);
			  outMap.clear();
			  outMap=shoppingPage.get().addCheapestProductsToCart("Sunscreens", map);
			  System.out.println("outMap: "+outMap);
			  shoppingPage.get().clickOnCartButton();
			  checkoutPage.get().verifyPageHeader("Checkout");
			  checkoutPage.get().verifyCartGridHeaders(Arrays.asList("Item","Price"));
			  checkoutPage.get().verifyCartGridValuesAndTotal(outMap);
			  checkoutPage.get().clickPayWithCardButton();
			  paymentWithCardPage.get().fillPaymentDetailsAndSubmit(0);
			  paymentConfirmationPage.get().verifyPageHeader("PAYMENT SUCCESS");
			  paymentConfirmationPage.get().verifyDisplayeMessage("Your payment was successful. You should receive a follow-up call from our sales team.");
		  }
		 
	}
	
	@AfterClass
	public void teardown()
	{
		driver.get().quit();
	}

}


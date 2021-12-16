package uitests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import resources.BaseTest;

public class TestHeadersOfAllPages extends BaseTest {

	@BeforeClass
	public void setup()
	{
		initializeDriver();
		initializePageObjects();
	}

	@BeforeMethod
	public void beforeMethod()
	{
		openTestURL();
	}

	@Test
	public void verifyPageHeaders()
	{

		landingPage.get().clickMoisturizersButton();
		shoppingPage.get().verifyPageHeader("Moisturizers");
		shoppingPage.get().clickOnCartButton();
		checkoutPage.get().verifyPageHeader("Checkout");
	}

	@Test
	public void verifyPaymentsHeader()
	{
		landingPage.get().clickSunscreensButton();
		shoppingPage.get().verifyPageHeader("Sunscreens");
		Map<String, Integer> map=new HashMap<String,Integer>();
		Map<String,Long> outMap=new HashMap<String,Long>();
		map.put("SPF-50", 1);
		map.put("SPF-30", 1);
		outMap.clear();
		outMap=shoppingPage.get().addCheapestProductsToCart("Sunscreens", map);
		System.out.println("outMap: "+outMap);
		shoppingPage.get().clickOnCartButton();
		checkoutPage.get().verifyPageHeader("Checkout");
		checkoutPage.get().clickPayWithCardButton();
		paymentWithCardPage.get().fillPaymentDetailsAndSubmit(1);
		paymentConfirmationPage.get().verifyPageHeader("PAYMENT SUCCESS");
	}

	@AfterClass
	public void teardown()
	{
		driver.get().quit();
	}

}


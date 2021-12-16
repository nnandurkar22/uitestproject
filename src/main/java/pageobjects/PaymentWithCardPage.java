package pageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import resources.BaseTest;


public class PaymentWithCardPage extends BaseTest {
	
	public WebDriver driver;
	
	public PaymentWithCardPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static final By byPageWaitEle=By.xpath("//h2[text()='Example charge']");
	public static final By byEmail=By.id("email");
	public static final By byCardNumber=By.id("card_number");
	public static final By byCCExp=By.id("cc-exp");
	public static final By byCvc=By.id("cc-csc");
	public static final By byZipCode=By.id("billing-zip");
	public static final By byIFrame=By.name("stripe_checkout_app");
	public static final By bySubmitButton=By.id("submitButton");

	
	public void fillPaymentDetailsAndSubmit(int testDataRow)
	{
		try
		{
		driver.switchTo().frame(driver.findElement(byIFrame));
		waitUtils.get().waitForElementToLoad(ExpectedConditions.visibilityOfElementLocated(PaymentWithCardPage.byPageWaitEle));
		Actions action=new Actions(driver);
		
		Map<String,String> dataMap=dataUtils.get().getDataFromCSV(testDataRow, "PaymentData.csv");
		
		driver.findElement(byEmail).sendKeys(dataMap.get("email_id"));
		String card_number=dataMap.get("card_number").replaceAll(" ", "");
		action.moveToElement(driver.findElement(byCardNumber)).click().sendKeys(card_number.subSequence(0, 4)+" ").sendKeys(card_number.subSequence(4, 8)+" ").sendKeys(card_number.subSequence(8,12)+" ").sendKeys(card_number.subSequence(12,16)+" ").build().perform();
		waitUtils.get().waitForElementToLoad(ExpectedConditions.visibilityOfElementLocated(PaymentWithCardPage.byCCExp));
		driver.findElement(byCCExp).sendKeys(dataMap.get("expiry_month"));
		driver.findElement(byCCExp).sendKeys(dataMap.get("expiry_year"));
		driver.findElement(byCvc).sendKeys(dataMap.get("card_cvc"));
		waitUtils.get().waitForElementToLoad(ExpectedConditions.visibilityOfElementLocated(PaymentWithCardPage.byZipCode));
		driver.findElement(byZipCode).sendKeys(dataMap.get("zip_code"));
		waitUtils.get().waitForElementToLoad(ExpectedConditions.elementToBeClickable(PaymentWithCardPage.bySubmitButton));
		driver.findElement(bySubmitButton).click();
		waitUtils.get().waitForElementToLoad(ExpectedConditions.urlContains("confirmation"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try
			{
			Assert.fail("Exception occurred while making payment with card");
			}
			catch(Exception e1)
			{
				
			}
		}
		
		}
	
	
	
	
}

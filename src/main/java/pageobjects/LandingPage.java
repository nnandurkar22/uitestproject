package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import resources.BaseTest;

public class LandingPage extends BaseTest {
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static final By currTempVal=By.id("temperature");
	public static final By moistButton=By.xpath("//button[contains(text(),'Buy moisturizers')]");
	public static final By sunscrButton=By.xpath("//button[contains(text(),'Buy sunscreens')]");
	
	
	public String getCurrentTemperature()
	{
		String val=driver.findElement(currTempVal).getText().split(" ")[0];
		return val;
	}
	
	public void clickMoisturizersButton()
	{
		driver.findElement(moistButton).click();
	}
	
	public void clickSunscreensButton()
	{
		driver.findElement(sunscrButton).click();
	}
	
	

}

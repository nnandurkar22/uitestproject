package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;

public class WaitUtils extends BaseTest {
	WebDriver driver;
	
	public WaitUtils(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void waitForElementToLoad(ExpectedCondition<?> condition)
	{
		Properties prop=super.loadProps();
		Long impWait=Long.parseLong(prop.getProperty("implicitWaitInSeconds"));
		Long expWait=Long.parseLong(prop.getProperty("exlicitWaitInSeconds"));
		
		setImplicitWait(0L);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(expWait));
		wait.until(condition);
		setImplicitWait(impWait);
	}
	
	public void setImplicitWait(Long impWait)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(impWait));
	}

}

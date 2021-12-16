package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageobjects.CheckoutPage;
import pageobjects.LandingPage;
import pageobjects.PaymentConfirmationPage;
import pageobjects.PaymentWithCardPage;
import pageobjects.ShoppingPage;

public class BaseTest {
	
	private static final String CHROME_BROWSER="chrome";
	private static final String EDGE_BROWSER="edge";
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	static ExtentReports extent;
	String reportPath = new File("").getAbsolutePath().toString().trim()+"/Reports/";
	
	
	//all page objects thread local declarations
	public static ThreadLocal<LandingPage> landingPage=new ThreadLocal<LandingPage>();
	public static ThreadLocal<ShoppingPage> shoppingPage=new ThreadLocal<ShoppingPage>();
	public static ThreadLocal<WaitUtils> waitUtils=new ThreadLocal<WaitUtils>();
	public static ThreadLocal<CheckoutPage> checkoutPage=new ThreadLocal<CheckoutPage>();
	public static ThreadLocal<PaymentWithCardPage> paymentWithCardPage=new ThreadLocal<PaymentWithCardPage>();
	public static ThreadLocal<PaymentConfirmationPage> paymentConfirmationPage=new ThreadLocal<PaymentConfirmationPage>();
	public static ThreadLocal<DataUtils> dataUtils=new ThreadLocal<DataUtils>();
	
	/**
	 * Method to load config.properties file
	 * @return
	 */
	public Properties loadProps()
	{
		Properties prop=new Properties();
		try
		{
		
		String localDir = System.getProperty("user.dir");
		FileInputStream fis=new FileInputStream(localDir + "\\src\\main\\java\\resources\\config.properties");
		
		prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * Method for initializing webdriver with the help of parameters specified in the config.properties file
	 * @return
	 */
	public WebDriver initializeDriver()
	{
	try
	{
	
	Properties prop=this.loadProps();
	String browserProp=prop.getProperty("browser");
	Long impWait=Long.parseLong(prop.getProperty("implicitWaitInSeconds"));
	
	try {
	if(browserProp.equalsIgnoreCase(CHROME_BROWSER))
	{
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
	}
	
	
	else if(browserProp.equalsIgnoreCase(EDGE_BROWSER))
	{
		WebDriverManager.edgedriver().setup();
		driver.set(new EdgeDriver());
	}
	
	else
	{
		System.out.println("No browser specified, hence defaulting to Chrome.");
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
	}
	}
	catch(NullPointerException e1)
	{
		System.out.println("No browser specified, hence defaulting to Chrome.");
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
	}
	
	driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(impWait));
	
	}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return driver.get();
	}
	
	
	public void openTestURL()
	{
		driver.get().get(getTestURL());
	}
	
	public String getTestURL()
	{
		Properties props=this.loadProps();
		String url=props.getProperty("testURL");
		return url;
	}
	
	public void closeBrowser()
	{
		driver.get().close();
	}
	
	public void killBrowserSession()
	{
		driver.get().quit();
	}
	
	
	/**
	 * Method for initializing all thread safe page objects so that 
	 * they can be directly accessed from all the test and
	 * page object classes without redundant initializations.
	 */
	public void initializePageObjects()
	{
		landingPage.set(new LandingPage(driver.get()));
		shoppingPage.set(new ShoppingPage(driver.get()));
		waitUtils.set(new WaitUtils(driver.get()));
		checkoutPage.set(new CheckoutPage(driver.get()));
		paymentWithCardPage.set(new PaymentWithCardPage(driver.get()));
		paymentConfirmationPage.set(new PaymentConfirmationPage(driver.get())); ;
		dataUtils.set(new DataUtils(driver.get()));
	}
	

	/**
	 * Method for returning extent report object. This is used in the listeners for logging test results.
	 * @return - ExtentReports
	 */
	public ExtentReports getReportObject()
	{
		
		String path =System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		 extent =new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}
	
	/**
	 * Method for getting screenshot using selenium's TakesScreenshot interface. Returns path of the captured screenshot.
	 * @param testCaseName - Name of test case under execution
	 * @param driver - Live WebDriver instance
	 * @return - String
	 */
	public String getScreenShotPath(String testCaseName,WebDriver driver)
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		try {
			FileUtils.copyFile(source,new File(destinationFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationFile;
	}
}

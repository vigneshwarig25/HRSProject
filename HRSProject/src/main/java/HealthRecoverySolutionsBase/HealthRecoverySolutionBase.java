package HealthRecoverySolutionsBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CapabilityType;




public class HealthRecoverySolutionBase {

	public static WebDriver driver;
	public static Properties prop;




	public HealthRecoverySolutionBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\HealthRecoverySolutionsConfig\\config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialization() throws InterruptedException {
		String browserName = prop.getProperty("browser");

		if(browserName.equals("chrome")){
			//FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\config\\config.properties");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Util\\chromedriver.exe");	
			ChromeOptions option = new ChromeOptions();
			option.addArguments("incognito");
			DesiredCapabilities capabilities = new DesiredCapabilities();	 
			capabilities.setCapability(ChromeOptions.CAPABILITY, option);
			option.merge(capabilities);				
			driver= new ChromeDriver(option);			
		}
		else  if (browserName.equals("IE")){
		//	System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\Util\\IEDriverServer.exe");
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"\\Util\\msedgedriver.exe");
		
			//	InternetExplorerOptions option = new InternetExplorerOptions();
			//	DesiredCapabilities capabilities = new DesiredCapabilities();	
			//	 capabilities.setCapability("ignoreZoomSetting",true);
			//    option.merge(capabilities);	
		//		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,false);
			//driver= new InternetExplorerDriver();
			 driver = new EdgeDriver();
		}



		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String url = prop.getProperty("url");
		System.out.println(url);
		driver.get(url); 
		   
		    Thread.sleep(5000);

	}

}

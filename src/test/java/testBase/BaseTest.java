package testBase;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utilities.ConfigReader;


public class BaseTest {

	public static WebDriver driver;
	public Logger log;

	@BeforeClass(alwaysRun=true,groups={"Sanity","Regression","Master","DataDriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String browser) throws MalformedURLException {

		//initiate logger
		log = LogManager.getLogger(this.getClass());

		if(ConfigReader.getProperty("execution_env").equalsIgnoreCase("remote")) {
			log.info("in remote if condition");
			DesiredCapabilities cap = new DesiredCapabilities();

			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);			
			}else{
				throw new IllegalArgumentException("unknown operating system:: "+os);
			}


			if(browser.equalsIgnoreCase("chrome")) {
				cap.setBrowserName("chrome");
			}else if(browser.equalsIgnoreCase("edge")) {
				cap.setBrowserName("MicrosoftEdge");
			}else if(browser.equalsIgnoreCase("firefox")) {
				cap.setBrowserName("firefox");
			}else{
				throw new IllegalArgumentException("unknown browser:: "+browser);
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444"),cap);//Selenium 4
			
		} else if(ConfigReader.getProperty("execution_env").equalsIgnoreCase("local")) {
			log.info("in local if condition");
			if(browser.equalsIgnoreCase("chrome")){	
				driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("edge")){	
				driver = new EdgeDriver();
			}else if(browser.equalsIgnoreCase("firefox")){	
				driver = new FirefoxDriver();
			}else {
				throw new IllegalArgumentException("unknown browser:: "+browser);
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(ConfigReader.getProperty("appUrl1"));
	}

	@AfterClass(alwaysRun=true,groups={"Sanity","Regression","Master","DataDriven"}) 
	public void tearDown() { 
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	public String randomAlphabets() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;

	}

	@SuppressWarnings("deprecation")
	public String randomNumerals() {
		String randomString = RandomStringUtils.randomNumeric(10);
		return randomString;

	}

	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric() {
		String randomString = RandomStringUtils.randomAlphanumeric(10);
		return randomString;

	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

	}
}

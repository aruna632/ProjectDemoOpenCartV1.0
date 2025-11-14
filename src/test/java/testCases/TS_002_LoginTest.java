package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.ConfigReader;

public class TS_002_LoginTest extends BaseTest {

	@Test(groups = {"Regression","Master"})
	public void verify_Login() {

		try {
			log.info("***Started Login test***");
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();
			log.info("***Clicked on Login option***");

			LoginPage loginPage = new LoginPage(driver);

			if(ConfigReader.getProperty("email").length()==0) {
				loginPage.setPassword(ConfigReader.getProperty("password").trim());
				log.info("***Entered Password***");
				loginPage.clkLogin();
				log.info("***clicked on Login button***");
			}else if(ConfigReader.getProperty("password").length()==0) {
				loginPage.setEmailId(ConfigReader.getProperty("email").trim());
				log.info("***Entered Email***");
				loginPage.clkLogin();
				log.info("***clicked on Login button***");
			}else if(ConfigReader.getProperty("email").length()==0 && ConfigReader.getProperty("password").length()==0) {
				loginPage.clkLogin();
				log.info("***clicked on Login button***");
			}else {
				loginPage.setEmailId(ConfigReader.getProperty("email").trim());
				log.info("***Entered Email***");
				loginPage.setPassword(ConfigReader.getProperty("password").trim());
				log.info("***Entered Password***");
				loginPage.clkLogin();
				log.info("***clicked on Login button***");
			}

			MyAccountPage myAccPage = new MyAccountPage(driver);

			if(loginPage.getMessage() != null && loginPage.getMessage().startsWith("Warning")) {
				log.info("***No match for E-Mail Address and/or Password.***");
				Assert.fail();
			}else {
				log.info("***Logged in successfully***");
				Assert.assertTrue(myAccPage.isAccountDisplayed());
			}
			myAccPage.clkLogout();
		}catch(Exception e) {
			log.info("Exception in try block and test failed");
			Assert.fail();
		}
	}
}

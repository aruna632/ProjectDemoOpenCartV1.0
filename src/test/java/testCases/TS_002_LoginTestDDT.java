package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TS_002_LoginTestDDT extends BaseTest{

	@Test(dataProvider="LoginData",dataProviderClass = DataProviders.class,groups= {"DataDriven","Master"})
	public void verify_Login(String email,String pwd,String data) {

		try {
			log.info("***Started Login test***");
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			homePage.clickLogin();
			log.info("***Clicked on Login option***");

			LoginPage loginPage = new LoginPage(driver);
			loginPage.setEmailId(email);
			log.info("***Entered Email***"+email);
			loginPage.setPassword(pwd);
			log.info("***Entered Password***"+pwd);
			loginPage.clkLogin();
			log.info("***clicked on Login button***");

			MyAccountPage myAccPage = new MyAccountPage(driver);
			boolean result = myAccPage.isAccountDisplayed();

			log.info("**Input Data***"+data);

			if(data.equalsIgnoreCase("Valid")) {		
				if(result) {
					log.info("***Test Passed***");
					myAccPage.clkLogout();
					Assert.assertTrue(result);
				}else {
					log.info("***Test Failed***");
					Assert.fail();
				}
			}

			if(data.equalsIgnoreCase("Invalid")) {
				if(result) {
					log.info("***Test Failed***");
					myAccPage.clkLogout();
					Assert.fail();
				}else {
					log.info("***Test Passed***");
					Assert.assertFalse(result);
				}
			}

		}catch(Exception e) {
			log.info("Exception in try block and test failed");
			Assert.fail();
		}
	}


}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TS_001_AccountRegistrationTest extends BaseTest {

	@Test(groups= {"Sanity","Master"})
	public void Verify_Account_Registration() {

		try{
			log.info("***Account Registration Test started***");

			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			log.info("***Clicked on MyAccount link***");
			homePage.clickRegister();
			log.info("***Clicked on Registration link***");

			AccountRegistrationPage accRegPage = new AccountRegistrationPage(driver);

			accRegPage.setFirstname(randomAlphabets());

			accRegPage.setLastName(randomAlphabets());

			log.info("Email for registration :: "+randomAlphaNumeric() + "@gmail.com");
			accRegPage.setEmail(randomAlphaNumeric() + "@gmail.com");

			accRegPage.setTelephone(randomNumerals());

			String pwd = randomAlphaNumeric() + "@#$!";
			log.info("Password for registration :: "+pwd);
			accRegPage.setPassword(pwd);
			accRegPage.setPassswordConfirm(pwd);
			log.info("***Entered Customer Details***");

			accRegPage.clkCheckPolicy();
			log.info("***Clicked on PolicyStatement***");
			accRegPage.clkcontinue();
			log.info("***Clicked on continue***");

			log.info("***Validating the message***");
			if(accRegPage.getMessage().equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
				log.info("***Message is as expected***");
			}else {
				log.info("***Message is not as expected and the test failed***");
				Assert.fail();
			}

		}catch(Exception e) {
			e.printStackTrace();
			log.error("***Account Registration Test Failed due to exception***");
			Assert.fail();
		}

		log.info("***Finished Account Registration Test***");
	}
}

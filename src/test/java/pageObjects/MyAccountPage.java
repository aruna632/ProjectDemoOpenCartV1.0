package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	//Constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement loginMessage;

	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;

	public boolean isAccountDisplayed() {
		try {
			return loginMessage.isDisplayed();	
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void clkLogout() {
		lnkLogout.click();
	}

}

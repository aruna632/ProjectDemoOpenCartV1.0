package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{

	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_emailId;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_password;

	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_Login;

	@FindBy(xpath="//div[contains(text(),'Warning')]")
	List<WebElement> errorMessage;


	public void setEmailId(String emailId) {
		txt_emailId.sendKeys(emailId);
	}

	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);	
	}

	public void clkLogin() {
		btn_Login.click();
	}

	public String getMessage() {
		try{
			if(errorMessage.size()>0) {
				String message = errorMessage.get(0).getText();
				return message;
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}


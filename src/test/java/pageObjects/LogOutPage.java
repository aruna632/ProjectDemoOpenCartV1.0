package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOutPage extends BasePage {

	//constructor
	public LogOutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//p[contains(text(),'logged off')]")
	WebElement logoutMessage;

	public String getMessage() {
		try {
			return logoutMessage.getText();
		}catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}

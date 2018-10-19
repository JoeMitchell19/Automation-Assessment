package assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	@FindBy(xpath ="//*[@id=\"j_username\"]")
	private WebElement adminLogin;
	@FindBy(xpath ="/html/body/div/div/form/div[2]/input")
	private WebElement adminPassword;
	@FindBy (xpath = "/html/body/div/div/form/div[3]/input")
	private WebElement loginbutton;
	
	public void login(String a, String b) {
		adminLogin.sendKeys(a);
		adminPassword.sendKeys(b);
	}
	public WebElement submit() {
		return loginbutton;
	}
}

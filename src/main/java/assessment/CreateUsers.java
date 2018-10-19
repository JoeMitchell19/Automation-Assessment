package assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateUsers {
	@FindBy(id = "username")
	private WebElement userName;
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[2]/td[2]/input")
	private WebElement password;
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[3]/td[2]/input")
	private WebElement passwordconfirm;
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[4]/td[2]/input")
	private WebElement fullName;
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[5]/td[2]/input")
	private WebElement email;
	@FindBy(xpath = "//*[@id=\"yui-gen1-button\"]")
	private WebElement button;

	
	public void getuserName(String a) {
		userName.sendKeys(a);
	}
	public void getpassword(String b) {
		password.sendKeys(b);;
	}
	public void getpasswordconfirm(String c) {
		passwordconfirm.sendKeys(c);
	}
	public void getfullName(String d) {
		fullName.sendKeys(d);;
	}
	public void getemail(String e) {
		email.sendKeys(e);
	}
	public WebElement button() {
		return button;
	}
}

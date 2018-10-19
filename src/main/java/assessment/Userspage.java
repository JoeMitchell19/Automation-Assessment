package assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Userspage {
	@FindBy (xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
	private WebElement button;
	
	public WebElement getbutton() {
		return button;
	}

}

package assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditUserPage {
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/table/tbody/tr[1]/td[3]/input")
	private WebElement fullnamebox;
public void changename(String x) {
	fullnamebox.sendKeys(x);
}
}

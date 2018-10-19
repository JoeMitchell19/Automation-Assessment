package assessment;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

// This is the list of imports required from the dependencies of selenium, junit etc.

public class AutomatedTestingStepClass {
	WebDriver driver = null;
	int reportcounter = 1;
	public static ExtentReports report;
	public static ExtentTest test;
	
	// setting up reports and driver - counter will iterate through the scenarios.
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports("C:\\Users\\Admin\\Desktop\\Assessment reporting\\Jenkins tests.html");
		if(reportcounter > 5) {
			report.flush();
		}
	}
	@After
	public void teardown() {
		driver.quit();
	}
	@Given("^that you are on the create UserScreen$")
	public void that_you_are_on_the_create_UserScreen() throws Throwable {
		driver.get(Constants.URL2);
		if(reportcounter ==1) {
			test = report.startTest("Creating a new User");
			test.log(LogStatus.INFO, "Loading Create User page");
		}
		else if(reportcounter ==2) {
			test = report.startTest("Creating new parameterised Users");
			test.log(LogStatus.INFO, "Loading Create User page");
		}
		String website = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/securityRealm/addUser",website);
		if(website == "http://localhost:8080/securityRealm/addUser") {
			test.log(LogStatus.PASS, "Create User page found");
		}
		else {
			test.log(LogStatus.PASS, "Create User page not found");
		}
	}
	// The previous code segment tests whether the correct web page is open

	@When("^the User details are entered on the Create UserScreen$")
	public void the_User_details_are_entered_on_the_Create_UserScreen() throws Throwable {
		test.log(LogStatus.INFO, "Inserting account info");
		CreateUsers c = PageFactory.initElements(driver, CreateUsers.class);
		c.getuserName("JM1");
		c.getpassword("Turtle72");
		c.getpasswordconfirm("Turtle72");
		c.getfullName("Joe Mitchell");
		c.getemail("mitchell.jo@sky.com");
	}
// The code above is setting up a new, random user. This will be the shell code for the parameterised test later on
	@When("^the details are submitted on the Create UserScreen$")
	public void the_details_are_submitted_on_the_Create_UserScreen() throws Throwable {
		// The following code segment allows the user to submit information 
		test.log(LogStatus.INFO, "Submitting account info");
		CreateUsers c = PageFactory.initElements(driver, CreateUsers.class);
		Actions a = new Actions(driver);
		a.moveToElement(c.button()).click().perform();
		String URLtest = driver.getCurrentUrl();

		// Once submitted, the system logs out, so this code segment logs you back in and will return you the users page.
		if(URLtest.equals("http://localhost:8080/login?from=%2F")) {
			LoginPage l = PageFactory.initElements(driver, LoginPage.class);
			l.login("admin", "f18e796e2f6a4a60a48e071ebaada27c");
			Actions a1 = new Actions(driver);
			a1.moveToElement(l.submit()).click().perform();
			
			//The previous action takes you to the home page, so the next job is to get to the manage users page
			WebElement managebutton = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[4]/a[2]"));
			Actions a2 = new Actions(driver);
			a2.moveToElement(managebutton).click().perform();
			
			// Now we're at the manage users page, we make one more move to return to the users page.
			WebElement userbutton = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[16]/a/dl/dt"));
			Actions a3 = new Actions(driver);
			a3.moveToElement(userbutton).click().perform();
			
		}
		assertEquals(Constants.URL2,URLtest);
		if(!URLtest.equals(Constants.URL2)) {
			driver.get(Constants.URL2);
		}
		// If the navigation via the menu has gone wrong, the previous if statement will take the system to the users page.
	}
  
	@Then("^the Username should be visible on the UsersScreen$")
	public void the_Username_should_be_visible_on_the_UsersScreen() throws Throwable {
		//Verifying that the correct information has been entered
		String username = "JM1";
		driver.findElement(By.linkText(username));
		WebElement UserID = driver.findElement(By.linkText(username));
		String verify = UserID.getText();
		assertEquals(username,verify);
		if(verify.equals("JM1") ) {
			test.log(LogStatus.PASS, "User name is Visible");
		}
		else {
			test.log(LogStatus.FAIL, "User name is not Visible");
		}
		report.endTest(test);
		reportcounter++;
	}

	@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, and \"([^\"]*)\" Fullname are entered on the Create UserScreen$")
	public void the_User_details_username_password_confirm_Password_and_Fullname_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		// Inserting the required information from the parameterized features.
		
		CreateUsers c = PageFactory.initElements(driver, CreateUsers.class);
		c.getuserName(arg1);
		c.getpassword(arg2);
		c.getpasswordconfirm(arg3);
		c.getfullName(arg4);
		c.getemail("test@qa.com");                            // This email is a generic email as no email was provided in the feature file, but an email field exists in my Jenkins page
		Actions a = new Actions(driver);
		a.moveToElement(c.button()).click().perform();
	}

	@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
	public void the_username_should_be_visible_on_the_UsersScreen(String arg1) throws Throwable {
		// Checking that the desired user name has been located.
		
		driver.findElement(By.linkText (arg1));
		WebElement UserID = driver.findElement(By.linkText (arg1));
		String verify = UserID.getText();
		assertEquals(arg1,verify);
		if(verify == arg1) {
			test.log(LogStatus.PASS, "User name " +arg1+ " is Visible");
		}
		else {
			test.log(LogStatus.FAIL, "User name " +arg1+ " is not Visible");
		}
		report.endTest(test);
		reportcounter++;
	}

	@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
	public void the_username_is_visible_on_the_UsersScreen(String arg1) throws Throwable {
		 test = report.startTest("Navigating to User profile page"); 
		 driver.get(Constants.URL1);
		 //opens new test to verify that the new accounts have been created.
		 test = report.startTest("Verifying that the desired user name is present");
		 
		 driver.findElement(By.linkText (arg1));
			WebElement UserID = driver.findElement(By.linkText (arg1));
			String verify = UserID.getText();
			assertEquals(arg1,verify);
			if(verify == arg1) {
				test.log(LogStatus.PASS, "User name " +arg1+ " is Visible");
			}
			else {
				test.log(LogStatus.FAIL, "User name " +arg1+ " is not Visible");
			}
	}

	@When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
	public void the_username_is_clicked_on_the_UserScreen(String arg1) throws Throwable {
	    // attempt to find element with desired userID on user screen
		test = report.startTest("Verifying that the desired user name is present");
		driver.findElement(By.linkText (arg1));
		WebElement UserID = driver.findElement(By.linkText (arg1));
		
		// clicking on the desired userID
		Actions a = new Actions(driver);
		a.moveToElement(UserID).click().perform();
		
	}

	@Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
	public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) throws Throwable {
		//attempt to verify whether the correct user profile has been found by comparing the id found to the known id.
		WebElement id = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]"));
		String idtext = id.getText();
		if(idtext.equals(arg1)) {
			test.log(LogStatus.PASS, "The user profile of " +arg1+ " has been found!");
		}
		else {
			test.log(LogStatus.FAIL, "The user profile of " +arg1+ " hasn't been found.");
		}
		assertEquals(arg1,idtext);
		report.endTest(test);
		reportcounter++;
	}

	@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
	public void the_Username_s_profile_page_has_been_loaded(String arg1) throws Throwable {
		//attempt to verify whether the correct user profile has been found by comparing the id found to the known id.
		WebElement id = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/div[2]"));
		String idtext = id.getText();
		if(idtext.equals(arg1)) {
			test.log(LogStatus.PASS, "The user profile of " +arg1+ " has been found!");
		}
		else {
			test.log(LogStatus.FAIL, "The user profile of " +arg1+ " hasn't been found.");
			System.out.println("Taking you to the user's site now");
			driver.get(Constants.URL3 +arg1);
		}
		assertEquals(arg1,idtext);
		// The else statement will lead the user to the desired page.
	}

	@Given("^the configure button has been clicked on the profile page$")
	public void the_configure_button_has_been_clicked_on_the_profile_page() throws Throwable {
		// This code will navigate the user to the configure page
	    WebElement configurebutton = driver.findElement(By.xpath("//*[@id=\"tasks\"]/div[4]/a[2]"));
	    Actions a = new Actions(driver);
	    a.moveToElement(configurebutton).click().perform();
	}

	@When("^I change the old FullName on the Configure Page to a new FullName \"([^\"]*)\"$")
	public void i_change_the_old_FullName_on_the_Configure_Page_to_a_new_FullName(String arg1) throws Throwable {
		EditUserPage e = PageFactory.initElements(driver, EditUserPage.class);
		e.changename(arg1);
	}

	@When("^I save the changes to the Configure Page$")
	public void i_save_the_changes_to_the_Configure_Page() throws Throwable {
		WebElement savebutton = driver.findElement(By.xpath("//*[@id=\"yui-gen2-button\"]"));
		Actions a = new Actions(driver);
		a.moveToElement(savebutton).click().perform();
	}

	@Then("^the Configure Page should show the NewFullName \"([^\"]*)\"$")
	public void the_Configure_Page_should_show_the_NewFullName(String arg1) throws Throwable {
		// I would check that the current url is correct
		// The I would check that the new full name has been inputted successfully.
	}

}

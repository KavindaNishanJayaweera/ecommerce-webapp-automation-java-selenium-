package stepDefinitions;

import com.base.Common;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginSteps {

	@Given("Open the Driver")
	public void open_Driver() {
		LoginPage.open_Driver();
	}

	@When("User enter Base URL")
	public void base_URL() {
	   LoginPage.optionbrowser();
	}

	@Then("Users Verify the Home page title {string}")
	public void  Users_Verify_the_Home_page_title(String pageTitle) {
		//HomePage.VerifyTitle(pageTitle);
		Common.method_verifyPageTitle(pageTitle);
	}

}

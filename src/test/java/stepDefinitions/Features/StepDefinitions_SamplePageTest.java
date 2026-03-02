package stepDefinitions.Features;

import com.base.Common;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Features.Page_Add_Users;
import pages.HomePage;

public class StepDefinitions_SamplePageTest {

    @When("User Hover the Tester's hub dropdown")
    public void hoverDropdown(){
        HomePage.hover_Testershubdropdown();
    }

    @Then("Users Clicks the SamplePageButton to Navigate page")
    public void clickSampleTestPage(){
        HomePage.clickSamplePageTest();
    }

    @And("Verify page title is {string}")
    public void verifyPageTitle(String pageTitle){
        Common.method_hardPause(6);
        Common.method_verifyPageTitle(pageTitle);
    }

    @When("User enter following data on add Sample Test page")
    public void user_enter_following_data_on_sampleTests_page(io.cucumber.datatable.DataTable dataTable) {
      Page_Add_Users.method_EnterTableData(dataTable); // Enter data into the Add Users page.
        // Store the user details in ScenarioContext or similar shared storage
    }
}

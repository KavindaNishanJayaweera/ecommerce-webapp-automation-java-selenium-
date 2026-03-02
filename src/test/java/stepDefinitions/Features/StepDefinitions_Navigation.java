package stepDefinitions.Features;

import io.cucumber.java.en.When;
import pages.Features.Navigations;
import pages.LoginPage;

public class StepDefinitions_Navigation {

    @When("User clicks on Tabs menu in Home Page")
    public void clickTabsMenu() {
        Navigations.method_ClickonTabs();
    }

}

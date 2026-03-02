package testRunner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		
		"./src/test/resources/0000/Login.feature",
		"./src/test/resources/featrues/0000/SamplePageTest.feature",

},
// To generate the step definition class
		//dryRun=true,
		strict = true, 
		glue = { "stepDefinitions" }, 
		//tags = { "@Regression" },
		//tags = { "-@Div" }, 
		plugin = { "pretty",
				"json:target/cucumber-reports/Cucumber.json", "junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports" }, monochrome = true, stepNotifications = true)
public class TestRunner {

}
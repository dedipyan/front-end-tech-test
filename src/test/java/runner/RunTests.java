package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "json:target/reports/cucumber.json" },  
		glue = "common",
        features = "src/test/resources/Features/",
        monochrome = true,
        junit = "--step-notifications",
        tags="@add_things_tobet"
)
public class RunTests {
}



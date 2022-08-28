package common;

import java.io.IOException;
import java.util.Date;


import com.browserPackage.BaseClass;
import com.browserPackage.Log;
import com.utility.Utility;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;

public class TestHooks extends BaseClass {

	private BaseClass baseClass;
	private String screenshotLocation;

	public TestHooks(BaseClass base) throws IOException {
		super();
		this.baseClass = base;
	}

	int position = 0;
	boolean flag = false;
	String steps = "[";

	@Before
	public void beforeEachScenario(Scenario scenario) throws IOException {
		String scenarioName = getScenarioName(scenario.getSourceTagNames().toString().trim());
		baseClass.eventInfo.put("scenarioName", scenarioName);
		Log.LogInfo("Started executing -------> "+scenarioName);
		System.out.println("Scenario name is " + scenarioName);
		screenshotLocation = System.getProperty("user.dir")+"/screenshots/" + (scenarioName + "_" + Utility.getCurrentTime());

	}


	@AfterStep
	public void aftereachstep(Scenario scenario) throws IOException {
			String ssId = Utility.formatDateAndTime(new Date(), "yyMMddhhmmssMs");
			String scenarioName =  String.valueOf(baseClass.eventInfo.get("scenarioName")).replaceAll("[_-]", "") + "_" + ssId;
			String destPath = screenshotLocation + "/" + scenarioName + ".png";
			Utility.takeSnapShotOfPage(destPath,driver);

	}

	public String getScenarioName(String tagName) {
		String scenarioName = "";
		String tags = tagName.replaceAll("[\\[\\],]", "").trim();
		String [] tagSplit = tags.split("@");
		for(String tag: tagSplit) {
			tag = tag.trim();
			if (!tag.isEmpty()) {
				scenarioName = tag;
				break;
			}
		}
		return scenarioName;
	}


	@After
	public void closeBrowser() throws Exception{
		if (driver == null) {
			return;
		}
		driver.quit();
		driver=null;
	}
}

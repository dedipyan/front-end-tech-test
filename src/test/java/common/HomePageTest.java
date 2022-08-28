package common;

import com.browserPackage.BaseClass;
import com.browserPackage.Log;
import com.utility.Utility;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;

import java.io.IOException;

public class HomePageTest extends BaseClass {

    HomePage homePage;
    public HomePageTest(BaseClass base) throws IOException {
        super();
        initiliazeWebDriver();
        homePage = new HomePage(driver);

    }

    @Given("^User has navigated to website$")
    public void user_has_open_shop_Canada_drives_website() throws Exception {
        String homeUrl = Utility.getConfigProperty("loginUrl");
        homePage.navigateToPage(homeUrl);
        Log.LogInfo("Navigated to URL : "+homeUrl);
        homePage.clickAcceptAllCookies();

    }

    @When("User selects the {string}")
    public void userSelectsThe(String option) {

        homePage.selectSport(option);
        homePage.waitForEventsLoad();
    }
}

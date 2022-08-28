package common;

import com.browserPackage.BaseClass;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.EventsPage;

import java.io.IOException;

public class EventsPageTest extends BaseClass {

    public BaseClass baseClass;
    public EventsPage events;

    public EventsPageTest(BaseClass base) throws IOException {
        events = new EventsPage(driver);
        this.baseClass = base;
    }

    @Then("^User click on bet for any event$")
    public void userclickonbetforanyevent()
    {
        String selectedEventNumber = events.selectAnyEventAndAddBet();
        baseClass.eventInfo.put("eventNumber",selectedEventNumber);
        Assert.assertTrue("Didn't add any event",events.getLoginBetButtonVisible());
    }

    @Then("^User removes the bet by clicking on the same event bet$")
    public void userremovesbyclickonthesamebet()
    {
        threadSleep(10);
        String eventNumber  = (String) baseClass.eventInfo.get("eventNumber");

        events.removeBetByClickonSameEventBet(eventNumber);
        threadSleep(10);
        Assert.assertTrue("Event bet didn't removed",events.getAddbetToselectiontextVisible());
    }

    @Then("^User removes bet by removing the delete button$")
    public void userremovesbetbyremovingdeletebutton()
    {
        events.deleteEventBet();
        threadSleep(10);
        Assert.assertTrue("Event bet didn't removed",events.getAddbetToselectiontextVisible());
    }

    @When("^User change Odds format$")
    public void userChangeOddsFormat()
    {
        events.changeOddsFormat();
    }
}

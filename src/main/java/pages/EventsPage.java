package pages;

import com.browserPackage.BaseClass;
import com.browserPackage.Log;
import com.utility.Utility;
import org.jsoup.Connection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class EventsPage extends BaseClass {

    public EventsPage(WebDriver driver) throws IOException {
        EventsPage.driver = driver;
        PageFactory.initElements(HomePage.driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'groupHeader__titleText') and contains(text(),'Events')]")
    private List<WebElement> eventsLive;

    @FindBy(how = How.XPATH,using = "//ul[@class='market-container']//div[contains(@class,'in-play')]/li")
    private List<WebElement> eventsCount;

    @FindBy(how = How.XPATH, using = "//button[text()='Log in & Place bet']")
    private List<WebElement> loginAndBet;

    @FindBy(how = How.XPATH,using = "//p[text()='Add selections to place a bet.']")
    private List<WebElement> addselectiontoBettext;

    @FindBy(how = How.XPATH, using = "//a[@class='remove ']")
    private WebElement deleteBetButton;

    @FindBy(how = How.XPATH,using = "//a[@class='currentFormat']")
    private WebElement oddsFormatButton;

    private String eventToBet = "(//ul[@class='market-container']//div[contains(@class,'in-play')]/li[$regex$]/div[contains(@class,'selection-cta')]//div[contains(@class,'selectionBlock')]/a)[2]";

    private String oddsFormatOption = "(//ul[@class='dropdown']//a)[$regex$]";

    public String selectAnyEventAndAddBet()
    {
        String randomeventNumber = "";
        if(eventsLive.size()>0)
        {
            randomeventNumber = String.valueOf(Utility.random(1, eventsCount.size()));

            WebElement eventBet = createDynamicLocator("xpath",eventToBet,randomeventNumber);
            click(eventBet);

        } else
        {
            System.out.println("There are no live events going for this sport");
            Log.LogError("There are no live events going for this sport");
        }
        return randomeventNumber;
    }

    public boolean getLoginBetButtonVisible()
    {
        return isElementPresent(loginAndBet);
    }

    public void removeBetByClickonSameEventBet(String eventNumber)
    {
        WebElement eventBet = createDynamicLocator("xpath",eventToBet,eventNumber);
        waitTillElementVisible(eventBet);
        click(eventBet);

    }

    public boolean getAddbetToselectiontextVisible()
    {
        return isElementPresent(addselectiontoBettext);
    }

    public void deleteEventBet()
    {
        click(deleteBetButton);
    }

    public void changeOddsFormat()
    {
        click(oddsFormatButton);
        String option  = String.valueOf(Utility.random(1, 2));
        WebElement oddsFormatToSelect = createDynamicLocator("xpath",oddsFormatOption,option);
        click(oddsFormatToSelect);
        threadSleep(10);
    }
}

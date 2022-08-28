package pages;

import com.browserPackage.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class HomePage extends BaseClass  {

    public HomePage(WebDriver driver) throws IOException {
        HomePage.driver = driver;
        PageFactory.initElements(HomePage.driver, this);
    }

    public void navigateToPage(String urlPage) {
        driver.get(urlPage);
    }

    @FindBy(how = How.XPATH,using = "//button[contains(@id,'onetrust') and contains(text(),'Accept All')]")
    private List<WebElement> acceptAllCookies;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'groupHeader__titleText') and contains(text(),'Events')] | //div[contains(@class,'groupHeader__titleText')]/span[text()='ODDS']")
    private WebElement eventsToLoad;


    private String sportOption = "//ul[@id='sportsList__links']/li/a/span[contains(text(),'$regex$')]";

    public void clickAcceptAllCookies()
    {
        if(acceptAllCookies.size()>0)
        {
            click(acceptAllCookies.get(0),60);
        }

    }

    public void selectSport(String option)
    {
        WebElement sportToBeSelected = createDynamicLocator("xpath",sportOption,option);

        click(sportToBeSelected);
    }

    public void waitForEventsLoad()
    {
        waitTillElementVisible(eventsToLoad);
    }
}

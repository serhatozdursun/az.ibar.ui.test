package base;

import com.thoughtworks.gauge.AfterScenario;
import exceptions.UndefinedOsName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.Browser;


public class BaseTest {

    public static WebDriver driver;
    protected static Browser browser = new Browser();


    public static void setBrowser() throws UndefinedOsName {
        browser.createDriver();
    }

    @AfterScenario
    public static void tearDown() {
        BaseTest.getDriver().quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(RemoteWebDriver driver) {
        BaseTest.driver = driver;
    }


}

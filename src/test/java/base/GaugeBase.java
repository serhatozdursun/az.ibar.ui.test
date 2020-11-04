package base;

import exceptions.NoSuchSelector;
import exceptions.UndefinedEnum;
import org.openqa.selenium.WebDriver;

import static mapping.Mapper.foundActivity;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GaugeBase extends BasePage {
    public GaugeBase(WebDriver driver) {
        super(driver);
    }


    /**
     * Click Element Method
     */
    public void click(String elm) throws UndefinedEnum, NoSuchSelector {
        clickElement(foundActivity(elm));
    }

    /**
     * Fill Input Method
     */
    public void sendKeysToWebElement(String elm, String text) throws UndefinedEnum, NoSuchSelector {
        sendKeys(foundActivity(elm), text);
    }

    public void pressEnter(String elm) throws UndefinedEnum, NoSuchSelector {
        pressEnter(foundActivity(elm));
    }

    /**
     * Go Uri Method
     *
     * @param uri
     */
    public void goTo(String uri) {
        goToUri(uri);
    }


    public void assertElementIsDisplay(String elm) throws Exception {
        Thread.sleep(1000);
        assertTrue(isElementDisplayed(foundActivity(elm)), elm + "web element does not exists");
    }

    public void clickElementsWithIndex(String elm, int index) throws UndefinedEnum, NoSuchSelector {
        clickElement(foundActivity(elm), index);

    }

    public String getElementText(String elm) throws UndefinedEnum, NoSuchSelector {
        return getText(foundActivity(elm));
    }

    public boolean isElementPresent(String elm) throws UndefinedEnum, NoSuchSelector, InterruptedException {
        return isElementPresent(foundActivity(elm));
    }

    public void wait(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public void clickElementsWithJS(String by, int index) throws UndefinedEnum, NoSuchSelector {
        clickElementsWithJS(foundActivity(by), index);
    }

    public String getAttribute(String by, String attribute) throws UndefinedEnum, NoSuchSelector {
        return getAttribute(foundActivity(by), attribute);
    }

    public String getColor(String by, String colorCssKey) throws UndefinedEnum, NoSuchSelector {
        return getColor(foundActivity(by), colorCssKey);
    }

    public void scrollToElement(String by) throws UndefinedEnum, NoSuchSelector {
        scrollToElement(foundActivity(by));
    }
}

package tests;


import base.BaseTest;
import base.GaugeBase;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import exceptions.NoSuchSelector;
import exceptions.UndefinedEnum;
import exceptions.UndefinedOsName;
import org.junit.jupiter.api.Assertions;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StepImplementation extends BaseTest {
    GaugeBase base;

    @BeforeScenario
    public void before() throws UndefinedOsName {
        setBrowser();
        base = new GaugeBase(driver);
    }


    @Step("Go to <uri>")
    public void goTo(String uri) throws InterruptedException {
        base.goToUri(uri);
    }

    @Step("Type <keys> in the <inputElement>")
    public void sendKeys(String keys, String elm) throws UndefinedEnum, NoSuchSelector {
        base.sendKeysToWebElement(elm, keys);
        base.pressEnter(elm);
    }

    @Step("Verify <element> is display on the page")
    public void isElementDisplayed(String elm) throws Exception {
        base.assertElementIsDisplay(elm);
    }

    @Step("Click <elm>")
    public void clickElement(String elm) throws UndefinedEnum, NoSuchSelector {
        base.click(elm);
    }

    @Step("Click language <switch_button>")
    public void clickLanguageSwitchButton(String by) throws UndefinedEnum, NoSuchSelector {
        base.clickElementsWithIndex(by, 1);
    }

    @Step("Check the language of the page is <language>")
    public void checkPageLanguage(String language) throws NoSuchSelector, InterruptedException, UndefinedEnum {
        assertTrue(base.isElementPresent(language));
    }

    @Step("Checks if the <page> page is loaded")
    public void assertCurrentUrl(String page) {
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(page), page + " not loaded, currently available page; " + currentUrl);
    }

    @Step("Click the <online_apply> for credit button")
    public void clickApplyOnline(String elm) throws UndefinedEnum, NoSuchSelector {
        base.clickElementsWithJS(elm, 0);
    }

    @Step("Checking if a validation error message is displayed in the <elm> field")
    public void checkValidationErrorMsg(String elm) throws UndefinedEnum, NoSuchSelector {
        String classes = base.getAttribute(elm, "class");
        assertTrue(classes.contains("parsley-error"));
        String bottom_border_color_string = base.getColor(elm, "border-bottom-color");
        String left_border_color_string = base.getColor(elm, "border-left-color");
        String right_border_color_string = base.getColor(elm, "border-right-color");
        String top_border_color_string = base.getColor(elm, "border-top-color");

        var exceptedColor = new Color(255, 0, 0);
        var actual_bottom_border_color = base.hexToRgb(bottom_border_color_string);
        var actual_top_border_color = base.hexToRgb(top_border_color_string);
        var actual_left_border_color = base.hexToRgb(left_border_color_string);
        var actual_right_border_color = base.hexToRgb(right_border_color_string);

        assertEquals(exceptedColor, actual_bottom_border_color);
        assertEquals(exceptedColor, actual_top_border_color);
        assertEquals(exceptedColor, actual_right_border_color);
        assertEquals(exceptedColor, actual_left_border_color);
    }

}

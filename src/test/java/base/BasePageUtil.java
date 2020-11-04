package base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;


public class BasePageUtil {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePageUtil(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 15);
    }

    public WebElement waitForElement(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public List<WebElement> waitForElements(By element) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public WebElement waitForElementToBeClickAble(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickElement(By element) {
        waitForElementToBeClickAble(element).click();
    }

    public void sendKeys(By element, String keys) {
        WebElement elm = waitForElement(element);
        elm.clear();
        elm.sendKeys(keys);
    }

    public void pressEnter(By element) {
        waitForElement(element).sendKeys(Keys.ENTER);
    }

    public void goToUri(String uri) {
        driver.get(uri);
    }

    public boolean isElementPresent(By element) throws InterruptedException {
        Thread.sleep(1000);
        int size = driver.findElements(element).size();
        if (size > 0)
            return true;
        else
            return false;
    }

    protected boolean isElementDisplayed(By element) {
        if (driver.findElement(element).isDisplayed()) {
            return true;
        }
        return false;
    }


    protected void clickElement(By by, int index) {
        waitForElements(by).get(index).click();
    }

    public String getText(By by) {
        return waitForElement(by).getText();
    }

    public void clickElementsWithJS(By by, int index) {
        var js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", waitForElements(by).get(index));
    }

    public String getAttribute(By by, String attribute) {
        return waitForElement(by).getAttribute(attribute);
    }

    public static Color hexToRgb(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }

    public String getColor(By by, String cssValue) {
        WebElement element = waitForElement(by);
        String color = element.getCssValue(cssValue);
        int index = color.indexOf(')');
        color = color.substring(0, index + 1);
        if (color.contains("transparent")) {
            color = "rgba(0,0,0,1)";
        }
        String[] hexValue = color.replace("rgba(", "").replace(")", "").replace("rgb(", "").split(",");
        int hexValue1 = Integer.parseInt(hexValue[0]);
        hexValue[1] = hexValue[1].trim();
        int hexValue2 = Integer.parseInt(hexValue[1]);
        hexValue[2] = hexValue[2].trim();
        int hexValue3 = Integer.parseInt(hexValue[2]);
        String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
        return actualColor;
    }

    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    protected Object executeJS(String jsStmt, boolean wait) {
        return wait ? getJSExecutor().executeScript(jsStmt, "") : getJSExecutor().executeAsyncScript(jsStmt, "");
    }

    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(jsStmt, true);
    }

    protected void scrollToElement(By element) {
        WebElement elm = waitForElement(element);
        if (element != null) {
            scrollTo(elm.getLocation().getX(), elm.getLocation().getY());
        }
    }
}


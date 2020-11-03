package util;


import base.BaseTest;
import exceptions.UndefinedOsName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;


public class Browser {
    private DesiredCapabilities capabilities;


    public void createDriver() throws UndefinedOsName {
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36";

        var chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("intl.accept_languages", "en");
        chromePrefs.put("general.useragent.override", userAgent);
        ChromeOptions option = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        option.setExperimentalOption("prefs", chromePrefs);
        option.addArguments("test-type");
        option.addArguments("disable-popup-blocking");
        option.addArguments("ignore-certificate-errors");
        option.addArguments("disable-translate");
        option.addArguments("--start-maximized");
        option.addArguments("disable-automatic-password-saving");
        option.addArguments("allow-silent-push");
        option.addArguments("disable-infobars");
        capabilities.setCapability(ChromeOptions.CAPABILITY, option);
        capabilities.setBrowserName("chrome");
        selectPath();
        BaseTest.setDriver(new ChromeDriver(capabilities));

    }

    public WebDriver getDriver() {
        return BaseTest.getDriver();
    }

    protected void selectPath() throws UndefinedOsName {
        String osName = System.getProperty("os.name").toLowerCase();
        String driverPath;
        if (osName.contains("win")) {
            driverPath = getClass().getClassLoader().getResource("win/chromedriver.exe").getPath();
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else if (osName.contains("mac")) {
            driverPath = getClass().getClassLoader().getResource("mac/chromedriver").getPath();
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else if (osName.contains("linux")) {
            driverPath = getClass().getClassLoader().getResource("linux/chromedriver").getPath();
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else
            throw new UndefinedOsName("Undefined os; " + osName);
    }
}

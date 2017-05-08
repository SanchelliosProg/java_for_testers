package mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;

/**
 * Created by Александр on 19.03.2017.
 */
public class ApplicationManager {
    private PropertiesProvider properties;

    private String browser;

    private WebDriver driver;

    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new PropertiesProvider();
        initWebDriver();
        initHelpers();
    }

    private void initHelpers(){

    }

    private void initWebDriver(){
        if (Objects.equals(browser, BrowserType.CHROME)){
            driver = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        } else {
            driver = new InternetExplorerDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}

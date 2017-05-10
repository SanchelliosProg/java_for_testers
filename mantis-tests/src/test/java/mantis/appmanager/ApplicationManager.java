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
    private RegistrationHelper registrationHelper;

    public ApplicationManager(String browser)  {
        this.browser = browser;
        properties = new PropertiesProvider();
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }


    public WebDriver getDriver() {
        if (driver == null) {
            initWebDriver();
        }
        return driver;
    }

    public void stop(){
        if (driver != null){
            driver.quit();
        }
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null){
            registrationHelper =  new RegistrationHelper(this);
        }
        return registrationHelper;
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

}

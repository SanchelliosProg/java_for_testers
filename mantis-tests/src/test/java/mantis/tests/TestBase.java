package mantis.tests;

import mantis.appmanager.ApplicationManager;
import mantis.appmanager.PropertiesProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    protected ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    protected PropertiesProvider props = new PropertiesProvider();


    protected void login() {

    }

    protected void debugWait() {
        if (Boolean.getBoolean("verifyUI")){
            try {
                Thread.sleep(1250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

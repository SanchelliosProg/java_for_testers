package mantis.tests;

import mantis.appmanager.ApplicationManager;
import mantis.appmanager.PropertiesProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    protected ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    protected PropertiesProvider props = new PropertiesProvider();


    @BeforeSuite
    public void setUp() throws Exception {
        //TODO: app.init()
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php", "config_inc.php.bak");
        app.stop();
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

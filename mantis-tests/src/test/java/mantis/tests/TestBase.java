package mantis.tests;

import mantis.appmanager.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    private WebDriver driver = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)).getDriver();
    protected int beforeCount = 0;

    @BeforeSuite
    public void setUp(){
        login();
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }


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

    protected void reduceBeforeCountDueToDataObjCreation() {
        beforeCount -= 1;
    }

    protected void riseBeforeCountDueToDataObjCreation() {
        beforeCount += 1;
    }

    protected String cleanedPhone(String phone){
        return phone.replaceAll("[\\s()-]", "");
    }

}

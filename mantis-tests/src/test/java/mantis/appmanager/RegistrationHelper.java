package mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by Александр on 10.05.2017.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager applicationManager) {
        this.app = applicationManager;
        this.wd = app.getDriver();
    }

    public void start(String username, String email){
        wd.get(new PropertiesProvider().getProperty("web.baseUrl")+"/signup_page.php");
    }
}

package mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Александр on 13.05.2017.
 */
public class LoginPageHelper extends BaseHelper{
    public LoginPageHelper(ApplicationManager app) {
        super(app);
    }

    public AdminPageHelper login(String username, String password){
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
        return new AdminPageHelper(app);
    }
}

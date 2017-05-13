package mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by Александр on 13.05.2017.
 */
public class AdminPageHelper extends BaseHelper{
    public AdminPageHelper(ApplicationManager app) {
        super(app);
    }

    public void goToManageUsersPage(){
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public void chooseUserWithName(String name){
        wd.findElement(By.linkText(name)).click();
    }

    public void clickResetPasswordButton(){
        click(By.cssSelector("input.button[value='Reset Password']"));
    }

    public void resetPassword(String newPassword) {
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}

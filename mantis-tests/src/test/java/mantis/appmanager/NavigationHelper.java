package mantis.appmanager;

/**
 * Created by Александр on 13.05.2017.
 */
public class NavigationHelper extends BaseHelper{
    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public LoginPageHelper loginPage(){
        wd.get("http://localhost/mantisbt-1.2.19/login_page.php");
        return new LoginPageHelper(app);
    }

    public void page(String link){
        wd.get(link);
    }
}

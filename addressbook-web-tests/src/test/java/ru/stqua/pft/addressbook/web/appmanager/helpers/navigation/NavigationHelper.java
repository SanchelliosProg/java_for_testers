package ru.stqua.pft.addressbook.web.appmanager.helpers.navigation;

import org.openqa.selenium.WebDriver;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void openMainPage(){
        driver.get("http://localhost/addressbook/");
    }
}

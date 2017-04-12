package ru.stqua.pft.addressbook.web.appmanager.helpers.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void openApp(){
        driver.get("http://localhost/addressbook/");
    }

    public void groupPage(){
        if(isElementPresent(By.tagName("h1"))
                && find(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))){
            return;
        }
        click(By.linkText("groups"));
    }

    public void homePage(){
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void addNewAddressPage() {
        if(isElementPresent(By.tagName("h1"))
                && find(By.tagName("h1")).getText().equals("Edit / add address book entry")){
            return;
        }
        click(By.linkText("add new"));
    }
}

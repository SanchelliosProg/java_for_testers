package ru.stqua.pft.addressbook.web.appmanager.helpers.navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqua.pft.addressbook.web.appmanager.PropertiesProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactListHelper;
import ru.stqua.pft.addressbook.web.model.ContactData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class NavigationHelper extends BaseHelper {
    private PropertiesProvider p;

    public NavigationHelper(WebDriver driver) {
        super(driver);
        p = new PropertiesProvider();
    }

    public void openApp(){
        driver.get(p.getProperty("web.baseUrl"));
    }

    public void groupPage(){
        if(isElementPresent(By.tagName("h1"))
                && find(By.tagName("h1")).getText().equals("GroupLabels")
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

    public void detailedContactPage(ContactData data){
        ContactListHelper contactListHelper = new ContactListHelper(driver);
        homePage();
        contactListHelper.openDetailedInfoOfContactWithLastName(data.getLastName());
    }

    public void editPageOf(ContactData data){
        ContactListHelper contactListHelper = new ContactListHelper(driver);
        homePage();
        contactListHelper.openEditPageOfContactWithLastName(data.getLastName());
    }

    public void addNewAddressPage() {
        if(isElementPresent(By.tagName("h1"))
                && find(By.tagName("h1")).getText().equals("Edit / add contactHelper book entry")){
            return;
        }
        click(By.linkText("add new"));
    }
}

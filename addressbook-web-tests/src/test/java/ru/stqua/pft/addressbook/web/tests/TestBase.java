package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.AddressListHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.Login;
import ru.stqua.pft.addressbook.web.appmanager.ApplicationManager;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.AddressHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupHelper;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {

    protected ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    protected GroupHelper groupHelper = app.getGroupHelper();
    protected AddressHelper addressHelper = app.getAddressHelper();
    protected NavigationHelper navigationHelper = app.getNavigationHelper();
    protected AddressListHelper addressListHelper = app.getAddressListHelper();
    protected WebDriver driver = app.getDriver();


    protected void login() {
        Login login = new Login("admin", "secret");
        driver.get("http://localhost/addressbook/");
        driver.findElement(By.cssSelector("input[name=user]")).sendKeys(login.getUsername());
        driver.findElement(By.cssSelector("input[name=pass]")).sendKeys(login.getPassword());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    protected boolean createGroupIfNotExist(Groups group){
        boolean isCreated = false;
        navigationHelper.goToGroupPage();
        if(!groupHelper.isGroupWithNamePresented(group.getName())){
            groupHelper.createGroup(group);
            isCreated = true;
        }
        return isCreated;
    }

    protected void createAddressIfNotExist(AddressData address){
        navigationHelper.goToHomePage();
        if(!addressHelper.isAddressWithNamePresented(address.getFirstName())){
            addressHelper.addAddress(address);
        }
    }

    protected void cleanUp(){
        addressListHelper.cleanup();
        groupHelper.cleanup();
        driver.quit();
    }

    private void checkDriverInit(){
        if (driver == null){
            driver = app.getDriver();
        }
    }

    protected void debugWait() {
        try {
            Thread.sleep(1250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

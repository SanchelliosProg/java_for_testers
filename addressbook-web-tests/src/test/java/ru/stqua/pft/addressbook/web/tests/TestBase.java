package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
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
    protected static ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    protected static GroupHelper group = app.getGroupHelper();
    protected static AddressHelper address = app.getAddressHelper();
    protected static NavigationHelper goTo = app.getNavigationHelper();
    protected static AddressListHelper addressListHelper = app.getAddressListHelper();
    protected static WebDriver driver = app.getDriver();

    @BeforeSuite
    public void setUp(){
        login();
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

    protected void login() {
        Login login = new Login("admin", "secret");
        driver.get("http://localhost/addressbook/");
        driver.findElement(By.cssSelector("input[name=user]")).sendKeys(login.getUsername());
        driver.findElement(By.cssSelector("input[name=pass]")).sendKeys(login.getPassword());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    protected boolean createGroupIfNotExist(Groups group){
        boolean isCreated = false;
        goTo.groupPage();
        if(!TestBase.group.isGroupWithNamePresented(group.getName())){
            TestBase.group.createGroup(group);
            isCreated = true;
        }
        return isCreated;
    }

    protected void createAddressIfNotExist(AddressData address){
        goTo.homePage();
        if(!TestBase.address.isAddressWithNamePresented(address.getFirstName())){
            TestBase.address.addAddress(address);
        }
    }

    protected void cleanUp(){
        addressListHelper.cleanup();
        group.cleanup();
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

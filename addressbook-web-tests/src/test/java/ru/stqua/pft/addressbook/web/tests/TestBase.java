package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.ContactDetailedScreenHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.ContactListHelper;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.*;
import ru.stqua.pft.addressbook.web.appmanager.ApplicationManager;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.ContactHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupHelper;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
    protected static GroupHelper group = app.getGroupHelper();
    protected static ContactHelper address = app.getContactHelper();
    protected static NavigationHelper goTo = app.getNavigationHelper();
    protected static ContactListHelper contactListHelper = app.getContactListHelper();
    protected static ContactDetailedScreenHelper contactDetailedScreenHelper = app.getContactDetailedScreenHelper();
    protected static WebDriver driver = app.getDriver();

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
        Login login = new Login("admin", "secret");
        driver.get("http://localhost/addressbook/");
        driver.findElement(By.cssSelector("input[name=user]")).sendKeys(login.getUsername());
        driver.findElement(By.cssSelector("input[name=pass]")).sendKeys(login.getPassword());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    protected AddedDataStatus<GroupData> createGroupIfNotExist(GroupLabels group){
        GroupData newGroup;
        goTo.groupPage();
        if(!TestBase.group.isGroupWithNamePresented(group.getName())){
            newGroup = TestBase.group.createGroup(group);
            return new AddedDataStatus<>(true, newGroup);
        } else {
            return new AddedDataStatus<>(false, GroupProvider.get(group));
        }
    }

    protected AddedDataStatus<ContactData> createContactIfNotExist(ContactData address){
        goTo.homePage();
        if(!TestBase.address.isContactWithNamePresented(address.getFirstName())){
            TestBase.contactListHelper.addContact(address);
            return new AddedDataStatus<>(true, address);
        } else {
            return new AddedDataStatus<>(false, address);
        }
    }

    protected void recreateContact(ContactData newContact){
        contactListHelper.removeContact(newContact);
        createContactIfNotExist(newContact);
    }

    protected void cleanUp(){
        contactListHelper.cleanup();
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

    protected void reduceBeforeCountDueToDataObjCreation() {
        beforeCount -= 1;
    }

    protected void riseBeforeCountDueToDataObjCreation() {
        beforeCount += 1;
    }

    protected String cleanedPhone(String phone){
        return phone.replaceAll("[\\s()]", "").replaceAll("[-]", "");
    }


}

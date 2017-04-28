package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.PropertiesProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactDetailedScreenHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactListHelper;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.*;
import ru.stqua.pft.addressbook.web.appmanager.ApplicationManager;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupHelper;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    protected static GroupHelper group = app.getGroupHelper();
    protected static ContactHelper contactHelper = app.getContactHelper();
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
        PropertiesProvider p = new PropertiesProvider();
        Login login = new Login(p.getProperty("web.adminLogin"), p.getProperty("web.adminPassword"));
        driver.get(p.getProperty("web.baseUrl"));
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
        if(!TestBase.contactHelper.isContactWithNamePresented(address.getFirstName())){
            TestBase.contactListHelper.createContact(address);
            return new AddedDataStatus<>(true, address);
        } else {
            return new AddedDataStatus<>(false, address);
        }
    }

    @Deprecated /*Метод ContactListHelper.createContact() должен заменять действия данного метода*/
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
        return phone.replaceAll("[\\s()-]", "");
    }


}

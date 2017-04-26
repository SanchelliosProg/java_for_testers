package ru.stqua.pft.addressbook.web.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactDetailedScreenHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactListHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;

import java.util.Objects;

/**
 * Created by Александр on 19.03.2017.
 */
public class ApplicationManager {
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private ContactListHelper contactListHelper;
    private ContactDetailedScreenHelper contactDetailedScreenHelper;

    private String browser;

    private WebDriver driver;

    public ApplicationManager(String browser){
        this.browser = browser;
        initWebDriver();
        initHelpers();
    }

    private void initHelpers(){
        groupHelper = new GroupHelper(getDriver());
        contactHelper = new ContactHelper(getDriver());
        navigationHelper = new NavigationHelper(getDriver());
        contactListHelper = new ContactListHelper(getDriver());
        contactDetailedScreenHelper = new ContactDetailedScreenHelper(getDriver());
    }

    private void initWebDriver(){
        if (Objects.equals(browser, BrowserType.CHROME)){
            driver = new ChromeDriver();
        } else if (Objects.equals(browser, BrowserType.FIREFOX)){
            driver = new FirefoxDriver();
        } else {
            driver = new InternetExplorerDriver();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactListHelper getContactListHelper() {
        return contactListHelper;
    }

    public ContactDetailedScreenHelper getContactDetailedScreenHelper() {
        return contactDetailedScreenHelper;
    }
}

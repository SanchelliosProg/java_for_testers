package ru.stqua.pft.addressbook.web.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.ContactHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.ContactListHelper;
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
}

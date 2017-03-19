package ru.stqua.pft.addressbook.web.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqua.pft.addressbook.web.tests.utils.address.AddressHelper;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupHelper;

/**
 * Created by Александр on 19.03.2017.
 */
public class ApplicationManager {
    private GroupHelper groupHelper;
    private AddressHelper addressHelper;
    private static ApplicationManager instance;
    private WebDriver driver;

    private ApplicationManager() {
        initWebDriver();
        groupHelper = new GroupHelper(getDriver());
        addressHelper = new AddressHelper(getDriver());
    }

    public static ApplicationManager getInstance() {
        if(instance == null){
            instance = new ApplicationManager();
            return instance;
        }else {
            return instance;
        }
    }

    private void initWebDriver(){
        driver = new ChromeDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public AddressHelper getAddressHelper() {
        return addressHelper;
    }

    public void openMainPage(){
        driver.get("http://localhost/addressbook/");
    }
}

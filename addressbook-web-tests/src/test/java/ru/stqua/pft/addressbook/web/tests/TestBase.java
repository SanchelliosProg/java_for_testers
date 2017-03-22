package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.Login;
import ru.stqua.pft.addressbook.web.appmanager.ApplicationManager;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.AddressHelper;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupHelper;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    protected WebDriver driver = ApplicationManager.getInstance().getDriver();
    protected GroupHelper groupHelper = ApplicationManager.getInstance().getGroupHelper();
    protected AddressHelper addressHelper = ApplicationManager.getInstance().getAddressHelper();
    protected NavigationHelper navigationHelper = ApplicationManager.getInstance().getNavigationHelper();


    protected void login() {
        Login login = new Login("admin", "secret");
        driver.get("http://localhost/addressbook/");
        driver.findElement(By.cssSelector("input[name=user]")).sendKeys(login.getUsername());
        driver.findElement(By.cssSelector("input[name=pass]")).sendKeys(login.getPassword());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    protected void cleanUp(){
        addressHelper.cleanup();
        groupHelper.cleanup();
        driver.quit();
    }

    protected void debug(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

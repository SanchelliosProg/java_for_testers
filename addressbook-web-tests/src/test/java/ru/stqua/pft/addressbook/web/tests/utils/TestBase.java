package ru.stqua.pft.addressbook.web.tests.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import ru.stqua.pft.addressbook.web.tests.models.Login;
import ru.stqua.pft.addressbook.web.tests.utils.address.AddressHelper;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupHelper;

/**
 * Created by Александр on 18.03.2017.
 */
public class TestBase {
    protected WebDriver driver = ApplicationManager.getInstance().getDriver();
    protected GroupHelper groupHelper = ApplicationManager.getInstance().getGroupHelper();
    protected AddressHelper addressHelper = ApplicationManager.getInstance().getAddressHelper();


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
}

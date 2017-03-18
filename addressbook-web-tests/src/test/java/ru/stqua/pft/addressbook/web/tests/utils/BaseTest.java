package ru.stqua.pft.addressbook.web.tests.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqua.pft.addressbook.web.tests.models.Login;

/**
 * Created by Александр on 18.03.2017.
 */
public class BaseTest {
    protected WebDriver driver = new ChromeDriver();;

    protected void login() {
        Login login = new Login("admin", "secret");
        driver.get("http://localhost/addressbook/");
        driver.findElement(By.cssSelector("input[name=user]")).sendKeys(login.getUsername());
        driver.findElement(By.cssSelector("input[name=pass]")).sendKeys(login.getPassword());
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }
}

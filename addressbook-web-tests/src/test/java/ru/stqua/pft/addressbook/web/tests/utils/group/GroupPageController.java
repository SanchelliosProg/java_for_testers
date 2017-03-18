package ru.stqua.pft.addressbook.web.tests.utils.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqua.pft.addressbook.web.tests.models.GroupData;

import java.util.List;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupPageController {
    public static final String GROUPS_URL = "http://localhost/addressbook/group.php";
    private WebDriver driver;

    public GroupPageController(WebDriver driver) {
        this.driver = driver;
    }

    public void createGroup(GroupData groupData) {
        driver.findElement(By.cssSelector("input[type='text'][name='group_name']")).sendKeys(groupData.getName());
        driver.findElement(By.cssSelector("textarea[name='group_header']")).sendKeys(groupData.getHeader());
        driver.findElement(By.cssSelector("textarea[name='group_footer']")).sendKeys(groupData.getFooter());
        driver.findElement(By.cssSelector("input[name='submit']")).click();
    }

    public void deleteGroups() {
        driver.get(GROUPS_URL);
        List<WebElement> groups = driver.findElements(By.cssSelector("form span"));
        for (WebElement group : groups){
            group.click();
        }
        driver.findElement(By.cssSelector("input[name='delete']")).click();
    }
}

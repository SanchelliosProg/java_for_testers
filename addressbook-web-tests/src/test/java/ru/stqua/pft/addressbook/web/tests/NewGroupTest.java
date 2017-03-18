package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.tests.models.GroupData;
import ru.stqua.pft.addressbook.web.tests.utils.BaseTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class NewGroupTest extends BaseTest {
    private final String GROUPS_URL = "http://localhost/addressbook/group.php";
    @BeforeClass
    public void setUp(){
        login();
    }

    @Test
    public void test1(){
        driver.get(GROUPS_URL);
        driver.findElement(By.cssSelector("input[type='submit'][name='new']")).click();
        GroupData groupData = new GroupData("Братство кольца",
                "Герои Властелина Колец",
                "Книжка и фильм просто замечательны");
        createGroup(groupData);
        driver.get(GROUPS_URL);
        String text = driver.findElement(By.cssSelector("span.group")).getText();
        System.out.println(text);
        assertThat(text, is(groupData.getName()));
    }


    @AfterClass
    public void tearDown(){
        deleteGroups();
        driver.quit();
    }

    private void createGroup(GroupData groupData) {
        driver.findElement(By.cssSelector("input[type='text'][name='group_name']")).sendKeys(groupData.getName());
        driver.findElement(By.cssSelector("textarea[name='group_header']")).sendKeys(groupData.getHeader());
        driver.findElement(By.cssSelector("textarea[name='group_footer']")).sendKeys(groupData.getFooter());
        driver.findElement(By.cssSelector("input[name='submit']")).click();
    }

    private void deleteGroups() {
        driver.get(GROUPS_URL);
        List<WebElement> groups = driver.findElements(By.cssSelector("form span"));
        for (WebElement group : groups){
            group.click();
        }
        driver.findElement(By.cssSelector("input[name='delete']")).click();
    }


}

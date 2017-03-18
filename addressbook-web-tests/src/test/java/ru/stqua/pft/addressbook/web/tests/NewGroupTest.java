package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.tests.models.GroupData;
import ru.stqua.pft.addressbook.web.tests.utils.BaseTest;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupPageController;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupProvider;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class NewGroupTest extends BaseTest {
    private GroupPageController groupPageController;
    @BeforeClass
    public void setUp(){
        login();
        groupPageController = new GroupPageController(driver);
    }

    @Test
    public void test1(){
        driver.get(GroupPageController.GROUPS_URL);
        driver.findElement(By.cssSelector("input[type='submit'][name='new']")).click();
        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        groupPageController.createGroup(groupData);
        driver.get(GroupPageController.GROUPS_URL);
        String text = driver.findElement(By.cssSelector("span.group")).getText();
        assertThat(text, is(groupData.getName()));
    }

    @AfterClass
    public void tearDown(){
        groupPageController.deleteGroups();
        driver.quit();
    }
}

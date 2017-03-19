package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.tests.models.GroupData;
import ru.stqua.pft.addressbook.web.tests.utils.BaseTest;
import ru.stqua.pft.addressbook.web.tests.utils.group.GroupHelper;
import ru.stqua.pft.addressbook.web.tests.models.GroupProvider;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class NewGroupTest extends BaseTest {
    @BeforeClass
    public void setUp(){
        login();
    }

    @Test
    public void test1(){
       groupHelper.open();
        groupHelper.openNewGropPage();
        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        groupHelper.createGroup(groupData);
        groupHelper.open();
        String text = driver.findElement(By.cssSelector("span.group")).getText();
        assertThat(text, is(groupData.getName()));
    }

    @AfterClass
    public void tearDown(){
        cleanUp();
    }
}

package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditGroupTest extends TestBase {
    @BeforeMethod
    public void setUp() {
        login();

    }

    @Test
    public void editTest() {
        createGroupIfNotExist(Groups.FAIRY_WORLDS);
        List<WebElement> before = groupHelper.getGroupList();
        GroupData groupData = GroupProvider.get(Groups.COOL_ACTION_MOVIES);
        navigationHelper.goToGroupPage();
        groupHelper.openEditFirstGroup();
        groupHelper.editGroup(groupData);
        groupHelper.clickUpdate();
        navigationHelper.goToGroupPage();
        List<WebElement> after = groupHelper.getGroupList();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));
        assertThat(after.size(), is(before.size()));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

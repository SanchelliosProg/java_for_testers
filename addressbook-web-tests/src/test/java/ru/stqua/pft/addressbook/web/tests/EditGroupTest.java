package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

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
        if(!groupHelper.isThereGroup()){
            groupHelper.createGroup(Groups.FAIRY_WORLDS);
        }
        GroupData groupData = GroupProvider.get(Groups.COOL_ACTION_MOVIES);
        navigationHelper.goToGroupPage();
        groupHelper.openEditFirstGroup();
        groupHelper.editGroup(groupData);
        groupHelper.clickUpdate();
        navigationHelper.goToGroupPage();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

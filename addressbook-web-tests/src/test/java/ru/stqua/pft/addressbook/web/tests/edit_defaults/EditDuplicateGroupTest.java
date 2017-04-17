package ru.stqua.pft.addressbook.web.tests.edit_defaults;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditDuplicateGroupTest extends TestBase {
    private GroupData groupData;

    @BeforeMethod
    public void setUp() {
        login();
        groupData = group.createGroup(GroupLabels.DUPLICATE_GROUP);
    }

    @Test
    public void editTest() {
        String oldName = groupData.getName();
        groupData = GroupProvider.get(GroupLabels.DUPLICATE_GROUP);
        group.open();
        group.openEditGroupWithName(oldName);
        group.editGroup(groupData);
        group.clickUpdate();
        group.open();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

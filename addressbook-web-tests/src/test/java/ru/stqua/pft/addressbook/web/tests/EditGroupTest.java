package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditGroupTest extends TestBase {

    @Test
    public void editTest() {
        createGroupIfNotExist(Groups.FAIRY_WORLDS);
        List<GroupData> before = groupHelper.getGroupDataList();
        GroupData groupData = GroupProvider.get(Groups.FAIRY_WORLDS);
        groupData.setHeader("new");
        navigationHelper.goToGroupPage();
        groupHelper.openEditFirstGroup();
        groupHelper.editGroup(groupData);
        groupHelper.clickUpdate();
        navigationHelper.goToGroupPage();
        List<GroupData> after = groupHelper.getGroupDataList();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));
        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertNotEquals(before, after);
    }

}

package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupHelper;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateGroupTest extends TestBase {

    @Test
    public void createGroup() {
        boolean doAdd = false;
        List<GroupData> before = groupHelper.getGroupDataList();

        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        navigationHelper.goToGroupPage();
        if(!groupHelper.isGroupWithNamePresented(groupData.getName())){
            groupHelper.createGroup(Groups.BROTHERHOOD_OF_RING);
            doAdd = true;
        }
        navigationHelper.goToGroupPage();
        List<GroupData> after = groupHelper.getGroupDataList();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));

        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        if(doAdd){
            before.add(groupData);
        }

        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}

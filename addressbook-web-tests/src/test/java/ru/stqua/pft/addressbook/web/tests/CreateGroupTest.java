package ru.stqua.pft.addressbook.web.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateGroupTest extends TestBase {

    @Test
    public void createGroup() {
        boolean doAdd = false;
        Set<GroupData> before = group.all();

        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        goTo.groupPage();
        if(!group.isGroupWithNamePresented(groupData.getName())){
            group.createGroup(Groups.BROTHERHOOD_OF_RING);
            doAdd = true;
        }
        goTo.groupPage();
        Set<GroupData> after = group.all();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));

        if(doAdd){
            before.add(groupData);
        }

        Assert.assertEquals(before, after);
    }

}

package ru.stqua.pft.addressbook.web.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.Comparator;
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
        List<GroupData> before = group.list();

        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        goTo.groupPage();
        if(!group.isGroupWithNamePresented(groupData.getName())){
            group.createGroup(Groups.BROTHERHOOD_OF_RING);
            doAdd = true;
        }
        goTo.groupPage();
        List<GroupData> after = group.list();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));

        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        if(doAdd){
            before.add(groupData);
        }

        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }

}

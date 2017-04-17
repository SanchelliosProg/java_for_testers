package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateGroupTest extends TestBase {

    @Test
    public void createGroup() {
        boolean doAdd = false;
        DataSet<GroupData> before = group.all();

        GroupData groupData = GroupProvider.get(GroupLabels.BROTHERHOOD_OF_RING);
        goTo.groupPage();
        if(!group.isGroupWithNamePresented(groupData.getName())){
            group.createGroup(GroupLabels.BROTHERHOOD_OF_RING);
            doAdd = true;
        }
        goTo.groupPage();
        DataSet after = group.all();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));

        if(doAdd){
            assertThat(after, equalTo(before.withAdded(groupData)));
        } else {
            assertThat(after, equalTo(before));
        }


    }

}

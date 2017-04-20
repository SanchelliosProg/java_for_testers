package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void preconditionsSetUp(){
        goTo.groupPage();
        if(!group.isAnyGroupPresented()){
            group.createGroup(GroupLabels.FAIRY_WORLDS);
        }
    }

    @Test
    public void editTest() {
        DataSet<GroupData> before = group.all();

        GroupData newGroup = GroupProvider.get(GroupLabels.COOL_ACTION_MOVIES);
        GroupData groupToRemove = group.editFirstGroup(newGroup);
        goTo.groupPage();
        DataSet<GroupData> after = group.all();

        assertThat(after, equalTo(before.without(groupToRemove).withAdded(newGroup)));
    }


}

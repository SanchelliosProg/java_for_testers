package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
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
public class ModifyGroupTest extends TestBase {

    @BeforeMethod
    public void preconditionsSetUp(){
        if (dbHelper.groups().size() == 0){
            goTo.groupPage();
            if(!group.isAnyGroupPresented()){
                group.createGroup(GroupLabels.FAIRY_WORLDS);
            }
        }
    }

    @Test
    public void editTest() {
        DataSet<GroupData> before = dbHelper.groups();
        beforeCount = dbHelper.groups().size();
        GroupData newGroup = GroupProvider.get(GroupLabels.COOL_ACTION_MOVIES);
        goTo.groupPage();
        GroupData modifiedGroup = group.modifyFirstGroup(newGroup);
        newGroup.withId(modifiedGroup.getId());
        goTo.groupPage();
        assertThat(dbHelper.groups().size(), equalTo(beforeCount));
        DataSet<GroupData> after = dbHelper.groups();

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(newGroup)));
        verifyGroupListInUI();
    }
}

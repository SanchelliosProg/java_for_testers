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
        createGroupIfNotExist(GroupLabels.FAIRY_WORLDS);
    }

    @Test
    public void editTest() {

        DataSet<GroupData> before = group.all();
        GroupData groupData = GroupProvider.get(GroupLabels.FAIRY_WORLDS);

        group.modifyGroupHeader(groupData, "new", goTo);

        DataSet<GroupData> after = group.all();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));

        assertThat(after, equalTo(before));
    }


}

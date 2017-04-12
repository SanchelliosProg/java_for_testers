package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void preconditionsSetUp(){
        createGroupIfNotExist(Groups.FAIRY_WORLDS);
    }

    @Test
    public void editTest() {

        List<GroupData> before = group.list();
        GroupData groupData = GroupProvider.get(Groups.FAIRY_WORLDS);

        group.modifyGroupHeader(groupData, "new", goTo);

        List<GroupData> after = group.list();
        assertThat(group.isGroupWithNamePresented(groupData.getName()), is(true));
        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        assertThat(after, is(before));
    }


}

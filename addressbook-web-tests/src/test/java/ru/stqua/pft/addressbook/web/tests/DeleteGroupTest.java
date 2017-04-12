package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class DeleteGroupTest extends TestBase {

    @BeforeMethod
    public void preconditionsSetUp(){
        goTo.groupPage();
        createGroupIfNotExist(Groups.GOOD_PEOPLE);
    }

    @Test
    public void deleteTest() {

        List<GroupData> before = group.list();
        goTo.groupPage();
        group.removeGroupWithName(Groups.GOOD_PEOPLE.getName());
        List<GroupData> after = group.list();
        assertThat(after.size(), is(before.size() - 1));

        group.removeGroupWithName(Groups.GOOD_PEOPLE.getName());
        assertThat(before, is(not(after)));
    }

}

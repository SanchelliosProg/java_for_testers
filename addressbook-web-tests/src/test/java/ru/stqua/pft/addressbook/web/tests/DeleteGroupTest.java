package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.model.DataSet;
import ru.stqua.pft.addressbook.web.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class DeleteGroupTest extends TestBase {
    private AddedDataStatus<GroupData> newGroupBundle;
    @BeforeMethod
    public void preconditionsSetUp(){
        goTo.groupPage();
        newGroupBundle = createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void deleteTest() {

        DataSet<GroupData> before = group.all();
        beforeCount = group.count();
        goTo.groupPage();
        group.removeGroupWithName(GroupLabels.GOOD_PEOPLE.getName());
        goTo.groupPage();
        assertThat(group.count(), equalTo(beforeCount - 1));
        DataSet<GroupData> after = group.all();

        group.removeGroupWithName(GroupLabels.GOOD_PEOPLE.getName());

        assertThat(after, equalTo(before.without(newGroupBundle.data())));
    }

}

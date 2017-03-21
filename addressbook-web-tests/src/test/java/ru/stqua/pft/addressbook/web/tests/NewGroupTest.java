package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.tests.models.GroupData;
import ru.stqua.pft.addressbook.web.tests.utils.TestBase;
import ru.stqua.pft.addressbook.web.tests.models.GroupProvider;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class NewGroupTest extends TestBase {
    boolean isLoggedIn = false;
    @BeforeMethod
    public void setUp() {
        if(!isLoggedIn){
            login();
        }
    }

    @Test
    public void CRUDGroup() {
        GroupData groupData = addGroup();
        groupHelper.open();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));

        groupHelper.openEditGroupWithName(groupData.getName());
        String newGroupName = "Новая Группа";
        groupData.setName(newGroupName);
        groupHelper.editGroupName(newGroupName);
        groupHelper.clickUpdate();
        groupHelper.open();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));

        groupHelper.cleanup();
        assertThat(groupHelper.isGroupsPresented(), is(false));
    }

    private GroupData addGroup() {
        groupHelper.open();
        groupHelper.openNewGroupPage();
        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        groupHelper.createGroup(groupData);
        return groupData;
    }

    @AfterMethod
    public void tearDown() {
        groupHelper.cleanup();
        driver.quit();
    }


}

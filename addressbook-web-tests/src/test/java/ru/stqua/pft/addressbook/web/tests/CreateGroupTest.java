package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateGroupTest extends TestBase {

    @BeforeMethod
    public void setUp() {
        login();
    }

    @Test
    public void CRUDGroup() {
        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);

        if(!groupHelper.isThereGroup()){
            groupHelper.createGroup(Groups.BROTHERHOOD_OF_RING);
        }

        navigationHelper.goToGroupPage();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));

        groupHelper.cleanup();
        assertThat(groupHelper.isAnyGroupPresented(), is(false));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

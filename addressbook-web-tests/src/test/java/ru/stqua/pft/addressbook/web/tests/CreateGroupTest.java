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
    public void createGroup() {
        GroupData groupData = GroupProvider.get(Groups.BROTHERHOOD_OF_RING);
        navigationHelper.goToGroupPage();
        if(!groupHelper.isGroupWithNamePresented(groupData.getName())){
            groupHelper.createGroup(Groups.BROTHERHOOD_OF_RING);
        }
        debugWait();
        navigationHelper.goToGroupPage();
        debugWait();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(true));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

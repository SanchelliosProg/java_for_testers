package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class DeleteGroupTest extends TestBase {
    private GroupData groupData;

    @BeforeMethod
    public void setUp() {
        login();
        navigationHelper.goToGroupPage();
    }

    @Test
    public void deleteTest() {
        if (!groupHelper.isThereGroup()) {
            groupHelper.createGroup(Groups.GOOD_PEOPLE);
        }
        navigationHelper.goToGroupPage();
        groupHelper.removeFirstGroup();
        assertThat(groupHelper.isGroupWithNamePresented(groupData.getName()), is(false));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

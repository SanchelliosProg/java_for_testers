package ru.stqua.pft.addressbook.web.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;

import java.util.List;

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
        createGroupIfNotExist(Groups.GOOD_PEOPLE);
        List<WebElement> before = groupHelper.getGroupList();
        navigationHelper.goToGroupPage();
        groupHelper.removeGroupWithName(Groups.GOOD_PEOPLE.getName());
        List<WebElement> after = groupHelper.getGroupList();
        assertThat(after.size(), is(before.size() - 1));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

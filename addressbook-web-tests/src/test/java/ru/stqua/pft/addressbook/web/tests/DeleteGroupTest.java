package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.GroupData;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class DeleteGroupTest extends TestBase {
    private GroupData groupData;

    @BeforeMethod
    public void setUp() {
        login();
        groupHelper.open();
        groupHelper.cleanup();
        groupData = groupHelper.addGroup(Groups.GOOD_PEOPLE);
    }

    @Test
    public void deleteTest(){

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

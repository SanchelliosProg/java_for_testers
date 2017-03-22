package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditAddressTest extends TestBase{
    AddressData data;
    @BeforeMethod
    public void setUp(){
        login();
        groupHelper.open();
        groupHelper.openNewGroupPage();
        groupHelper.createGroup(GroupProvider.get(Groups.COOL_ACTION_MOVIES));
    }

    @Test
    public void editTest() {
        data = AddressProvider.getAddress(Addresses.JOHN_MATRIX);
        addressHelper.open();
        addressHelper.addAddress(data);
        navigationHelper.openMainPage();
        String prevName = data.getFirstName();
        data = AddressProvider.getAddress(Addresses.CASEY_RAYBACK);
        addressHelper.editAddressWithName(prevName, data);
        navigationHelper.openMainPage();
        assertThat(addressHelper.isElementWithTextExists(data.getFirstName()), is(true));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

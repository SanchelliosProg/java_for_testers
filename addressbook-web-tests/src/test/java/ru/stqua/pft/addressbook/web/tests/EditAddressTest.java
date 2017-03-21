package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

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

        addressHelper.open();
        data = AddressProvider.getAddress(Addresses.CASEY_RAYBACK);

    }

//            addressHelper.openEditAddressWithName(frodo.getFirstName());
//        frodo.setFirstName("Фёдор");
//        addressHelper.editFirstName(frodo.getFirstName());
//        navigationHelper.openMainPage();
//    assertThat(addressHelper.isElementWithTextExists(frodo.getFirstName()), is(true));


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

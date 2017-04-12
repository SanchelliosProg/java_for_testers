package ru.stqua.pft.addressbook.web.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.model.GroupProvider;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditAddressTest extends TestBase{

    @Test
    public void editTest() {
        createGroupIfNotExist(Groups.COOL_ACTION_MOVIES);
        AddressData beforeAddress = AddressProvider.getAddress(Addresses.JOHN_MATRIX);

        createAddressIfNotExist(beforeAddress);
        List<AddressData> before = addressListHelper.getAddresses();

        navigationHelper.goToHomePage();
        String prevName = beforeAddress.getFirstName();

        AddressData afterAddress = AddressProvider.getAddress(Addresses.CASEY_RAYBACK);
        addressHelper.editAddressWithName(prevName, afterAddress);

        List<AddressData> after = addressListHelper.getAddresses();

        navigationHelper.goToHomePage();
        assertThat(addressHelper.isAddressWithNamePresented(afterAddress.getFirstName()), is(true));
        Assert.assertNotEquals(before, after);
    }
}

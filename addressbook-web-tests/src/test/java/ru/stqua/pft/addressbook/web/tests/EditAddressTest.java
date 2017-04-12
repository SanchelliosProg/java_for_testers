package ru.stqua.pft.addressbook.web.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditAddressTest extends TestBase{

    @BeforeMethod
    public void preconditionsSetUp(){
        createGroupIfNotExist(Groups.COOL_ACTION_MOVIES);
    }

    @Test
    public void editTest() {

        AddressData beforeAddress = AddressProvider.getAddress(Addresses.JOHN_MATRIX);

        createAddressIfNotExist(beforeAddress);
        List<AddressData> before = addressListHelper.getAddresses();

        goTo.homePage();
        String prevName = beforeAddress.getFirstName();

        AddressData afterAddress = AddressProvider.getAddress(Addresses.CASEY_RAYBACK);
        address.editAddressWithName(prevName, afterAddress);

        List<AddressData> after = addressListHelper.getAddresses();

        goTo.homePage();
        assertThat(address.isAddressWithNamePresented(afterAddress.getFirstName()), is(true));
        Assert.assertNotEquals(before, after);
    }
}

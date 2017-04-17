package ru.stqua.pft.addressbook.web.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditAddressTest extends TestBase{

    @BeforeMethod
    public void preconditionsSetUp(){
        createGroupIfNotExist(GroupLabels.COOL_ACTION_MOVIES);
    }

    @Test
    public void editTest() {

        AddressData beforeAddress = AddressProvider.getAddress(Addresses.JOHN_MATRIX);

        createAddressIfNotExist(beforeAddress);
        DataSet<AddressData> before = addressListHelper.all();

        goTo.homePage();

        AddressData afterAddress = AddressProvider.getAddress(Addresses.CASEY_RAYBACK);
        address.editAddressWithName(beforeAddress.getFirstName(), afterAddress);

        DataSet<AddressData> after = addressListHelper.all();

        goTo.homePage();
        assertThat(address.isAddressWithNamePresented(afterAddress.getFirstName()), is(true));

        assertThat(after, is(not(before)));
    }
}

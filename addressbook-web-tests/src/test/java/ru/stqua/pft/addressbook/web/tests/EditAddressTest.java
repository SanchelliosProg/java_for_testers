package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditAddressTest extends TestBase{

    @BeforeMethod
    public void preconditionsSetUp(){
        goTo.homePage();
        addressListHelper.cleanup();
        addressListHelper.addAddress(AddressProvider.getAddress(Addresses.JOHN_MATRIX));
        createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void editTest() {
        DataSet<AddressData> before = addressListHelper.all();
        goTo.homePage();
        AddressData newAddress = AddressData.newBuilder().firstName("Lionel").lastName("Richie").address("USA")
                .phone("02").email("donwritehere@getoff.us").group(GroupLabels.GOOD_PEOPLE).build();
        AddressData oldAddress = addressListHelper.editFirstAddress(newAddress);

        DataSet<AddressData> after = addressListHelper.all();
        assertThat(after, equalTo(before.without(oldAddress).withAdded(newAddress)));
    }
}

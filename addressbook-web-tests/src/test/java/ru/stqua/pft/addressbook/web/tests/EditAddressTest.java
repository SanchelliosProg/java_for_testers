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
        if(!addressListHelper.isAddressesPresented()){
            address.addAddress(AddressProvider.getAddress(Addresses.JOHN_MATRIX));
        }
        createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void editTest() {
        DataSet<AddressData> before = addressListHelper.all();
        goTo.homePage();
        AddressData removedAddress = addressListHelper.removeFirstAddress();
        AddressData addedAddress = AddressData.newBuilder().firstName("Lionel").lastName("Richie").address("USA")
                .phone("02").email("donwritehere@getoff.us").group(GroupLabels.GOOD_PEOPLE).build();
        address.addAddress(addedAddress);
        DataSet<AddressData> after = addressListHelper.all();
        assertThat(after, equalTo(before.without(removedAddress).withAdded(addedAddress)));
    }
}

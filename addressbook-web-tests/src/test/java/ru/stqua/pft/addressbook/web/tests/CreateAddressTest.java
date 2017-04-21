package ru.stqua.pft.addressbook.web.tests;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.model.DataSet;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateAddressTest extends TestBase {

    @BeforeMethod
    public void preconditions(){
        createGroupIfNotExist(GroupLabels.BROTHERHOOD_OF_RING);
    }

    @Test
    public void addContact(){
        DataSet<AddressData> before = addressListHelper.all();
        beforeCount = addressListHelper.count();

        AddressData frodo = AddressProvider.getAddress(Addresses.FRODO_BAGGINS);
        goTo.homePage();

        AddedDataStatus<AddressData> newAddress = createAddressIfNotExist(frodo);

        goTo.homePage();
        assertThat(addressListHelper.count(), equalTo(beforeCount+1));
        DataSet<AddressData> after = addressListHelper.all();

        goTo.homePage();
        if(newAddress.isCreated()){
            assertThat(after, equalTo(before.withAdded(frodo)));
        }else {
            assertThat(after, equalTo(before));
        }

    }

}

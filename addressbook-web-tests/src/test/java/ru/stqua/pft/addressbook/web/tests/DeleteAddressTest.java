package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Александр on 22.03.2017.
 */
public class DeleteAddressTest extends TestBase {

    @BeforeMethod
    public void preconditionsSetUp(){
        createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void deleteTest() {

        DataSet<AddressData> before = addressListHelper.all();
        AddressData ghandi = AddressProvider.getAddress(Addresses.M_GHANDI);
        goTo.homePage();

        createAddressIfNotExist(ghandi);

        goTo.homePage();
        addressListHelper.removeFirstAddress();
        goTo.homePage();
        DataSet<AddressData> after = addressListHelper.all();

        assertThat(after, equalTo(before.without(ghandi)));
    }

}

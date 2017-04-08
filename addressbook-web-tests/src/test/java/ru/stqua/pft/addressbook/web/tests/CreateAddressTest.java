package ru.stqua.pft.addressbook.web.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateAddressTest extends TestBase {

    @BeforeMethod
    public void setUp(){
        login();
    }

    @Test
    public void addContact(){
        List<AddressData> before = addressListHelper.getAddresses();
        createGroupIfNotExist(Groups.BROTHERHOOD_OF_RING);

        AddressData frodo = AddressProvider.getAddress(Addresses.FRODO_BAGGINS);
        navigationHelper.goToHomePage();

        createAddressIfNotExist(frodo);

        List<AddressData> after = addressListHelper.getAddresses();
        before.add(frodo);

        Comparator<? super AddressData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

        before.sort(byId);
        after.sort(byId);

        navigationHelper.goToHomePage();
        Assert.assertEquals(after, before);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package ru.stqua.pft.addressbook.web.tests;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateAddressTest extends TestBase {

    @BeforeMethod
    public void setUp(){
        login();
        debugWait();
    }

    @Test
    public void addContact(){
        createGroupIfNotExist(Groups.BROTHERHOOD_OF_RING);

        AddressData frodo = AddressProvider.getAddress(Addresses.FRODO_BAGGINS);
        navigationHelper.goToHomePage();

        createAddressIfNotExist(frodo);

        navigationHelper.goToHomePage();
        assertThat(addressHelper.isAddressWithNamePresented(frodo.getFirstName()), is(true));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 22.03.2017.
 */
public class DeleteAddressTest extends TestBase {
    @BeforeMethod
    public void setUp(){
        login();
    }

    @Test
    public void deleteTest(){
        createGroupIfNotExist(Groups.GOOD_PEOPLE);
        AddressData ghandi = AddressProvider.getAddress(Addresses.M_GHANDI);
        addressHelper.open();

        createAddressIfNotExist(ghandi);

        navigationHelper.goToHomePage();
        addressHelper.deleteFirstAddress();
        navigationHelper.goToHomePage();
        assertThat(addressHelper.isAddressWithNamePresented(ghandi.getFirstName()), is(false));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

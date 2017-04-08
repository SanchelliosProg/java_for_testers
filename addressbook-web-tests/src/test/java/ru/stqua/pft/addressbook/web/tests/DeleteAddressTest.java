package ru.stqua.pft.addressbook.web.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import ru.stqua.pft.addressbook.web.appmanager.helpers.navigation.NavigationHelper;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.model.AddressProvider;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 22.03.2017.
 */
public class DeleteAddressTest extends TestBase {
    @BeforeMethod
    public void setUp() {
        login();
    }

    @Test
    public void deleteTest() {
        NavigationHelper navigationHelper = new NavigationHelper(driver);
        boolean isCreated = createGroupIfNotExist(Groups.GOOD_PEOPLE);
        List<AddressData> before = addressListHelper.getAddresses();
        AddressData ghandi = AddressProvider.getAddress(Addresses.M_GHANDI);
        navigationHelper.goToHomePage();

        createAddressIfNotExist(ghandi);

        navigationHelper.goToHomePage();
        addressListHelper.deleteFirstAddress();
        navigationHelper.goToHomePage();
        List<AddressData> after = addressListHelper.getAddresses();

        int delta = 0;
        if(isCreated){
            delta = 1;
        }

        Assert.assertEquals(after.size(), before.size() - delta);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

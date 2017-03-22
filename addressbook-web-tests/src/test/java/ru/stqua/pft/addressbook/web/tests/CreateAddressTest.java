package ru.stqua.pft.addressbook.web.tests;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.model.AddressData;
import ru.stqua.pft.addressbook.web.appmanager.ApplicationManager;
import ru.stqua.pft.addressbook.web.model.AddressProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.model.GroupProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateAddressTest extends TestBase {

    @BeforeMethod
    public void setUp(){
        login();
        groupHelper.open();
        groupHelper.openNewGroupPage();
        groupHelper.createGroup(GroupProvider.get(Groups.BROTHERHOOD_OF_RING));
    }

    @Test
    public void addContact(){
        AddressData frodo = AddressProvider.getAddress(Addresses.FRODO_BAGGINS);
        addressHelper.open();
        addressHelper.addAddress(frodo);
        navigationHelper.openMainPage();
        assertThat(addressHelper.isElementWithTextExists(frodo.getFirstName()), is(true));

        addressHelper.cleanup();
        navigationHelper.openMainPage();
        assertThat(addressHelper.isAddressesPresented(), is(false));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

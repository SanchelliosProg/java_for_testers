package ru.stqua.pft.addressbook.web.tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.tests.models.AddressData;
import ru.stqua.pft.addressbook.web.tests.utils.ApplicationManager;
import ru.stqua.pft.addressbook.web.tests.utils.TestBase;
import ru.stqua.pft.addressbook.web.tests.models.AddressProvider;
import ru.stqua.pft.addressbook.web.tests.utils.address.Addresses;
import ru.stqua.pft.addressbook.web.tests.models.GroupProvider;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class NewAddressTest extends TestBase {

    @BeforeClass
    public void setUp(){
        login();
        groupHelper.open();
        groupHelper.openNewGropPage();
        groupHelper.createGroup(GroupProvider.get(Groups.BROTHERHOOD_OF_RING));
    }

    @Test
    public void addContact(){
        AddressData frodo = AddressProvider.getAddress(Addresses.FRODO_BAGGINS);
        addressHelper.open();
        addressHelper.addAddress(frodo);
        ApplicationManager.getInstance().openMainPage();
        assertThat(addressHelper.isElementWithTextExists(frodo.getFirstName()), is(true));
    }

    @AfterClass
    public void tearDown(){
        cleanUp();
    }
}

package ru.stqua.pft.addressbook.web.tests;
import org.testng.annotations.*;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Contacts;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 18.03.2017.
 */
public class CreateContactTest extends TestBase {

    @BeforeMethod
    public void preconditions(){
        createGroupIfNotExist(GroupLabels.BROTHERHOOD_OF_RING);
    }

    @Test
    public void addContact(){
        DataSet<ContactData> before = contactListHelper.all();
        beforeCount = contactListHelper.count();

        ContactData frodo = ContactProvider.getContact(Contacts.FRODO_BAGGINS);
        goTo.homePage();

        AddedDataStatus<ContactData> newAddress = createContactIfNotExist(frodo);
        if(!newAddress.isCreated()){
            beforeCount -= 1;
        }

        goTo.homePage();
        assertThat(contactListHelper.count(), equalTo(beforeCount+1));
        DataSet<ContactData> after = contactListHelper.all();

        goTo.homePage();
        if(newAddress.isCreated()){
            assertThat(after, equalTo(before.withAdded(frodo)));
        }else {
            assertThat(after, equalTo(before));
        }

    }

}

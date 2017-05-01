package ru.stqua.pft.addressbook.web.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.appmanager.utils.RandomPhoneNumberProvider;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by avvasi78 on 21.03.2017.
 */
public class EditContactTest extends TestBase{

    @BeforeMethod
    public void preconditionsSetUp(){
        if (dbHelper.contacts().size() != 0) {
            goTo.homePage();
            contactListHelper.cleanup();
            contactListHelper.createContact(ContactProvider.getContact(ContactsLabels.JOHN_MATRIX));
        } else {
            contactListHelper.createContact(ContactProvider.getContact(ContactsLabels.JOHN_MATRIX));
        }
        createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void editTest() {
        DataSet<ContactData> before = dbHelper.contacts();
        beforeCount = before.size();
        goTo.homePage();
        ContactData newAddress = ContactData.newBuilder().firstName("Lionel").lastName("Richie").address("USA")
                .homePhoneOnly(RandomPhoneNumberProvider.generateRandomNumber()).email("donwritehere@getoff.us")
                .group(GroupLabels.GOOD_PEOPLE).noPhoto().build();
        ContactData oldAddress = contactListHelper.editFirstContact(newAddress);
        newAddress.setId(oldAddress.getId());
        goTo.homePage();
        assertThat(dbHelper.contacts().size(), CoreMatchers.equalTo(beforeCount));
        DataSet<ContactData> after = dbHelper.contacts();
        assertThat(after, equalTo(before.without(oldAddress).withAdded(newAddress)));
    }
}

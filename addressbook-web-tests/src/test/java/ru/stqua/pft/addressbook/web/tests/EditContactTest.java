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
        goTo.homePage();
        contactListHelper.cleanup();
        contactListHelper.addContact(ContactProvider.getContact(ContactsLabels.JOHN_MATRIX));
        createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void editTest() {
        DataSet<ContactData> before = contactListHelper.all();
        beforeCount = contactListHelper.count();
        goTo.homePage();
        ContactData newAddress = ContactData.newBuilder().firstName("Lionel").lastName("Richie").address("USA")
                .homePhoneOnly(RandomPhoneNumberProvider.generateRandomNumber()).email("donwritehere@getoff.us")
                .group(GroupLabels.GOOD_PEOPLE).build();
        ContactData oldAddress = contactListHelper.editFirstContact(newAddress);
        goTo.homePage();
        assertThat(contactListHelper.count(), CoreMatchers.equalTo(beforeCount));
        DataSet<ContactData> after = contactListHelper.all();
        assertThat(after, equalTo(before.without(oldAddress).withAdded(newAddress)));
    }
}

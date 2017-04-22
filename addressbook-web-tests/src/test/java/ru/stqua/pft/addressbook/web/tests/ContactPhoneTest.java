package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqua.pft.addressbook.web.appmanager.helpers.address.Contacts.MARTIN_RIGGS;

/**
 * Created by Александр on 22.04.2017.
 */
public class ContactPhoneTest extends TestBase {


    @Test
    public void phoneCheck(){
        createGroupIfNotExist(GroupLabels.COOL_ACTION_MOVIES);
        goTo.homePage();
        ContactData newContact = ContactProvider.getContact(MARTIN_RIGGS);
        recreateContact(newContact);
        goTo.homePage();
        ContactData contactToExamine = contactListHelper.getContactWithName(MARTIN_RIGGS.getName());
        assertThat("Home phones should be equal", cleanedPhone(newContact.getHomePhone()),
                equalTo(contactToExamine.getHomePhone()));
        assertThat("Mobile phones should be equal", cleanedPhone(newContact.getMobilePhone()),
                equalTo(contactToExamine.getMobilePhone()));
        assertThat("Work phones should be equal", cleanedPhone(newContact.getWorkPhone()),
                equalTo(contactToExamine.getWorkPhone()));
    }

    private String cleanedPhone(String phone){
        return phone.replaceAll("[\\s()]", "").replaceAll("[-]", "");
    }
}

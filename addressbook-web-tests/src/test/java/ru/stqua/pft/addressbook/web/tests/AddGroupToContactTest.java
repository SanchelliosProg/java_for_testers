package ru.stqua.pft.addressbook.web.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Александр on 05.05.2017.
 */
public class AddGroupToContactTest extends TestBase {

    AddedDataStatus<ContactData> addedContact;
    AddedDataStatus<GroupData> addedGroup;

    @BeforeMethod
    public void setData() {
        createGroupIfNotExist(GroupLabels.BROTHERHOOD_OF_RING);
        addedGroup = createGroupIfNotExist(GroupLabels.FAIRY_WORLDS);
        addedContact = createContactIfNotExist(ContactProvider.getContact(ContactsLabels.FRODO_BAGGINS));

    }

    @Test
    public void addToGroup() {
        goTo.homePage();
        addedContact.data().addGroup(addedGroup.data());
        contactListHelper.setGroupsToContact(addedContact.data());
        ContactData dataFormDb = null;

        try {
            dataFormDb = dbHelper.getContactWithLastName(addedContact.data().getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(addedContact.data(), equalTo(dataFormDb));
    }
}

package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.GroupData;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Александр on 07.05.2017.
 */
public class DeleteGroupFromContactTest extends TestBase{

    AddedDataStatus<ContactData> addedContact;
    AddedDataStatus<GroupData> addedGroup;

    @BeforeMethod
    public void setData() {
        addedGroup = createGroupIfNotExist(GroupLabels.COOL_ACTION_MOVIES);
        addedContact = createContactIfNotExist(ContactProvider.getContact(ContactsLabels.JOHN_MATRIX));
        contactListHelper.setGroupsToContact(addedContact.data());
    }

    @Test
    public void deleteFromGroup() {
        goTo.homePage();
        contactListHelper.openDetailedInfoOfContactWithLastName(addedContact.data().getLastName());
        contactDetailedScreenHelper.clickFirstGroup();
        contactListOfGroupHelper.removeFromGroup(addedContact.data());
        addedContact.data().removeGroup(addedGroup.data());

        ContactData dataFormDb = null;

        try {
            dataFormDb = dbHelper.getContactWithLastName(addedContact.data().getLastName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(addedContact.data(), equalTo(dataFormDb));
    }
}

package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.AddedDataStatus;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.DataSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Александр on 22.03.2017.
 */
public class DeleteContactTest extends TestBase {

    @BeforeMethod
    public void preconditionsSetUp(){
        createGroupIfNotExist(GroupLabels.GOOD_PEOPLE);
    }

    @Test
    public void deleteTest() {

        DataSet<ContactData> before = contactListHelper.all();
        beforeCount = contactListHelper.count();
        ContactData ghandi = ContactProvider.getContact(ContactsLabels.M_GHANDI);
        goTo.homePage();

        AddedDataStatus<ContactData> status = createContactIfNotExist(ghandi);

        if (status.isCreated()){
            riseBeforeCountDueToDataObjCreation();
        }

        goTo.homePage();
        contactListHelper.removeContact(ghandi);
        goTo.homePage();
        assertThat(contactListHelper.count(), equalTo(beforeCount-1));
        DataSet<ContactData> after = contactListHelper.all();

        assertThat(after, equalTo(before.without(ghandi)));
    }



}

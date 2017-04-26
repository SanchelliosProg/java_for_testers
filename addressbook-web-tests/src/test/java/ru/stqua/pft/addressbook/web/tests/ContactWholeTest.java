package ru.stqua.pft.addressbook.web.tests;


import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactPhoneNumbersState.*;
import static ru.stqua.pft.addressbook.web.appmanager.helpers.contact.PhotoStatus.*;

/**
 * Created by Александр on 23.04.2017.
 */
public class ContactWholeTest extends TestBase {
    @Test
    public void wholeDataTest(){
        goTo.homePage();
        ContactData gorby = ContactProvider.getContact(ContactsLabels.GORBY);
        contactListHelper.createContact(gorby);
        goTo.detailedContactPage(gorby);
        ContactData parsedGorby = contactDetailedScreenHelper.parseContactData(ALL_PHONES, WITHOUT_PHOTO);
        //Проверка имени и фамилии
        assertThat(gorby, equalTo(parsedGorby));
        //Проверка остальных данных
        assertThat(gorby.getAddress(), equalTo(parsedGorby.getAddress()));
        assertThat(gorby.getEmail(), equalTo(parsedGorby.getEmail()));
        assertThat(gorby.getHomePhone(), equalTo(parsedGorby.getHomePhone()));
        assertThat(gorby.getMobilePhone(), equalTo(parsedGorby.getMobilePhone()));
        assertThat(gorby.getWorkPhone(), equalTo(parsedGorby.getWorkPhone()));
    }

    @Test
    public void wholeDataFromEditPageTest(){
        goTo.homePage();
        ContactData gorby = ContactProvider.getContact(ContactsLabels.GORBY);
        contactListHelper.createContact(gorby);
        goTo.detailedContactPage(gorby);

        goTo.homePage();
        goTo.editPageOf(gorby);
        ContactData parsedGorby = contactHelper.parseEditPage();
        goTo.homePage();
        assertThat(gorby, equalTo(parsedGorby));
        assertThat(gorby.getAddress(), equalTo(parsedGorby.getAddress()));
        assertThat(gorby.getEmail(), equalTo(parsedGorby.getEmail()));
        assertThat(gorby.getHomePhone(), equalTo(parsedGorby.getHomePhone()));
        assertThat(gorby.getMobilePhone(), equalTo(parsedGorby.getMobilePhone()));
        assertThat(gorby.getWorkPhone(), equalTo(parsedGorby.getWorkPhone()));
    }
}

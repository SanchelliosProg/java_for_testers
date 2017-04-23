package ru.stqua.pft.addressbook.web.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.model.ContactData;
import ru.stqua.pft.addressbook.web.model.ContactProvider;
import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Александр on 23.04.2017.
 */
public class ContactWholeTest extends TestBase {
    @Test
    public void wholeDataTest(){
        goTo.homePage();
        ContactData gorby = ContactProvider.getContact(ContactsLabels.GORBY);
        recreateContact(gorby);
        goTo.homePage();
        contactListHelper.openDetailedInfoOfContactWithLastName(gorby.getLastName());
        ContactData parsedGorby = contactDetailedScreenHelper.parseContactData();
        //Проверка имени и фамилии
        assertThat(gorby, equalTo(parsedGorby));
        //Проверка остальных данных
        assertThat(gorby.getAddress(), equalTo(parsedGorby.getAddress()));
        assertThat(gorby.getEmail(), equalTo(parsedGorby.getEmail()));
        assertThat(gorby.getHomePhone(), equalTo(parsedGorby.getHomePhone()));
        assertThat(gorby.getMobilePhone(), equalTo(parsedGorby.getMobilePhone()));
        assertThat(gorby.getWorkPhone(), equalTo(parsedGorby.getWorkPhone()));
    }
}

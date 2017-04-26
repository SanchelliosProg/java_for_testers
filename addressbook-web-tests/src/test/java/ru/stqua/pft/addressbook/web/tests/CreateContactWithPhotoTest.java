package ru.stqua.pft.addressbook.web.tests;

import org.testng.annotations.Test;
import ru.stqua.pft.addressbook.web.appmanager.utils.RandomPhoneNumberProvider;
import ru.stqua.pft.addressbook.web.model.ContactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static ru.stqua.pft.addressbook.web.appmanager.helpers.contact.ContactPhoneNumbersState.*;
import static ru.stqua.pft.addressbook.web.appmanager.helpers.contact.PhotoStatus.*;

/**
 * Created by Александр on 25.04.2017.
 */
public class CreateContactWithPhotoTest extends TestBase {
    private String name = "Patrice";
    private String lastName = "Lumumba";
    private String address = "Kongo";
    private String email = "leninmakesafricafree@redpower.kg";
    private File photo = new File("src/test/resources/lumumba.jpg");

    @Test
    public void withPhoto(){
        goTo.homePage();
        ContactData contactData = ContactData.newBuilder().firstName(name).lastName(lastName).address(address)
                .homePhoneOnly(RandomPhoneNumberProvider.generateRandomNumber()).email(email).noGroup().photo(photo).build();
        contactListHelper.createContact(contactData);
        goTo.detailedContactPage(contactData);
        ContactData parsedData = contactDetailedScreenHelper.parseContactData(HOME_PHONE_ONLY, WITH_PHOTO);
        assertThat(contactData, equalTo(parsedData));
    }

}

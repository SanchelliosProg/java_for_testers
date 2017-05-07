package ru.stqua.pft.addressbook.web.appmanager.helpers.contact;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import ru.stqua.pft.addressbook.web.appmanager.helpers.BaseHelper;
import ru.stqua.pft.addressbook.web.model.ContactData;

/**
 * Created by Александр on 23.04.2017.
 */
public class ContactDetailedScreenHelper extends BaseHelper {


    private final String CONTENT_CSS = "div#content";
    private final String LINK_FIRST_GROUP = "div#content i a";

    public ContactDetailedScreenHelper(WebDriver driver) {
        super(driver);
    }

    public void clickFirstGroup(){
        click(LINK_FIRST_GROUP);
    }

    public ContactData parseContactData(ContactPhoneNumbersState state, PhotoStatus photoStatus) {
        String text = find(CONTENT_CSS).getText();
        String[] sections = text.split("\n");
        String[] name = sections[0].split(" ");
        String firstName = name[0];
        String lastName = name[1];
        String address;
        switch (photoStatus) {
            case WITH_PHOTO:
                address = sections[2];
                break;
            case WITHOUT_PHOTO:
                address = sections[1];
                break;
            default:
                address = "";
                break;
        }

        String homePhone;
        String mobilePhone;
        String workPhone;
        String email;

        switch (state) {
            case ALL_PHONES:
                homePhone = sections[3].replaceAll("H: ", "");
                mobilePhone = sections[4].replaceAll("M: ", "");
                workPhone = sections[5].replaceAll("W: ", "");
                email = sections[7];
                return ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                        .homePhone(homePhone).mobilePhone(mobilePhone).workPhone(workPhone).email(email).noGroup().noPhoto().build();
            case HOME_PHONE_ONLY:
                homePhone = sections[3].replaceAll("H: ", "");
                email = sections[4];
                return ContactData.newBuilder().firstName(firstName).lastName(lastName).address(address)
                        .homePhoneOnly(homePhone).email(email).noGroup().noPhoto().build();
            default:
                throw new InvalidArgumentException("такого статуса нет");

        }


    }
}

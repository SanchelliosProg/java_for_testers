package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.model.labels.ContactsLabels;
import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

import static ru.stqua.pft.addressbook.web.appmanager.utils.RandomPhoneNumberProvider.*;

/**
 * Created by Александр on 19.03.2017.
 */
public class ContactProvider {
    public static ContactData getContact(ContactsLabels address) {
        switch (address) {
            case FRODO_BAGGINS:
                return ContactData.newBuilder().firstName(ContactsLabels.FRODO_BAGGINS.getName())
                        .lastName("Baggins").address("Shire").homePhoneOnly(generateRandomNumber())
                        .email("frodoBaggins@midlemail.me").group(GroupLabels.BROTHERHOOD_OF_RING).build();
            case JOHN_MATRIX:
                return ContactData.newBuilder().firstName(ContactsLabels.JOHN_MATRIX.getName())
                        .lastName("Matrix").address("USA").homePhoneOnly(generateRandomNumber())
                        .email("john_matrix@besttimes.us").group(GroupLabels.COOL_ACTION_MOVIES).build();
            case CASEY_RAYBACK:
                return ContactData.newBuilder().firstName(ContactsLabels.CASEY_RAYBACK.getName())
                        .lastName("Rayback").address("USA")
                        .homePhoneOnly(generateRandomNumber())
                        .email("rayback@powerofliberty.us").group(GroupLabels.COOL_ACTION_MOVIES).build();
            case M_GHANDI:
                return ContactData.newBuilder().firstName(ContactsLabels.M_GHANDI.getName())
                        .lastName("Ghandi").address("India")
                        .homePhoneOnly(generateRandomNumber())
                        .email("ghandi@peace.in").group(GroupLabels.GOOD_PEOPLE).build();
            case MARTIN_RIGGS:
                return ContactData.newBuilder().firstName(ContactsLabels.MARTIN_RIGGS.getName())
                        .lastName("Riggs").address("LA")
                        .homePhone(generateRandomNumber())
                        .mobilePhone(generateRandomNumber())
                        .workPhone(generateRandomNumber())
                        .email("imnotsooldforthis@bang.us")
                        .group(GroupLabels.COOL_ACTION_MOVIES).build();
            case GORBY:
                return ContactData.newBuilder().firstName(ContactsLabels.GORBY.getName())
                        .lastName("Gorbachev").address("USSR")
                        .homePhone(generateRandomNumber())
                        .mobilePhone(generateRandomNumber())
                        .workPhone(generateRandomNumber())
                        .email("ivetriedmybest@sorry.su")
                        .noGroup().build();
            default:
                throw new IllegalArgumentException();
        }
    }
}

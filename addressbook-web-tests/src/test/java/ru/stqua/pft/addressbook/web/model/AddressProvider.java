package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Contacts;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressProvider {
    public static ContactData getAddress(Contacts address) {
        switch (address) {
            case FRODO_BAGGINS:
                return ContactData.newBuilder().firstName(Contacts.FRODO_BAGGINS.getName())
                        .lastName("Baggins").address("Shire").phone("01")
                        .email("frodoBaggins@midlemail.me").group(GroupLabels.BROTHERHOOD_OF_RING).build();
            case JOHN_MATRIX:
                return ContactData.newBuilder().firstName(Contacts.JOHN_MATRIX.getName())
                        .lastName("Matrix").address("USA").phone("02")
                        .email("john_matrix@besttimes.us").group(GroupLabels.COOL_ACTION_MOVIES).build();
            case CASEY_RAYBACK:
                return ContactData.newBuilder().firstName(Contacts.CASEY_RAYBACK.getName())
                        .lastName("Rayback").address("USA").phone("03")
                        .email("rayback@powerofliberty.us").group(GroupLabels.COOL_ACTION_MOVIES).build();
            case M_GHANDI:
                return ContactData.newBuilder().firstName(Contacts.M_GHANDI.getName())
                        .lastName("Ghandi").address("India").phone("04")
                        .email("ghandi@peace.in").group(GroupLabels.GOOD_PEOPLE).build();
            default:
                throw new IllegalArgumentException();
        }
    }
}

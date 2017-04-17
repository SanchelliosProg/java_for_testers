package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.GroupLabels;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressProvider {
    public static AddressData getAddress(Addresses address) {
        switch (address) {
            case FRODO_BAGGINS:
                return AddressData.newBuilder().firstName(Addresses.FRODO_BAGGINS.getName())
                        .lastName("Baggins").address("Shire").phone("01")
                        .email("frodoBaggins@midlemail.me").group(GroupLabels.BROTHERHOOD_OF_RING).build();
            case JOHN_MATRIX:
                return AddressData.newBuilder().firstName(Addresses.JOHN_MATRIX.getName())
                        .lastName("Matrix").address("USA").phone("02")
                        .email("john_matrix@besttimes.us").group(GroupLabels.COOL_ACTION_MOVIES).build();
            case CASEY_RAYBACK:
                return AddressData.newBuilder().firstName(Addresses.CASEY_RAYBACK.getName())
                        .lastName("Rayback").address("USA").phone("03")
                        .email("rayback@powerofliberty.us").group(GroupLabels.COOL_ACTION_MOVIES).build();
            case M_GHANDI:
                return AddressData.newBuilder().firstName(Addresses.M_GHANDI.getName())
                        .lastName("Ghandi").address("India").phone("04")
                        .email("ghandi@peace.in").group(GroupLabels.GOOD_PEOPLE).build();
            default:
                throw new IllegalArgumentException();
        }
    }
}

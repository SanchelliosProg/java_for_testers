package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.appmanager.helpers.address.Addresses;
import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressProvider {
    public static AddressData getAddress(Addresses address) {
        switch (address) {
            case FRODO_BAGGINS:
                return AddressData.newBuilder().firstName("Frodo")
                        .lastName("Baggins").address("Shire").phone("01")
                        .email("frodoBaggins@midlemail.me").group(Groups.BROTHERHOOD_OF_RING).build();

            default:
                throw new IllegalArgumentException();
        }
    }
}

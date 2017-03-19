package ru.stqua.pft.addressbook.web.tests.models;

import ru.stqua.pft.addressbook.web.tests.models.AddressData;
import ru.stqua.pft.addressbook.web.tests.utils.address.Addresses;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

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

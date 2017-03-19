package ru.stqua.pft.addressbook.web.tests.utils.address;

/**
 * Created by Александр on 19.03.2017.
 */
public class AddressProvider {
    public static AddressData getAddress(Addresses address) {
        switch (address) {
            case FRODO_BAGGINS:
                return AddressData.newBuilder()
                        .firstName("Frodo")
                        .lastName("Baggins").photo("frodo_baggins.jpg")
                        .address("Shire")
                        .phone("01")
                        .email("frodo@middlearth.md")
                        .build();
            default:
                throw new IllegalArgumentException();
        }
    }
}

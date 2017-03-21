package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

/**
 * Created by Александр on 19.03.2017.
 */
public enum Addresses {
    FRODO_BAGGINS ("Frodo"),
    JOHN_MATRIX ("John"),
    CASEY_RAYBACK ("Casey");

    private String name;

    Addresses(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

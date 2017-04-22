package ru.stqua.pft.addressbook.web.appmanager.helpers.address;

/**
 * Created by Александр on 19.03.2017.
 */
public enum Contacts {
    FRODO_BAGGINS ("Frodo"),
    JOHN_MATRIX ("John"),
    CASEY_RAYBACK ("Casey"),
    M_GHANDI("Mahatma"),
    MARTIN_RIGGS("Martin");


    private String name;

    Contacts(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

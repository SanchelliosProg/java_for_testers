package ru.stqua.pft.addressbook.web.model.labels;

/**
 * Created by Александр on 19.03.2017.
 */
public enum ContactsLabels {
    FRODO_BAGGINS ("Frodo"),
    JOHN_MATRIX ("John"),
    CASEY_RAYBACK ("Casey"),
    M_GHANDI("Mahatma"),
    MARTIN_RIGGS("Martin"),
    GORBY("Mikhail");


    private String name;

    ContactsLabels(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

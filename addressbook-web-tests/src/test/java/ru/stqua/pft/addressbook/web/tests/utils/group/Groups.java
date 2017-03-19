package ru.stqua.pft.addressbook.web.tests.utils.group;

/**
 * Created by Александр on 18.03.2017.
 */
public enum Groups {
    BROTHERHOOD_OF_RING("Братство кольца");

    private String name;

    Groups(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package ru.stqua.pft.addressbook.web.model.labels;

/**
 * Created by Александр on 18.03.2017.
 */
public enum GroupLabels {
    BROTHERHOOD_OF_RING("Братство кольца"),
    FAIRY_WORLDS("Волшебные миры"),
    COOL_ACTION_MOVIES("Крутые боевики"),
    GOOD_PEOPLE("Замечательные люди"),
    DEFAULT_GROUP("Default group"),
    DUPLICATE_GROUP("Дубликаты");

    private String name;

    GroupLabels(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

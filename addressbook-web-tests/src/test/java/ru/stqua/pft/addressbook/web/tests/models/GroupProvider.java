package ru.stqua.pft.addressbook.web.tests.models;

import ru.stqua.pft.addressbook.web.tests.models.GroupData;
import ru.stqua.pft.addressbook.web.tests.utils.group.Groups;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupProvider {
    public static GroupData get(Groups group) {
        switch (group) {
            case BROTHERHOOD_OF_RING:
                return new GroupData("Братство кольца",
                        "Герои Властелина Колец",
                        "Книжка и фильм просто замечательны");
            default:
                throw new IllegalArgumentException();
        }

    }
}

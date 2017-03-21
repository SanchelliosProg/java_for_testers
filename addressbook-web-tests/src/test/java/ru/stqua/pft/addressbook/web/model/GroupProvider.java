package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupProvider {
    public static GroupData get(Groups group) {
        switch (group) {
            case BROTHERHOOD_OF_RING:
                return new GroupData(Groups.BROTHERHOOD_OF_RING.getName(),
                        "Герои Властелина Колец",
                        "Книжка и фильм просто замечательны");
            case FAIRY_WORLDS:
                return new GroupData(Groups.FAIRY_WORLDS.getName(),
                        "Просто, надо было придумать что-то",
                        "Ну, вот, я и придумал");
            case COOL_ACTION_MOVIES:
                return new GroupData(Groups.COOL_ACTION_MOVIES.getName(),
                        "Боевички",
                        "С гнусавым голосом");
            case GOOD_PEOPLE:
                return new GroupData(Groups.GOOD_PEOPLE.getName(),
                        "Замечательные люди",
                        "О таких книжки пишут");
            default:
                throw new IllegalArgumentException();
        }
    }
}

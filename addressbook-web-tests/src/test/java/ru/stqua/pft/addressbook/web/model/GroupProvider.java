package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.appmanager.helpers.group.Groups;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupProvider {
    public static GroupData get(Groups group) {
        switch (group) {
            case BROTHERHOOD_OF_RING:
                return new GroupData()
                        .withName(Groups.BROTHERHOOD_OF_RING.getName())
                        .withHeader("Герои Властелина Колец")
                        .withFooter("Книжка и фильм просто замечательны");
            case FAIRY_WORLDS:
                return new GroupData().withName(Groups.FAIRY_WORLDS.getName())
                        .withHeader("Просто, надо было придумать что-то")
                        .withFooter("Ну, вот, я и придумал");
            case COOL_ACTION_MOVIES:
                return new GroupData().withName(Groups.COOL_ACTION_MOVIES.getName())
                        .withHeader("Боевички")
                        .withFooter("С гнусавым голосом");
            case GOOD_PEOPLE:
                return new GroupData().withName(Groups.GOOD_PEOPLE.getName())
                        .withHeader("Замечательные люди")
                        .withFooter("О таких книжки пишут");
            case DEFAULT_GROUP:
                return new GroupData().withName(Groups.DEFAULT_GROUP.getName())
                        .withHeader("Default boring stuff")
                        .withFooter("Default boring stuff again");
            case DUPLICATE_GROUP:
                return new GroupData().withName(Groups.DUPLICATE_GROUP.getName())
                        .withHeader("Одно и то же...")
                        .withFooter("Везде...");
            default:
                throw new IllegalArgumentException();
        }
    }
}

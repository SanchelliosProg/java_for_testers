package ru.stqua.pft.addressbook.web.model;

import ru.stqua.pft.addressbook.web.model.labels.GroupLabels;

/**
 * Created by Александр on 18.03.2017.
 */
public class GroupProvider {
    public static GroupData get(GroupLabels group) {
        switch (group) {
            case BROTHERHOOD_OF_RING:
                return new GroupData()
                        .withName(GroupLabels.BROTHERHOOD_OF_RING.getName())
                        .withHeader("Герои Властелина Колец")
                        .withFooter("Книжка и фильм просто замечательны");
            case FAIRY_WORLDS:
                return new GroupData().withName(GroupLabels.FAIRY_WORLDS.getName())
                        .withHeader("Просто, надо было придумать что-то")
                        .withFooter("Ну, вот, я и придумал");
            case COOL_ACTION_MOVIES:
                return new GroupData().withName(GroupLabels.COOL_ACTION_MOVIES.getName())
                        .withHeader("Боевички")
                        .withFooter("С гнусавым голосом");
            case GOOD_PEOPLE:
                return new GroupData().withName(GroupLabels.GOOD_PEOPLE.getName())
                        .withHeader("Замечательные люди")
                        .withFooter("О таких книжки пишут");
            case DEFAULT_GROUP:
                return new GroupData().withName(GroupLabels.DEFAULT_GROUP.getName())
                        .withHeader("Default boring stuff")
                        .withFooter("Default boring stuff again");
            case DUPLICATE_GROUP:
                return new GroupData().withName(GroupLabels.DUPLICATE_GROUP.getName())
                        .withHeader("Одно и то же...")
                        .withFooter("Везде...");
            default:
                throw new IllegalArgumentException();
        }
    }
}

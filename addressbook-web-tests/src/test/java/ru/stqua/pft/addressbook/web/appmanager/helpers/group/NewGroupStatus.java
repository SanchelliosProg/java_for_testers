package ru.stqua.pft.addressbook.web.appmanager.helpers.group;

import ru.stqua.pft.addressbook.web.model.GroupData;

/**
 * Created by Александр on 13.04.2017.
 */
public class NewGroupStatus {
    private boolean isCreated;
    private GroupData data;

    public NewGroupStatus(boolean isCreated, GroupData data) {
        this.isCreated = isCreated;
        this.data = data;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public GroupData getData() {
        return data;
    }

    public void setData(GroupData data) {
        this.data = data;
    }
}

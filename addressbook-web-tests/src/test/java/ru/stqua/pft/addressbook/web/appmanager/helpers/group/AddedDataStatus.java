package ru.stqua.pft.addressbook.web.appmanager.helpers.group;

import ru.stqua.pft.addressbook.web.model.GroupData;

/**
 * Created by Александр on 13.04.2017.
 */
public class AddedDataStatus<T> {
    private boolean isCreated;
    private T data;

    public AddedDataStatus(boolean isCreated, T data) {
        this.isCreated = isCreated;
        this.data = data;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public T data() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

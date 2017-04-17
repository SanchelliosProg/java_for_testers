package ru.stqua.pft.addressbook.web.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Александр on 16.04.2017.
 */
public class DataSet<T> extends ForwardingSet<T>{

    private Set<T> delegate;

    public DataSet(DataSet dataSet) {
        this.delegate = new HashSet<>(dataSet.delegate);
    }

    public DataSet() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<T> delegate() {
        return delegate;
    }

    public DataSet withAdded(T data){
        DataSet dataSet = new DataSet(this);
        dataSet.add(data);
        return dataSet;
    }

    public DataSet without(T data){
        DataSet dataSet = new DataSet(this);
        dataSet.remove(data);
        return dataSet;
    }
}

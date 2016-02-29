package de.pheru.fx.mvp;

import javafx.collections.ObservableList;

/**
 * Created by Philipp on 28.02.2016.
 */
public class ObservableListWrapper<E> {

    private ObservableList<E> list;

    public ObservableList<E> getList() {
        return list;
    }

    public void setList(ObservableList<E> list) {
        this.list = list;
    }
}

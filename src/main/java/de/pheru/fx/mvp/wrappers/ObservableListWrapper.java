package de.pheru.fx.mvp.wrappers;

import javafx.collections.ObservableList;

public class ObservableListWrapper<E> {

    private ObservableList<E> list;

    public ObservableList<E> getList() {
        return list;
    }

    public void setList(ObservableList<E> list) {
        this.list = list;
    }
}

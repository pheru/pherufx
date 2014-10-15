/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Phili_000
 */
public class TestData {
    
    private final IntegerProperty value = new SimpleIntegerProperty(0);

    public Integer getValue() {
        return value.get();
    }

    public void setValue(final Integer value) {
        this.value.set(value);
    }

    public IntegerProperty valueProperty() {
        return value;
    }
    
}

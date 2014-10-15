/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.gui.right;

import de.eru.pherufx.testproject.cdi.TestEvent;
import de.eru.pherufx.testproject.cdi.TestQualifier;
import de.eru.pherufx.testproject.data.TestData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author Phili_000
 */
public class RightPresenter {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    
    @Inject
    @TestQualifier(dataType = TestQualifier.DataType.ONE)
    private TestData data1;
    @Inject
    @TestQualifier(dataType = TestQualifier.DataType.TWO)
    private TestData data2;
    @Inject
    @TestQualifier(dataType = TestQualifier.DataType.THREE)
    private TestData data3;
    
    @Inject
    private Event<TestEvent> testEvent;
    
    @FXML
    private void buttonOneAction(){
        data1.setValue(data1.getValue() + 1);
    }
    @FXML
    private void buttonTwoAction(){
        data2.setValue(data2.getValue() + 1);
        testEvent.fire(new TestEvent());
    }
    @FXML
    private void buttonThreeAction(){
        data3.setValue(data3.getValue() + 1);
    }
    
    public void showHideButtons(){
        button1.setVisible(!button1.isVisible());
        button2.setVisible(!button2.isVisible());
        button3.setVisible(!button3.isVisible());
    }
}

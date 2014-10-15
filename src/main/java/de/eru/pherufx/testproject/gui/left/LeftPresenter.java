/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.gui.left;

import de.eru.pherufx.testproject.cdi.TestQualifier;
import de.eru.pherufx.testproject.data.TestData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.inject.Inject;

/**
 *
 * @author Phili_000
 */
public class LeftPresenter implements Initializable{

    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    
    
    @Inject
    @TestQualifier(dataType = TestQualifier.DataType.ONE)
    private TestData data1;
    @Inject
    @TestQualifier(dataType = TestQualifier.DataType.TWO)
    private TestData data2;
    @Inject
    @TestQualifier(dataType = TestQualifier.DataType.THREE)
    private TestData data3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label1.textProperty().bind(data1.valueProperty().asString());
        label2.textProperty().bind(data2.valueProperty().asString());
        label3.textProperty().bind(data3.valueProperty().asString());
    }
    
}

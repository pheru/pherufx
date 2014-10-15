/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.test;

import de.eru.pherufx.testproject.Eins;
import de.eru.pherufx.testproject.TestInjection;
import de.eru.pherufx.testproject.Zwei;
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
public class TestPresenter implements Initializable {

    @FXML
    private Label label1;
    @FXML
    private Label label2;

    @Inject
    @Eins
    TestInjection ti;
    @Inject
    @Zwei
    TestInjection ti2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Eins: " + ti.getI() + "\t Zwei: " + ti2.getI());
        ti.setI(3);
        System.out.println("Eins: " + ti.getI() + "\t Zwei: " + ti2.getI());
        label1.setText("Wuhuuuuuuu!");
    }

}

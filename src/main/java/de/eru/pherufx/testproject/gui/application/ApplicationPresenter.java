/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.gui.application;

import de.eru.pherufx.testproject.gui.left.LeftView;
import de.eru.pherufx.testproject.gui.right.RightPresenter;
import de.eru.pherufx.testproject.gui.right.RightView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javax.inject.Inject;

/**
 *
 * @author Phili_000
 */
public class ApplicationPresenter implements Initializable{
 
    @FXML
    private SplitPane root;
    @FXML
    private Label label;
    
    @Inject
    private LeftView leftView; //TODO Als lokale Variable möglich?
    @Inject
    private RightView rightView;
    private RightPresenter rightPresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getItems().add(leftView.getView());
        root.getItems().add(rightView.getView());
        rightPresenter = (RightPresenter) rightView.getPresenter();
    }
    
    @FXML
    private void buttonAction(){
        rightPresenter.showHideButtons();
    }
    
//    private void eventFired(@Observes TestEvent event){
//        label.setText("CDI-Event fired!");
//    }
}

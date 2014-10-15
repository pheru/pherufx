/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject;

import de.eru.pherufx.cdi.StartApplication;
import de.eru.pherufx.testproject.test.TestView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author Phili_000
 */
public class Starter {
    
    @Inject
    private TestView view;
    
    public void launchJavaFXApplication(@Observes @StartApplication Stage s) {
        s.setScene(new Scene(view.getView(), 500, 500));
        s.show();
    }
}

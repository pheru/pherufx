/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.eru.pherufx.testproject.gui.application;

import de.eru.pherufx.gui.JavaFXView;
import de.eru.pherufx.testproject.cdi.TestEvent;
import javax.enterprise.event.Observes;

/**
 *
 * @author Phili_000
 */
public class ApplicationView extends JavaFXView{
    private void eventFired(@Observes TestEvent event){
        System.out.println("CDI-Event Fired");
    }
}

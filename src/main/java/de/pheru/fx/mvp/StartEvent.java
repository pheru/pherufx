package de.pheru.fx.mvp;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.stage.Stage;

/**
 *
 * @author Philipp Bruckner
 */
public class StartEvent extends Event {

    public static final EventType<StartEvent> TYPE = new EventType<>("PheruInitialize");

    private Stage primaryStage;

    public StartEvent() {
        super(TYPE);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

package de.pheru.fx.mvp;

import javafx.stage.Stage;

public interface PheruFXEntryPoint {

    void start(final Stage primaryStage) throws Exception;

    default void stop() throws Exception {
        // empty by default
    }
}

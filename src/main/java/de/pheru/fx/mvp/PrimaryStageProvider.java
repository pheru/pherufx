package de.pheru.fx.mvp;

import javafx.application.Application.Parameters;
import javafx.stage.Stage;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

/**
 *
 * @author Philipp Bruckner
 */
@Singleton
public class PrimaryStageProvider {

    private Stage primaryStage;

    void setPrimaryStage(Stage p) {
        this.primaryStage = p;
    }

    @Produces
    @PrimaryStage
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

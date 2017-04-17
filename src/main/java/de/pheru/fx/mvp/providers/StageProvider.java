package de.pheru.fx.mvp.providers;

import de.pheru.fx.mvp.qualifiers.PrimaryStage;
import javafx.stage.Stage;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class StageProvider {

    private Stage primaryStage;

    public void setPrimaryStage(final Stage stage) {
        this.primaryStage = stage;
    }

    @Produces
    @PrimaryStage
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}

package de.pheru.fx.mvp.factories;

import de.pheru.fx.mvp.qualifiers.ApplicationStylesheets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.inject.Inject;
import java.util.List;

public class StageFactory {

    @Inject
    @ApplicationStylesheets
    private List<String> applicationStylesheets;

    public Stage createStage() {
        final Stage stage = new Stage();
        stage.sceneProperty().addListener((observable, oldValue, newValue) -> newValue.getStylesheets().addAll(applicationStylesheets));
        return stage;
    }

    public Stage createStage(final StageStyle stagestyle) {
        final Stage stage = createStage();
        stage.initStyle(stagestyle);
        return stage;
    }
}

package de.pheru.fx.mvp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.enterprise.util.AnnotationLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author Philipp Bruckner
 */
public class PheruFXApplication extends Application {

    private static WeldContainer weldContainer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        beforeStart();

        //Damit wird sichergestellt, dass auch alle JavaFX-Aktivitäten in beforeStart() abgeschlossen sind
        Platform.runLater(() -> {
            weldContainer = new Weld().initialize();
            weldContainer.instance().select(ApplicationParametersProvider.class).get().setParameters(getParameters());
            weldContainer.instance().select(PrimaryStageProvider.class).get().setPrimaryStage(primaryStage);
            weldContainer.event().select(Stage.class, new AnnotationLiteral<StartApplication>() {
                }).fire(primaryStage);

            startFinished();
        });
    }

    public void beforeStart() {
        //empty
    }

    public void startFinished() {
        //empty
    }

    protected static WeldContainer getWeldContainer() {
        return weldContainer;
    }
}

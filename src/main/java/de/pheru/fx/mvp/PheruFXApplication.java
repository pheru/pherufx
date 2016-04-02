package de.pheru.fx.mvp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author Philipp Bruckner
 */
public class PheruFXApplication extends Application {

    private static WeldContainer weldContainer;

    @Override
    public void start(Stage primaryStage) throws Exception {
        beforeStart();
        new Thread(() -> {
            weldContainer = new Weld().initialize();
            weldContainer.instance().select(ApplicationParametersProvider.class).get().setParameters(getParameters());
            weldContainer.instance().select(PrimaryStageProvider.class).get().setPrimaryStage(primaryStage);
            load();
            Platform.runLater(() -> {
                weldContainer.event().select(Stage.class, new AnnotationLiteral<StartApplication>() {
                }).fire(primaryStage);
                afterStart();
            });
        }).start();
    }

    /**
     * This method is called immediately at the beginning of the {@link #start(Stage)} method.
     * The default implementation does nothing.
     * <p>
     * This method is called on the JavaFX Application Thread.
     */
    public void beforeStart() {
        //empty by default
    }

    /**
     * This method is called after the weld-container is initialized.
     * The default implementation does nothing.
     * <p>
     * This method is NOT called on the JavaFX Application Thread.
     */
    public void load() {
        //empty by default
    }

    /**
     * This method is called after the {@link StartApplication} event is fired.
     * The default implementation does nothing.
     * <p>
     * This method is called on the JavaFX Application Thread.
     */
    public void afterStart() {
        //empty by default
    }

    protected static WeldContainer getWeldContainer() {
        return weldContainer;
    }
}

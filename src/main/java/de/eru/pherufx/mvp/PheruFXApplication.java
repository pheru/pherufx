package de.eru.pherufx.mvp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javax.enterprise.util.AnnotationLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author Philipp Bruckner
 */
public class PheruFXApplication extends Application {

    private static EventHandler<StartEvent> startingHandler;
    private static EventHandler<StartEvent> startFinishedHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StartEvent startEvent = new StartEvent();
        startEvent.setPrimaryStage(primaryStage);
        if (startingHandler != null) {
            startingHandler.handle(startEvent);
        }
        WeldContainer weldContainer = new Weld().initialize();
        weldContainer.instance().select(ApplicationParametersProvider.class).get().setParameters(getParameters());
        weldContainer.event().select(Stage.class, new AnnotationLiteral<StartApplication>() {
        }).fire(primaryStage);
        if (startFinishedHandler != null) {
            startFinishedHandler.handle(startEvent);
        }
    }

    public static EventHandler<StartEvent> getOnStarting() {
        return startingHandler;
    }

    public static void setOnStarting(EventHandler<StartEvent> initializeHandler) {
        PheruFXApplication.startingHandler = initializeHandler;
    }

    public static EventHandler<StartEvent> getOnStartFinished() {
        return startFinishedHandler;
    }

    public static void setOnStartFinished(EventHandler<StartEvent> startFinishedHandler) {
        PheruFXApplication.startFinishedHandler = startFinishedHandler;
    }

}

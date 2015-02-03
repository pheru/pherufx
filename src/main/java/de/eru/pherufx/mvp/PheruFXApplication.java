package de.eru.pherufx.mvp;

import javafx.application.Application;
import javafx.stage.Stage;
import javax.enterprise.util.AnnotationLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 *
 * @author Philipp Bruckner
 */
public class PheruFXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WeldContainer weldContainer = new Weld().initialize();
        weldContainer.instance().select(ApplicationParametersProvider.class).get().setParameters(getParameters());
        weldContainer.event().select(Stage.class, new AnnotationLiteral<StartApplication>() {
        }).fire(primaryStage);
    }

}

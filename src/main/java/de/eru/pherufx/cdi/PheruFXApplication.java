package de.eru.pherufx.cdi;

import de.eru.pherufx.testproject.Main;
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
        // Let's initialize CDI/Weld.
        WeldContainer weldContainer = new Weld().initialize();
// Make the application parameters injectable with a standard CDI
// annotation
        weldContainer.instance().select(ApplicationParametersProvider.class).get().setParameters(getParameters());
// Now that JavaFX thread is ready
// let's inform whoever cares using standard CDI notification mechanism:
// CDI events
        weldContainer.event().select(Stage.class, new AnnotationLiteral<StartApplication>() {
        }).fire(primaryStage);
    }
    
}

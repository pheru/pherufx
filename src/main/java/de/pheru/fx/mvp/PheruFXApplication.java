package de.pheru.fx.mvp;

import de.pheru.fx.mvp.annotations.SetStylesheetAsUserAgentStylesheet;
import de.pheru.fx.mvp.providers.StylesheetProvider;
import de.pheru.fx.mvp.qualifiers.StartApplication;
import de.pheru.fx.mvp.exceptions.ApplicationInitializationException;
import de.pheru.fx.mvp.providers.ParametersProvider;
import de.pheru.fx.mvp.providers.StageProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.util.AnnotationLiteral;
import java.util.ArrayList;
import java.util.List;

public class PheruFXApplication extends Application {

    private WeldContainer weldContainer;

    @Override
    public void start(final Stage primaryStage) throws Exception {
        beforeStart();
        new Thread(() -> {
            final List<String> stylesheets = loadStylesheets();
            weldContainer = new Weld().initialize();
            weldContainer.instance().select(ParametersProvider.class).get().setApplicationParameters(getParameters());
            weldContainer.instance().select(StageProvider.class).get().setPrimaryStage(primaryStage);
            weldContainer.instance().select(StylesheetProvider.class).get().setGlobalStylesheets(stylesheets);
            load();
            Platform.runLater(() -> {
                weldContainer.event().select(Stage.class, new AnnotationLiteral<StartApplication>() {
                }).fire(primaryStage);
                afterStart();
            });
        }).start();
    }

    private List<String> loadStylesheets() {
        final List<String> stylesheets = new ArrayList<>();

        final String[] additionalStylesheets = CssUtil.getAdditionalStylesheetsByAnnotation(getClass());
        for (final String additionalStylesheet : additionalStylesheets) {
            final String stylesheet = CssUtil.getStylesheet(getClass(), additionalStylesheet);
            if (stylesheet == null) {
                throw new ApplicationInitializationException("No stylesheet found for \"" + additionalStylesheet + "\"!");
            }
            stylesheets.add(stylesheet);
        }

        final String stylesheet = CssUtil.getStylesheet(getClass(), getClass().getSimpleName());
        final SetStylesheetAsUserAgentStylesheet setStylesheetAsUserAgentStylesheet = getClass().getAnnotation(SetStylesheetAsUserAgentStylesheet.class);
        if (setStylesheetAsUserAgentStylesheet != null) {
            if (stylesheet == null) {
                throw new ApplicationInitializationException("Failed so set user agent stylesheet: No stylesheet found for \"" + getClass().getSimpleName() + "\"!");
            }
            setUserAgentStylesheet(stylesheet);
        } else {
            if (stylesheet != null) {
                stylesheets.add(stylesheet);
            }
        }
        return stylesheets;
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
     * This method is called after the weld-container has been initialized.
     * The default implementation does nothing.
     * <p>
     * This method is NOT called on the JavaFX Application Thread.
     */
    public void load() {
        //empty by default
    }

    /**
     * This method is called after the {@link StartApplication} event has been fired.
     * The default implementation does nothing.
     * <p>
     * This method is called on the JavaFX Application Thread.
     */
    public void afterStart() {
        //empty by default
    }

    protected WeldContainer getWeldContainer() {
        return weldContainer;
    }
}

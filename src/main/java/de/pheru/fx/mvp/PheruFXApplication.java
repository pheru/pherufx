package de.pheru.fx.mvp;

import de.pheru.fx.mvp.providers.ParametersProvider;
import de.pheru.fx.mvp.providers.StageProvider;
import de.pheru.fx.mvp.providers.StylesheetProvider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.List;

public abstract class PheruFXApplication extends Application {

    protected abstract Class<? extends PheruFXEntryPoint> getEntryPointClass();

    private WeldContainer weldContainer;
    private PheruFXEntryPoint entryPoint;

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final List<String> stylesheets = CssUtil.loadStylesheets(getClass(), getClass().getSimpleName().toLowerCase());
        final Stage splashStage = createSplashStage();

        if (splashStage != null) {
            if (splashStage.getScene() != null) {
                splashStage.getScene().getStylesheets().addAll(stylesheets);
            }
            splashStage.show();
        }

        final Thread thread = new Thread(() -> {
            weldContainer = new Weld().initialize();
            weldContainer.instance().select(ParametersProvider.class).get().setApplicationParameters(getParameters());
            weldContainer.instance().select(StageProvider.class).get().setPrimaryStage(primaryStage);
            weldContainer.instance().select(StylesheetProvider.class).get().setApplicationStylesheets(stylesheets);

            final Class<? extends PheruFXLoader> loaderClass = getLoaderClass();
            if (loaderClass != null) {
                final PheruFXLoader loader = weldContainer.instance().select(loaderClass).get();
                if (splashStage instanceof UpdateableSplashStage) {
                    loader.setUpdatableSplashStage((UpdateableSplashStage) splashStage);
                }
                try {
                    loader.load();
                } catch (final Exception e) {
                    Platform.exit();
                    throw new RuntimeException("Exception in PheruFXLoader load method", e);
                }
            }
            entryPoint = weldContainer.instance().select(getEntryPointClass()).get();
            Platform.runLater(() -> {
                try {
                    entryPoint.start(primaryStage);
                } catch (final Exception e) {
                    Platform.exit();
                    throw new RuntimeException("Exception in PheruFXEntryPoint start method", e);
                }
                if (splashStage != null) {
                    splashStage.hide();
                }
            });
        }, "PheruFX Starter Thread");
        thread.start();
    }

    @Override
    public final void stop() throws Exception {
        try {
            entryPoint.stop();
        } finally {
            if (weldContainer != null) {
                weldContainer.shutdown();
            }
        }
    }

    /**
     * The default implementation provided by the PheruFXApplication class returns null.
     */
    protected Class<? extends PheruFXLoader> getLoaderClass() {
        return null;
    }

    /**
     * The default implementation provided by the PheruFXApplication class returns null.
     */
    protected Stage createSplashStage() {
        return null;
    }

    protected WeldContainer getWeldContainer() {
        return weldContainer;
    }
}

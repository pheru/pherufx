package de.pheru.fx.mvp;

import de.pheru.fx.mvp.exceptions.ApplicationInitializationException;
import javafx.application.Platform;

public abstract class PheruFXLoader {

    private UpdateableSplashStage splashStage;

    public abstract void load() throws Exception;

    protected void updateMessage(final String message) {
        checkIfSplashStageIsUpdateable();
        Platform.runLater(() -> splashStage.loadingMessageUpdated(message));
    }

    protected void updateProgress(final double workDone, final double max) {
        checkIfSplashStageIsUpdateable();
        Platform.runLater(() -> splashStage.loadingProgressUpdated(workDone, max));
    }

    protected void fail(final String message, final Throwable throwable) {
        checkIfSplashStageIsUpdateable();
        Platform.runLater(() -> splashStage.loadingFailed(message, throwable));
    }

    private void checkIfSplashStageIsUpdateable() {
        if (splashStage == null) {
            throw new ApplicationInitializationException("Updating SplashStage failed. " +
                    "Make sure to provide a Stage implementing the UpdateableSplashStage interface " +
                    "by overriding PheruFXApplication#createSplashStage.");
        }
    }

    void setUpdatableSplashStage(final UpdateableSplashStage updateableSplashStage) {
        this.splashStage = updateableSplashStage;
    }
}

package de.pheru.fx.mvp;

import de.pheru.fx.mvp.exceptions.ApplicationInitializationException;
import javafx.application.Platform;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public abstract class PheruFXLoader {

    private UpdateableSplashStage splashStage;

    public abstract void load() throws Exception;

    protected void updateMessage(final String message) {
        callBlockingUpdateFunction(() -> splashStage.loadingMessageUpdated(message));
    }

    protected void updateProgress(final double workDone, final double max) {
        callBlockingUpdateFunction(() -> splashStage.loadingProgressUpdated(workDone, max));
    }

    protected void fail(final String message, final Throwable throwable) {
        callBlockingUpdateFunction(() -> splashStage.loadingFailed(message, throwable));
    }

    private void callBlockingUpdateFunction(final Runnable runnable) {
        checkIfSplashStageIsUpdateable();
        final FutureTask<Void> futureTask = new FutureTask<>(() -> {
            runnable.run();
            return null;
        });
        Platform.runLater(futureTask);
        try {
            futureTask.get();
        } catch (final InterruptedException | ExecutionException ignored) {
            // Nothing to do
        }
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

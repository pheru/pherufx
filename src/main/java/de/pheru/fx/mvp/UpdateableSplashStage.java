package de.pheru.fx.mvp;

public interface UpdateableSplashStage {

    void loadingMessageUpdated(final String message);

    void loadingProgressUpdated(final double workDone, final double max);

    void loadingFailed(final String message, final Throwable throwable);
}

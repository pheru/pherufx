package de.pheru.fx.mvp.providers;

import de.pheru.fx.mvp.qualifiers.ApplicationParameters;
import javafx.application.Application.Parameters;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ParametersProvider {

    private Parameters applicationParameters;

    public void setApplicationParameters(final Parameters parameters) {
        this.applicationParameters = parameters;
    }

    @Produces
    @ApplicationParameters
    public Parameters getApplicationParameters() {
        return applicationParameters;
    }
}

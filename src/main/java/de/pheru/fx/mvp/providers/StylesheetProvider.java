package de.pheru.fx.mvp.providers;

import de.pheru.fx.mvp.qualifiers.ApplicationStylesheets;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class StylesheetProvider {

    private List<String> applicationStylesheets;

    public void setApplicationStylesheets(final List<String> applicationStylesheets) {
        this.applicationStylesheets = applicationStylesheets;
    }

    @Produces
    @ApplicationStylesheets
    public List<String> getApplicationStylesheets() {
        return applicationStylesheets;
    }

}

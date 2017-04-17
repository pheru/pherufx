package de.pheru.fx.mvp.providers;

import de.pheru.fx.mvp.qualifiers.GlobalStylesheets;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class StylesheetProvider {

    private List<String> globalStylesheets;

    public void setGlobalStylesheets(final List<String> globalStylesheets) {
        this.globalStylesheets = globalStylesheets;
    }

    @Produces
    @GlobalStylesheets
    public List<String> getGlobalStylesheets() {
        return globalStylesheets;
    }

}

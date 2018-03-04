package de.pheru.fx.mvp;

import de.pheru.fx.mvp.exceptions.ViewInitializationException;
import de.pheru.fx.mvp.qualifiers.ApplicationStylesheets;
import de.pheru.fx.mvp.qualifiers.PheruFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class PheruFXView<P> {

    private static final String VIEW_ENDING = "view";

    @Inject
    @PheruFXMLLoader
    private FXMLLoader loader;
    @Inject
    @ApplicationStylesheets
    private List<String> applicationStylesheets;

    @PostConstruct
    private void init() {
        try {
            loader.setLocation(getClass().getResource(getViewName() + ".fxml"));
            addResourceBundle();
            loader.load();
            addCSS();
        } catch (final IllegalStateException | IOException e) {
            throw new ViewInitializationException("Could not initialize " + getClass().getName() + ": " + e.getMessage(), e);
        }
    }

    public Parent getView() {
        return loader.getRoot();
    }

    public P getPresenter() {
        return loader.getController();
    }

    private void addResourceBundle() {
        try {
            final ResourceBundle resources = ResourceBundle.getBundle(getClass().getPackage().getName() + "." + getViewName());
            loader.setResources(resources);
        } catch (final MissingResourceException e) {
            // do nothing
        }
    }

    private void addCSS() {
        final Parent parent = loader.getRoot();
        parent.getStylesheets().addAll(applicationStylesheets);
        parent.getStylesheets().addAll(CssUtil.loadStylesheets(getClass(), getViewName()));
    }

    private String getViewName() {
        String viewName = getClass().getSimpleName().toLowerCase();
        viewName = viewName.replace(VIEW_ENDING, "");
        return viewName;
    }

}

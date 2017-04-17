package de.pheru.fx.mvp;

import de.pheru.fx.mvp.exceptions.ViewInitializationException;
import de.pheru.fx.mvp.qualifiers.GlobalStylesheets;
import de.pheru.fx.mvp.qualifiers.PheruFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class PheruFXView {

    private static final String VIEW_ENDING = "view";

    @Inject
    @PheruFXMLLoader
    private FXMLLoader loader;
    @Inject
    @GlobalStylesheets
    private List<String> globalStylesheets;

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
        parent.getStylesheets().addAll(globalStylesheets);

        final String[] additionalStylesheets = CssUtil.getAdditionalStylesheetsByAnnotation(getClass());
        for (final String additionalStylesheet : additionalStylesheets) {
            final String stylesheet = CssUtil.getStylesheet(getClass(), additionalStylesheet);
            if (stylesheet == null) {
                throw new ViewInitializationException("No stylesheet found for \"" + additionalStylesheet + "\"!");
            }
            parent.getStylesheets().add(stylesheet);
        }
        final String stylesheet = CssUtil.getStylesheet(getClass(), getViewName());
        if (stylesheet != null) {
            parent.getStylesheets().add(stylesheet);
        }
    }

    private String getViewName() {
        String viewName = getClass().getSimpleName().toLowerCase();
        viewName = viewName.replace(VIEW_ENDING, "");
        return viewName;
    }

    public Parent getView() {
        return loader.getRoot();
    }

    public Object getPresenter() {
        return loader.getController();
    }
}

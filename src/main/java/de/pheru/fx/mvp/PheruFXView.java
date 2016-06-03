package de.pheru.fx.mvp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Philipp Bruckner
 */
public abstract class PheruFXView {

    public static final String VIEW_ENDING = "view";

    @Inject
    private FXMLLoader loader;

    @PostConstruct
    private void init() {
        try {
            loader.setLocation(getClass().getResource(getViewName() + ".fxml"));
            addResourceBundle();
            loader.load();
            addCSS();
        } catch (IllegalStateException | IOException ex) {
            throw new IllegalStateException("Could not load " + getClass().getName(), ex);
        }
    }

    private void addResourceBundle() {
        try {
            ResourceBundle resources = ResourceBundle.getBundle(getClass().getPackage().getName() + "." + getViewName());
            loader.setResources(resources);
        } catch (MissingResourceException e) {
            // do nothing
        }
    }

    private void addCSS() {
        URL uri = getClass().getResource(getViewName() + ".css");
        if (uri == null) {
            return;
        }
        String css = uri.toExternalForm();
        Parent parent = loader.getRoot();
        parent.getStylesheets().add(css);
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

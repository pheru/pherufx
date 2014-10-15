package de.eru.pherufx.gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Philipp Bruckner
 */
public abstract class JavaFXView {

    public static final String VIEW_ENDING = "view";

    @Inject
    private FXMLLoader fxmlLoader;

    @PostConstruct
    private void init() throws IOException {
        URL resource = getClass().getResource(getFXMLName());
        fxmlLoader.setLocation(resource);
        fxmlLoader.load();
    }

    private String getFXMLName() {
        String fxmlName = "";
        fxmlName = getClass().getSimpleName().toLowerCase();
        fxmlName = fxmlName.replace(VIEW_ENDING, "");
        fxmlName = fxmlName.concat(".fxml");
        return fxmlName;
    }

    public Parent getView() {
        return fxmlLoader.getRoot();
    }

    public Object getPresenter() {
        return fxmlLoader.getController();
    }
}

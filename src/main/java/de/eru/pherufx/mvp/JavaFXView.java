package de.eru.pherufx.mvp;

import java.io.IOException;
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
    private FXMLLoader loader;

    @PostConstruct
    private void init() throws IOException {
        URL resource = getClass().getResource(getViewName() + ".fxml");
        loader.setLocation(resource);
        loader.load();
        addCSS();
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
    
    private String getViewName(){
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

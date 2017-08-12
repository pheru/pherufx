package de.pheru.fx.mvp.factories;

import de.pheru.fx.mvp.qualifiers.ApplicationStylesheets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javax.inject.Inject;
import java.util.List;

public class AlertFactory {

    @Inject
    @ApplicationStylesheets
    private List<String> applicationStylesheets;

    public Alert createAlert(final Alert.AlertType alertType){
        final Alert alert = new Alert(alertType);
        alert.getDialogPane().getStylesheets().addAll(applicationStylesheets);
        return alert;
    }

    public Alert createAlert(final Alert.AlertType alertType, final String contentText, final ButtonType... buttons){
        final Alert alert = createAlert(alertType);
        alert.setContentText(contentText);
        alert.getButtonTypes().addAll(buttons);
        return alert;
    }
}

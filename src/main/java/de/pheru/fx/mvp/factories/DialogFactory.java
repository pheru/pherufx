package de.pheru.fx.mvp.factories;

import de.pheru.fx.mvp.qualifiers.ApplicationStylesheets;
import javafx.scene.control.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

public class DialogFactory {

    @Inject
    @ApplicationStylesheets
    private List<String> applicationStylesheets;

    public Alert createAlert(final Alert.AlertType alertType) {
        final Alert alert = new Alert(alertType);
        prepareDialog(alert);
        return alert;
    }

    public Alert createAlert(final Alert.AlertType alertType, final String contentText, final ButtonType... buttons) {
        final Alert alert = new Alert(alertType, contentText, buttons);
        prepareDialog(alert);
        return alert;
    }

    public TextInputDialog createTextInputDialog() {
        final TextInputDialog textInputDialog = new TextInputDialog();
        prepareDialog(textInputDialog);
        return textInputDialog;
    }

    public TextInputDialog createTextInputDialog(final String defaultValue) {
        final TextInputDialog textInputDialog = new TextInputDialog(defaultValue);
        prepareDialog(textInputDialog);
        return textInputDialog;
    }

    public <T> ChoiceDialog<T> createChoiceDialog() {
        final ChoiceDialog<T> choiceDialog = new ChoiceDialog<>();
        prepareDialog(choiceDialog);
        return choiceDialog;
    }

    public <T> ChoiceDialog<T> createChoiceDialog(final T defaultChoice, final T... choices) {
        final ChoiceDialog<T> choiceDialog = new ChoiceDialog<>(defaultChoice, choices);
        prepareDialog(choiceDialog);
        return choiceDialog;
    }

    public <T> ChoiceDialog<T> createChoiceDialog(final T defaultChoice, final Collection<T> choices) {
        final ChoiceDialog<T> choiceDialog = new ChoiceDialog<>(defaultChoice, choices);
        prepareDialog(choiceDialog);
        return choiceDialog;
    }

    public <T> Dialog<T> createDialog() {
        final Dialog<T> dialog = new Dialog<>();
        prepareDialog(dialog);
        return dialog;
    }

    private void prepareDialog(final Dialog<?> dialog) {
        dialog.getDialogPane().getStylesheets().addAll(applicationStylesheets);
    }
}

package org.openjfx.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.Arrays;
import java.util.Map;

public class ValidationUtils {

    public static BooleanBinding any(BooleanBinding[] bindings) {
        return Bindings.createBooleanBinding(() ->
                Arrays.stream(bindings).anyMatch(BooleanBinding::get), bindings);
    }

    public static BooleanBinding emptyTextFieldBinding(TextField textField, String message, Map<BooleanBinding, String> messages) {
        BooleanBinding binding = Bindings.createBooleanBinding(() ->
                textField.getText().trim().isEmpty(), textField.textProperty());
        configureTextFieldBinding(binding, textField, message, messages);
        return binding ;
    }

    private static void configureTextFieldBinding(BooleanBinding binding, TextField textField, String message, Map<BooleanBinding, String> messages) {
        messages.put(binding, message);
        if (textField.getTooltip() == null) {
            textField.setTooltip(new Tooltip());
        }
        String tooltipText = textField.getTooltip().getText();
        binding.addListener((obs, oldValue, newValue) -> {
            updateTextFieldValidationStatus(textField, tooltipText, newValue, message);
        });
        updateTextFieldValidationStatus(textField, tooltipText, binding.get(), message);
    }

    private static void updateTextFieldValidationStatus(TextField textField,
                                                 String defaultTooltipText, boolean invalid, String message) {
        textField.pseudoClassStateChanged(PseudoClass.getPseudoClass("validation-error"), invalid);
        String tooltipText ;
        if (invalid) {
            tooltipText = message;
        } else {
            tooltipText = defaultTooltipText;
        }
        if (tooltipText == null || tooltipText.isEmpty()) {
            textField.setTooltip(null);
        } else {
            Tooltip tooltip = textField.getTooltip();
            if (tooltip == null) {
                textField.setTooltip(new Tooltip(tooltipText));
            } else {
                tooltip.setText(tooltipText);
            }
        }
    }
}

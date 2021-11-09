package gmacias.control;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class RegisterUtils {

    Map<Control, Label> mapLabels = new HashMap<>();

    /*
     *  Returns true if the selected value in the ToggleGroup is equals to trueOption
     */
    public boolean isSelectedTrue(ToggleGroup toggleGroup, String trueOption) {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText().equals(trueOption);
    }

    public String getSelectedValue(ToggleGroup toggleGroup) {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText();
    }

    public boolean validateControlList(ArrayList<Control> controls) {
        boolean allValid = true;
        for (Control control : controls)
            if (!isValidControl(control))
                allValid = false;

        return allValid;
    }

    public boolean isValidControl(Control control) {
        switch (control.getClass().getSimpleName()) {
            case "DatePicker":
                DatePicker datePicker = (DatePicker) control;
                if (datePicker.getValue() == null)
                    return invalidControl(control, "A data não pode estar vazia");     // invalidControl always returns false
                else if (datePicker.getValue().isAfter(LocalDate.now()))
                    return invalidControl(control, "A data não pode ser maior que hoje");
                else
                    return validControl(control);                      // validControl always returns true
            case "TextField":
                for (String validation : control.getStyleClass())
                    switch (validation) {
                        case "notEmpty":
                            if (((TextField) control).getText().trim().isEmpty())
                                return invalidControl(control, "Campo deve ser preenchido");
                            break;
                        case "onlyLetters":
                            if (!((TextField) control).getText().matches("[\\p{L} ]+"))
                                return invalidControl(control, "Este campo deve conter somente letras");
                            break;
                    }
                break;

        }

        return validControl(control);
    }

    private boolean invalidControl(Control field, String errorMessage) {
        if (!field.getStyleClass().contains("invalid-field"))
            field.getStyleClass().add("invalid-field");

        if (mapLabels.containsKey(field))
            mapLabels.get(field).setText(" | " + errorMessage);
        else {
            Label errorLabel = new Label(" | " + errorMessage);
            errorLabel.setTextFill(Color.RED);
            mapLabels.put(field, errorLabel);
            ((HBox) field.getParent()).getChildren().add(mapLabels.get(field));
        }

        return false;
    }

    private boolean validControl(Control field) {
        if (field.getStyleClass().contains("invalid-field"))
            field.getStyleClass().remove("invalid-field");
        ((HBox) field.getParent()).getChildren().remove(mapLabels.get(field));
        mapLabels.remove(field);
        return true;
    }

}

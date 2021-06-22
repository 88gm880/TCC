package control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField nameTxt;
    @FXML
    private DatePicker birthdayDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

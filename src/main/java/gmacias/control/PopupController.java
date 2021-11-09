package gmacias.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/popup.fxml")
public class PopupController implements Initializable {

    @FXML
    private Label message;
    @FXML
    private Button okBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void closeAction(ActionEvent event) {
        ((Stage) okBtn.getScene().getWindow()).close();
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }
}

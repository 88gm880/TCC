package gmacias.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import gmacias.model.entity.User;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@FxmlView("../view/shutdown.fxml")
public class ShutdownController implements Initializable {

    @FXML
    private ComboBox<User> userCb;

    @FXML
    private DatePicker shutdownDate;

    @FXML
    private TextArea shutdownReason;

    @FXML
    private Button cancel, shutdown;

    @FXML
    void cancelOnAction(ActionEvent event) {

    }

    @FXML
    void shutdownDateOnAction(ActionEvent event) {

    }

    @FXML
    void shutdownOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

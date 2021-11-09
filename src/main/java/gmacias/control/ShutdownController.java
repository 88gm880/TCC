package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView("../view/shutdown.fxml")
public class ShutdownController {

    @FXML
    private VBox root;

    @FXML
    private ComboBox<?> userCb;

    @FXML
    private DatePicker shutdownDate;

    @FXML
    private TextArea shutdownReason;

    @FXML
    private Button cancel;

    @FXML
    private Button shutdown;

    @FXML
    void cancelOnAction(ActionEvent event) {

    }

    @FXML
    void shutdownDateOnAction(ActionEvent event) {

    }

    @FXML
    void shutdownOnAction(ActionEvent event) {

    }

}

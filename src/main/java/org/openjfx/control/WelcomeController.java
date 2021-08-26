package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.enums.ScreensEnum;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@FxmlView("../view/welcome.fxml")
public class WelcomeController implements Initializable {

    @FXML
    private Button registerScreen;

    @FXML
    private Button listScreen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerScreenOnAction(ActionEvent event) {
        ScreensEnum.setPane(ScreensEnum.register);
    }

    public void listScreenOnAction(ActionEvent event) {
        ScreensEnum.setPane(ScreensEnum.list);
    }
}

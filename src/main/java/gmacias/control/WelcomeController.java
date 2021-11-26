package gmacias.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import net.rgielen.fxweaver.core.FxmlView;
import gmacias.control.enums.ScreensEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@FxmlView("../view/welcome.fxml")
public class WelcomeController implements Initializable {

    @FXML // User buttons
    private Button registerScreen, listScreen, shutdownScreen;

    @Autowired
    private ListController listController;

    @Autowired
    private NewActivityController newActivityController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerScreenOnAction(ActionEvent event) {
        ScreensEnum.setPane(ScreensEnum.register);
    }

    public void listScreenOnAction(ActionEvent event) {
        if (ScreensEnum.setPane(ScreensEnum.list))
            listController.updateTable();
    }

    public void shutdownScreenOnAction(ActionEvent event) {
        ScreensEnum.setPane(ScreensEnum.shutdown);
    }

    public void newActivityOnAction(ActionEvent event) {
        if (ScreensEnum.setPane(ScreensEnum.newActivity))
            newActivityController.updateTable();
    }
}

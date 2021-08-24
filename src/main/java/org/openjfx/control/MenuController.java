package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.enums.ScreensEnum;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/menu.fxml")
public class MenuController implements Initializable {

    @FXML
    private MenuItem menuCadastrar;
    @FXML
    private MenuItem menuLista;
    @FXML
    private MenuBar menuBar;
    @FXML
    private VBox root;

    private int lastId = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getChildren().add(ScreensEnum.welcome.node);
    }

    public void menuCadastrarOnAction(ActionEvent event) {
        setPane(ScreensEnum.register);
    }

    public void menuListaOnAction(ActionEvent event) {
        setPane(ScreensEnum.list);
    }

    private void setPane(ScreensEnum pane) {
        if (lastId != pane.id) {
            root.getChildren().remove(ScreensEnum.findById(lastId).node);
            root.getChildren().add(pane.node);
            lastId = pane.id;
        } else {
            System.out.println("Já está nessa tela");
        }
    }

}

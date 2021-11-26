package gmacias.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import gmacias.control.enums.ScreensEnum;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ListController listController;
    @Autowired
    private NewActivityController newActivityController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void menuCadastrarOnAction(ActionEvent event) {
        ScreensEnum.setPane(ScreensEnum.register);
    }

    public void menuListaOnAction(ActionEvent event) {
        if (ScreensEnum.setPane(ScreensEnum.list))
            listController.updateTable();
    }

    public void menuInicioOnAction(ActionEvent event){
        ScreensEnum.setPane(ScreensEnum.welcome);
    }

    public void menuListaAtividadeOnAction(ActionEvent event) {

    }

    public void menuDesligarOnAction(ActionEvent event) {
        ScreensEnum.setPane(ScreensEnum.shutdown);
    }

    public void menuAtividadeOnAction(ActionEvent event) {
        if (ScreensEnum.setPane(ScreensEnum.newActivity))
            newActivityController.updateTable();
    }

}

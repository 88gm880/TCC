package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private enum paneEnum {
        welcome(0, "../view/welcome.fxml"),  //
        register(1, "../view/register2.fxml"),  //
        list(2, "../view/list.fxml"),           //
        ;

        private paneEnum(int id, String path) {
            this.id = id;
            Node aux;
            try {
                aux = FXMLLoader.load(getClass().getResource(path));
            } catch (Exception e) {
                System.out.println("Problema ao carregar a tela " + path);
                aux = null;
            }
            this.node = aux;
        }

        private static paneEnum findById(int id) {
            for (paneEnum pane : values())
                if (pane.id == id)
                    return pane;
            return null;
        }

        private final int id;
        private final Node node;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getChildren().add(paneEnum.welcome.node);
    }

    public void menuCadastrarOnAction(ActionEvent event) {
        setPane(paneEnum.register);
    }

    public void menuListaOnAction(ActionEvent event) {
        setPane(paneEnum.list);
    }

    private void setPane(paneEnum pane) {
        if (lastId != pane.id) {
            root.getChildren().remove(paneEnum.findById(lastId).node);
            root.getChildren().add(pane.node);
            lastId = pane.id;
        } else {
            System.out.println("Já está nessa tela");
        }
    }

}

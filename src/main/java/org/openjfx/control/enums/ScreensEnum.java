package org.openjfx.control.enums;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.openjfx.JavaFxApplication;
import org.openjfx.control.ListController;
import org.openjfx.control.LoginController;
import org.openjfx.control.RegisterController;

public enum ScreensEnum {

    login(0, LoginController.class), //
    //welcome(1, ),  //
    register(2, RegisterController.class),  //
    list(3, ListController.class),          //
    ;

    private ScreensEnum(int id, Class controller) {
        this.id = id;
        Node aux;
        try {
            aux = JavaFxApplication.fxWeaver.loadView(controller);
        } catch (Exception e) {
            System.out.println("Problema ao carregar a tela " + controller.getName());
            aux = null;
        }
        this.node = aux;
    }

    public static ScreensEnum findById(int id) {
        for (ScreensEnum pane : values())
            if (pane.id == id)
                return pane;
        return null;
    }

    public final int id;
    public final Node node;
}

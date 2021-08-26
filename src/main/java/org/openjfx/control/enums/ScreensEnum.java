package org.openjfx.control.enums;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import lombok.Getter;
import org.openjfx.JavaFxApplication;
import org.openjfx.control.*;

public enum ScreensEnum {

    login(0, LoginController.class), //
    welcome(1, WelcomeController.class),  //
    register(2, RegisterController.class),  //
    list(3, ListController.class),          //
    menu(4, MenuController.class);

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

    public static void setPane(ScreensEnum pane) {
        if (lastPaneId != 0)
            root.getChildren().remove(findById(lastPaneId).node);
        root.getChildren().add(pane.node);
        lastPaneId = pane.id;
    }

    @Getter
    private final int id;
    @Getter
    private final Node node;
    @Getter
    private static final VBox root = (VBox) menu.node;
    private static int lastPaneId = 0;

}

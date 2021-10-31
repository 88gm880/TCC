package org.openjfx.control.enums;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import org.openjfx.JavaFxApplication;
import org.openjfx.control.ListController;
import org.openjfx.control.LoginController;
import org.openjfx.control.MenuController;
import org.openjfx.control.PopupController;
import org.openjfx.control.RegisterController;
import org.openjfx.control.WelcomeController;

public enum ScreensEnum {

    login(0, LoginController.class),        //
    welcome(1, WelcomeController.class),    //
    register(2, RegisterController.class),  //
    list(3, ListController.class),          //
    menu(4, MenuController.class),          //
    popup(5, PopupController.class)         //
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

    public static boolean setPane(ScreensEnum pane) {
        if (lastPaneId == pane.id)
            return false;
        if (lastPaneId != 0)
            root.getChildren().remove(findById(lastPaneId).node);
        root.getChildren().add(pane.node);
        lastPaneId = pane.id;
        return true;
    }

    public static void showPopup(String message){
        PopupController popupController = JavaFxApplication.fxWeaver.getBean(PopupController.class);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Aviso");
        window.setResizable(false);
        VBox popup = (VBox) ScreensEnum.popup.getNode();
        popupController.setMessage(message);
        try{
            window.setScene(new Scene(popup, 200, 150));
        }catch (Exception e) {
            window.setScene(popup.getScene());
        }
        window.showAndWait();
    }

    @Getter
    private final int id;
    @Getter
    private final Node node;
    @Getter
    private static final VBox root = (VBox) menu.node;
    private static int lastPaneId = 0;

}

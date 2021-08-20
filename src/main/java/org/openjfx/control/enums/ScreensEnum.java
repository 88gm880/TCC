package org.openjfx.control;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public enum ScreensEnum {

    welcome(0, "../view/welcome.fxml"),  //
    register(1, "../view/register2.fxml"),  //
    list(2, "../view/list.fxml"),           //
    ;

    private ScreensEnum(int id, String path) {
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

    public static ScreensEnum findById(int id) {
        for (ScreensEnum pane : values())
            if (pane.id == id)
                return pane;
        return null;
    }

    public final int id;
    public final Node node;
}

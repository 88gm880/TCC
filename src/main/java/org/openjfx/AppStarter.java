package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class AppStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        File logo2File = new File(getClass().getClassLoader().getResource("org/openjfx/images/logo1.png").getFile());
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getIcons().add(new Image(logo2File.toURI().toString()));
        primaryStage.setTitle("Gerenciador Casa do Piá");
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

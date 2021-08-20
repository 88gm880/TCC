package org.openjfx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxWeaver;
import org.openjfx.control.LoginController;
import org.openjfx.control.enums.ScreensEnum;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    public static FxWeaver fxWeaver;

    @Override
    public void init(){
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(AppStarter.class)
                .run(args);
        fxWeaver = applicationContext.getBean(FxWeaver.class);
    }

    @Override
    public void start(Stage primaryStage) {

        File logo2File = new File(getClass().getClassLoader().getResource("org/openjfx/images/logo1.png").getFile());
        //FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = (Parent) ScreensEnum.login.node;
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.getIcons().add(new Image(logo2File.toURI().toString()));
        primaryStage.setTitle("Gerenciador Casa do Piá");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();
    }
}

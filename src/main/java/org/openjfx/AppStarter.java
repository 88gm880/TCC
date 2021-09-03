package org.openjfx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppStarter{


    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }
}

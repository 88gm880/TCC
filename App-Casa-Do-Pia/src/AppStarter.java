import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./vision/login.fxml"));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Gerenciador Casa do Piá");
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

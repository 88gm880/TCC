import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.getIcons().add(new Image("file:App-Casa-Do-Pia/images/logo1.png"));
        primaryStage.setTitle("Gerenciador Casa do Piá");
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

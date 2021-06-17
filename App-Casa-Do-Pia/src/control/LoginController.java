package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button loginCancelBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginErrorLbl;
    @FXML
    private ImageView loginLogoImg;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logo2File = new File("/resources/images/logo2.png");
        Image logo2Image = new Image(logo2File.toURI().toString());
        loginLogoImg.setImage(logo2Image);
    }

    public void loginBtnOnAction(ActionEvent event){
        loginErrorLbl.setOpacity((loginErrorLbl.getOpacity()+1)%2);
    }

    public void loginCancelBtnOnAction(ActionEvent event){
        Stage stage = (Stage) loginCancelBtn.getScene().getWindow();
        stage.close();
    }

}

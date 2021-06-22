package control;

import DAO.DbConnection;
import DAO.LoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ImageView loginLogoImg;
    @FXML
    private TextField userLoginTxt;
    @FXML
    private PasswordField userPwdTxt;
    @FXML
    private Label loginErrorLbl;
    @FXML
    private Button loginBtn;
    @FXML
    private Button loginCancelBtn;

    private LoginDAO loginDAO = new LoginDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println("Working Directory = " + System.getProperty("user.dir")); //descobre diretorio atual
        File logo2File = new File("./App-Casa-Do-Pia/images/logo2.png");
        Image logo2Image = new Image(logo2File.toURI().toString());
        loginLogoImg.setImage(logo2Image);
    }

    public void loginBtnOnAction(ActionEvent event) {
        validateLogin();
    }

    public void loginCancelBtnOnAction(ActionEvent event) {
        Stage stage = (Stage) loginCancelBtn.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        String username = userLoginTxt.getText();
        String password = userPwdTxt.getText();

        if (username.isBlank() || password.isBlank()) {
            loginErrorLbl.setText("O usuário e a senha devem ser preenchidos");
            loginErrorLbl.setOpacity(1.0);
        } else if (loginDAO.isValidLogin(username, password)) {
            System.out.println("Logou");
            openNew();
            loginErrorLbl.setOpacity(0.0);
        } else {
            loginErrorLbl.setText("Login e/ou senha incorretos. Tente novamente.");
            loginErrorLbl.setOpacity(1.0);
        }
    }

    public void openNew() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../vision/register.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setTitle("Gerenciador Casa do Piá");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.openjfx.model.dao.LoginDAO;

import java.io.File;
import java.net.URL;
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

    static VBox root;
    private LoginDAO loginDAO = new LoginDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logo2File = new File(getClass().getClassLoader().getResource("org/openjfx/images/logo2.png").getFile());
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
        userLoginTxt.setText(userLoginTxt.getText().trim());
        userPwdTxt.setText(userPwdTxt.getText().trim());
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
            VBox root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(new Scene(root, 870, 590));
        } catch (Exception e) {
            //System.out.println("Problema ao carregar a tela");
            e.printStackTrace();
            e.getCause();
        }
    }
}

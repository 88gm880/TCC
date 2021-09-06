package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.enums.ScreensEnum;
import org.openjfx.control.repositories.LoginRepository;
import org.openjfx.model.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/login.fxml")
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

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File logo2File = new File(getClass().getClassLoader().getResource("org/openjfx/images/logo2.png").getFile());
        Image logo2Image = new Image(logo2File.toURI().toString());
        loginLogoImg.setImage(logo2Image);

        //Config para login padrão
        /*Login login = new Login();
        login.setLoginUser("gmacias");
        login.setLoginPassword("admin");
        loginRepository.save(login);*/
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
        } else if (loginRepository.isValidLogin(username, password)) {
            System.out.println("Logou");
            login();
            loginErrorLbl.setOpacity(0.0);
        } else {
            loginErrorLbl.setText("Login e/ou senha incorretos. Tente novamente.");
            loginErrorLbl.setOpacity(1.0);
        }
    }

    public void login() {
        try {
            VBox root = (VBox) ScreensEnum.menu.getNode();
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(new Scene(root, 870, 590));
            ScreensEnum.setPane(ScreensEnum.welcome);
            ScreensEnum.getRoot().getScene().getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

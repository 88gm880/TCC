package control;

import dao.StudentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.StudentImpl;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField ageTxt;
    private int age;
    @FXML
    private TextField naturality;
    @FXML
    private TextField fatherName;
    @FXML
    private TextField motherName;
    @FXML
    private TextField addressStreet;
    @FXML
    private TextField addressNumber;
    @FXML
    private TextField addressDistrict;
    @FXML
    private TextField addressComplement;
    @FXML
    private TextField phone;
    @FXML
    private TextField messagePhone;
    @FXML
    private Button testeBtn;

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void birthdayOnAction(ActionEvent event) {
        if (birthday.getValue().isAfter(LocalDate.now())){
            throw new RuntimeException("Data de nascimento não pode ser maior que hoje");
        }
        age = Period.between(birthday.getValue(), LocalDate.now()).getYears();
        ageTxt.setText(String.valueOf(age));
    }

    public void testeBtnOnAction(ActionEvent event) {
        studentDAO.registerStudent(new StudentImpl(name.getText(),
                birthday.getValue(),
                age,
                naturality.getText(),
                fatherName.getText(),
                motherName.getText(),
                phone.getText(),
                messagePhone.getText()));
    }


}

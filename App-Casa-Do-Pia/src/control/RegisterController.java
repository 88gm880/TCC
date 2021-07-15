package control;

import dao.StudentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private TabPane tabPane;
    @FXML
    private Button nextBtn0;
    @FXML
    private Button nextBtn1;
    @FXML
    private Button nextBtn2;
    @FXML
    private Button nextBtn3;
    @FXML
    private Spinner rooms;
    @FXML
    private Spinner bedrooms;
    @FXML
    private ChoiceBox maritalStatus;

    private StudentDAO studentDAO = new StudentDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        bedrooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        maritalStatus.getItems().addAll("Solteiro", "Casado", "União estável", "Viúvo");
    }

    public void birthdayOnAction(ActionEvent event) {
        if (birthday.getValue().isAfter(LocalDate.now())) {
            throw new RuntimeException("Data de nascimento não pode ser maior que hoje");
        }
        age = Period.between(birthday.getValue(), LocalDate.now()).getYears();
        ageTxt.setText(String.valueOf(age));
    }

    public void nextBtnOnAction(ActionEvent event) {
        if (event.getSource().equals(nextBtn0))
            tabPane.getSelectionModel().select(1);
        if (event.getSource().equals(nextBtn1))
            tabPane.getSelectionModel().select(2);
        if (event.getSource().equals(nextBtn2))
            tabPane.getSelectionModel().select(3);
        if (event.getSource().equals(nextBtn3))
            tabPane.getSelectionModel().select(4);

    }

    public void registerBtnOnAction(ActionEvent event) {
        //studentDAO.registerStudent(new StudentImpl(name.getText(), birthday.getValue(), age, naturality.getText(),
        //        fatherName.getText(), motherName.getText(), phone.getText(), messagePhone.getText()));

    }


}

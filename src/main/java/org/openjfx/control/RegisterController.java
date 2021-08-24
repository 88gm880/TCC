package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.repositories.AddressRepository;
import org.openjfx.control.repositories.StudentRepository;
import org.openjfx.model.entity.Address;
import org.openjfx.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/register.fxml")
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
    private CheckBox godfather;
    @FXML
    private CheckBox deadFather;
    @FXML
    private CheckBox godmother;
    @FXML
    private CheckBox deadMother;
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
    private Button prevBtn1;
    @FXML
    private Button prevBtn2;
    @FXML
    private Button prevBtn3;
    @FXML
    private Spinner<Integer> rooms;
    @FXML
    private Spinner<Integer> bedrooms;
    @FXML
    private TextField legalGuardian;
    @FXML
    private TextField lgRelation;
    @FXML
    private TextField lgCpf;
    @FXML
    private TextField lgAge;
    @FXML
    private ChoiceBox<String> lgMaritalStatus;


    //private StudentDAO studentDAO = new StudentDAO();
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        bedrooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        lgMaritalStatus.getItems().addAll("Solteiro", "Casado", "União estável", "Viúvo");
    }

    public void birthdayOnAction(ActionEvent event) throws RuntimeException {
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

    public void prevBtnOnAction(ActionEvent event) {
        if (event.getSource().equals(prevBtn1))
            tabPane.getSelectionModel().select(0);
        if (event.getSource().equals(prevBtn2))
            tabPane.getSelectionModel().select(1);
        if (event.getSource().equals(prevBtn3))
            tabPane.getSelectionModel().select(2);

    }

    public void registerBtnOnAction(ActionEvent event) {

        /* Código antigo:
        address.setStreet(addressStreet.getText());
        address.setNumber(addressNumber.getText());
        address.setDistrict(addressDistrict.getText());
        address.setComplement(addressComplement.getText());
        new Student(name.getText(), birthday.getValue(), age, naturality.getText(),
                fatherName.getText(), motherName.getText(), address, phone.getText(), messagePhone.getText()
        */
        Address address = Address.builder()
                .street(addressStreet.getText())
                .number(addressNumber.getText())
                .district(addressDistrict.getText())
                .complement(addressComplement.getText())
                .build();

        /*Habitation habitation = Habitation.builder()
                .build();

        Health health = Health.builder()
                .build();

        SocialAssistance socialAssistance = SocialAssistance.builder()
                .build();*/

        //Cria um aluno com os atributos setados
        Student student = Student.builder()
                .name(name.getText())
                .birthday(birthday.getValue())
                .age(age)
                .naturality(naturality.getText())
                .fatherName(fatherName.getText())
                //.godfather(godfather.isSelected())
                //.deadFather(deadFather.isSelected())
                .motherName(motherName.getText())
                //.godmother(godmother.isSelected())
                //.deadMother(deadMother.isSelected())
                .phone(phone.getText())
                .messagePhone(messagePhone.getText())
                //.address(address)
                //.habitation(habitation)
                //.health(health)
                //.socialAssistance(socialAssistance)
                .build();

        address.setStudent(student);
        studentRepository.save(student);
        addressRepository.save(address);
        //studentDAO.registerStudent(student);
    }
}

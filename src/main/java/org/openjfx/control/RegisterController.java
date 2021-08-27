package org.openjfx.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.repositories.AddressRepository;
import org.openjfx.control.repositories.HabitationRepository;
import org.openjfx.control.repositories.StudentRepository;
import org.openjfx.model.entity.Habitation;
import org.openjfx.model.entity.Student;
import org.openjfx.model.entity.Address;
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
    private TabPane tabPane;
    @FXML
    private DatePicker birthday;
    @FXML
    private Spinner<Integer> rooms, bedrooms;
    @FXML
    private CheckBox godfather, deadFather, godmother, deadMother;
    @FXML
    private ChoiceBox<String> lgMaritalStatus, residenceKind, buildingType;
    @FXML
    private Button nextBtn0, nextBtn1, nextBtn2, nextBtn3,
            prevBtn1, prevBtn2, prevBtn3;
    @FXML
    private ToggleGroup physicalHealthRb, mentalHealthRb, medicalRb, remedyRb, sewerRb,
            sexoRb, pipedWaterRb, eletricLightRb;
    @FXML
    private TextField name, ageTxt, naturality, fatherName, motherName, phone, messagePhone, //
            addressStreet, addressNumber, addressDistrict, addressComplement, addressReference,//
            otherResidenceKind, otherBuildingType,  //
            legalGuardian, lgRelation, lgCpf, lgAge,
            obsPhysical, obsMental, obsMedical, obsRemedy;  //

    private int age;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private HabitationRepository habitationRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        bedrooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        lgMaritalStatus.getItems().addAll("Solteiro", "Casado", "União estável", "Viúvo");
        residenceKind.getItems().addAll("Particular permanente", "Particular provisório", "Particular coletivo", "Outros:");
        buildingType.getItems().addAll("Alvenaria", "Madeira", "Misto", "Outros:");
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

        //Cria um aluno com os atributos setados
        final Student student = Student.builder()
                .name(name.getText())
                .birthday(birthday.getValue())
                .age(age)
                .naturality(naturality.getText())
                .fatherName(fatherName.getText())
                .godfather(godfather.isSelected())
                .deadFather(deadFather.isSelected())
                .motherName(motherName.getText())
                .godmother(godmother.isSelected())
                .deadMother(deadMother.isSelected())
                .phone(phone.getText())
                .messagePhone(messagePhone.getText())
                //.address(address)
                //.habitation(habitation)
                //.health(health)
                //.socialAssistance(socialAssistance)
                .build();

        final Address address = Address.builder()
                .street(addressStreet.getText())
                .number(addressNumber.getText())
                .district(addressDistrict.getText())
                .complement(addressComplement.getText())
                .reference(addressReference.getText())
                .student(student)
                .build();


        final Habitation habitation = Habitation.builder()
                .residenceKind(residenceKind.getValue().equals("Outros:")
                        ? otherResidenceKind.getText() : residenceKind.getValue())
                .privatePipedWater(isSelectedTrue(pipedWaterRb, "Água encanada particular"))
                .privateEletricity(isSelectedTrue(eletricLightRb,"Luz elétrica particular"))
                .sewer(isSelectedTrue(sewerRb, "Sim"))
                .buildingType(buildingType.getValue().equals("Outros:")
                        ? otherBuildingType.getText() : buildingType.getValue())
                .rooms(rooms.getValue())
                .bedrooms(bedrooms.getValue())
                .student(student)
                .build();

        /*

        Health health = Health.builder()
                .build();

        SocialAssistance socialAssistance = SocialAssistance.builder()
                .build();*/

        studentRepository.save(student);
        addressRepository.save(address);
        habitationRepository.save(habitation);

    }

    private boolean isSelectedTrue(ToggleGroup toggleGroup, String trueOption) {
        return !((RadioButton) toggleGroup.getSelectedToggle()).getText().equals(trueOption);
    }
}

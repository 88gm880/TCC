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
import org.openjfx.control.repositories.HealthRepository;
import org.openjfx.control.repositories.SocialAssistanceRepository;
import org.openjfx.control.repositories.StudentRepository;
import org.openjfx.model.entity.Habitation;
import org.openjfx.model.entity.Health;
import org.openjfx.model.entity.SocialAssistance;
import org.openjfx.model.entity.Student;
import org.openjfx.model.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.UUID;

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
            otherResidenceKind, otherBuildingType, nis, cras, //
            legalGuardian, lgRelation, lgCpf, lgAge,
            physicalObs, mentalObs, medicalObs, remedyObs;  //

    private int age;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private HabitationRepository habitationRepository;
    @Autowired
    private HealthRepository healthRepository;
    @Autowired
    private SocialAssistanceRepository socialAssistanceRepository;

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
        if (event.getSource().equals(nextBtn0)) {
            tabPane.getSelectionModel().select(1);
            //invalidTextField(name);
        } else if (event.getSource().equals(nextBtn1)) {
            tabPane.getSelectionModel().select(2);
            /*name.getStyleClass().remove("invalid-field");
            name.setPromptText("");*/
        } else if (event.getSource().equals(nextBtn2)) {
            tabPane.getSelectionModel().select(3);
        } else if (event.getSource().equals(nextBtn3)) {
            tabPane.getSelectionModel().select(4);
        }
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
                .codStudent(UUID.randomUUID().toString())
                .birthday(birthday.getValue())
                .age(age)
                .naturality(naturality.getText())
                .sexo(isSelectedTrue(sexoRb, "Masculino") ? 'M' : 'F')
                .fatherName(fatherName.getText())
                .godfather(godfather.isSelected())
                .deadFather(deadFather.isSelected())
                .motherName(motherName.getText())
                .godmother(godmother.isSelected())
                .deadMother(deadMother.isSelected())
                .phone(phone.getText())
                .messagePhone(messagePhone.getText())
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
                .privateEletricity(isSelectedTrue(eletricLightRb, "Luz elétrica particular"))
                .sewer(isSelectedTrue(sewerRb, "Sim"))
                .buildingType(buildingType.getValue().equals("Outros:")
                        ? otherBuildingType.getText() : buildingType.getValue())
                .rooms(rooms.getValue())
                .bedrooms(bedrooms.getValue())
                .student(student)
                .build();


        Health health = Health.builder()
                .physicalIllness(isSelectedTrue(physicalHealthRb, "Sim:"))
                .physicalObs(isSelectedTrue(physicalHealthRb, "Sim:") ? physicalObs.getText() : null)
                .mentalIllness(isSelectedTrue(mentalHealthRb, "Sim:"))
                .mentalObs(isSelectedTrue(mentalHealthRb, "Sim:") ? mentalObs.getText() : null)
                .medicalMonitoring(isSelectedTrue(medicalRb, "Sim:"))
                .medicalObs(isSelectedTrue(medicalRb, "Sim:") ?  medicalObs.getText() : null)
                .continuousRemedy(isSelectedTrue(remedyRb, "Sim:"))
                .remedyObs(isSelectedTrue(remedyRb, "Sim:") ? remedyObs.getText() : null)
                .student(student)
                .build();

        SocialAssistance socialAssistance = SocialAssistance.builder()
                .nis(nis.getText())
                .cras(cras.getText())
                .student(student)
                .build();

        studentRepository.save(student);
        addressRepository.save(address);
        habitationRepository.save(habitation);
        healthRepository.save(health);
        socialAssistanceRepository.save(socialAssistance);

        //TODO mover botão de registrar, e ajeitar lista de estudantes
    }

    /*
     *  Retorna true se o valor selecionado no ToggleGroup for igual ao de trueOption
     *  Returns true if the selected value in the ToggleGroup is equals to trueOption
     * */
    private boolean isSelectedTrue(ToggleGroup toggleGroup, String trueOption) {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText().equals(trueOption);
    }

    private boolean validateFields(int page) {
        switch (page) {
            /*case 0:
                return validStudentAndAddress();*/
            default:
                return true;
        }
    }

    /*private boolean validStudentAndAddress() {
        boolean result = true;
        return result;
    }*/

    private void invalidTextField(TextField field, String errorMessage) {
        if (!field.getStyleClass().contains("invalid-field"))
            field.getStyleClass().add("invalid-field");
        field.setPromptText(errorMessage); // "Campo não pode estar vazio"
    }

    private void validTextField(TextField field, String promptText){
        if (field.getStyleClass().contains("invalid-field"))
            field.getStyleClass().remove("invalid-field");
        field.setPromptText(promptText);
    }
}

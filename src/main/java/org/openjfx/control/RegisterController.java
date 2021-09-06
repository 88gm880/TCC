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
import org.openjfx.control.repositories.ScholarityRepository;
import org.openjfx.control.repositories.SocialAssistanceRepository;
import org.openjfx.control.repositories.StudentRepository;
import org.openjfx.model.entity.Habitation;
import org.openjfx.model.entity.Health;
import org.openjfx.model.entity.Scholarity;
import org.openjfx.model.entity.SocialAssistance;
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
    private DatePicker birthday, startDate;
    @FXML
    private Spinner<Integer> rooms, bedrooms;
    @FXML
    private CheckBox godfather, deadFather, godmother, deadMother;
    @FXML
    private ChoiceBox<String> lgMaritalStatus, residenceKind, buildingType, schoolGrade, referralInstitution;
    @FXML
    private Button nextBtn0, nextBtn1, nextBtn2,
            prevBtn1, prevBtn2, prevBtn3;
    @FXML
    private ToggleGroup physicalHealthRb, mentalHealthRb, medicalRb, remedyRb, sewerRb,
            bolsaFamiliaRb, sexoRb, pipedWaterRb, eletricLightRb, otherAssistancesRb,
            attendingShiftRb, schoolShiftRb, learningDifficultyRb;
    @FXML
    private TextField name, ageTxt, naturality, fatherName, motherName, phone, messagePhone,        //
            addressStreet, addressNumber, addressDistrict, addressComplement, addressReference,     //
            otherResidenceKind, otherBuildingType, nis, cras, bolsaFamiliaObs, otherAssistancesObs, //
            legalGuardian, lgRelation, lgCpf, lgAge,                                                //
            physicalObs, mentalObs, medicalObs, remedyObs,                                          //
            schoolName, learningDifficultyObs, otherReferral, scholarityObs;                        //

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
    @Autowired
    private ScholarityRepository scholarityRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        bedrooms.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));
        lgMaritalStatus.getItems().addAll("Solteiro", "Casado", "União estável", "Viúvo");
        residenceKind.getItems().addAll("Particular permanente", "Particular provisório", "Particular coletivo", "Outros:");
        buildingType.getItems().addAll("Alvenaria", "Madeira", "Misto", "Outros:");
        for (int i = 1; i <= 9; i++) //add 1° até 8° série
            schoolGrade.getItems().add(i + "° ano");
        for (int i = 1; i <= 3; i++) //add 1° até 3° ano do colegial
            schoolGrade.getItems().add(i + "° ano do colegial");
        referralInstitution.getItems().addAll("CRAS", "CREAS", "Conselho tutelar", "Procura espontânea", "Outros:");
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
//            invalidTextField(name, "Campo não deve ser vazio");
        } else if (event.getSource().equals(nextBtn1)) {
            tabPane.getSelectionModel().select(2);
            /*name.getStyleClass().remove("invalid-field");
            name.setPromptText("");*/
        } else if (event.getSource().equals(nextBtn2)) {
            tabPane.getSelectionModel().select(3);
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
                .startDate(startDate.getValue())
                .attendingShift(getSelectedValue(attendingShiftRb))
                .referralInstitution(referralInstitution.getValue().equals("Outros:")
                        ? otherReferral.getText() : referralInstitution.getValue())
                .build();

        final Address address = Address.builder()
                .street(addressStreet.getText())
                .number(addressNumber.getText())
                .district(addressDistrict.getText())
                .complement(addressComplement.getText())
                .reference(addressReference.getText())
                //.student(student)
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
                .medicalObs(isSelectedTrue(medicalRb, "Sim:") ? medicalObs.getText() : null)
                .continuousRemedy(isSelectedTrue(remedyRb, "Sim:"))
                .remedyObs(isSelectedTrue(remedyRb, "Sim:") ? remedyObs.getText() : null)
                .student(student)
                .build();

        SocialAssistance socialAssistance = SocialAssistance.builder()
                .nis(nis.getText())
                .cras(cras.getText())
                .bolsaFamilia(isSelectedTrue(bolsaFamiliaRb, "Sim?:"))
                .bolsaFamiliaObs(isSelectedTrue(bolsaFamiliaRb, "Sim?:") ? bolsaFamiliaObs.getText() : null)
                .otherAssistances(isSelectedTrue(otherAssistancesRb, "Sim:"))
                .otherAssistancesObs(isSelectedTrue(otherAssistancesRb, "Sim:")? otherAssistancesObs.getText(): null)
                .student(student)
                .build();

        Scholarity scholarity = Scholarity.builder()
                .schoolName(schoolName.getText())
                .schoolShift(getSelectedValue(schoolShiftRb))
                .grade(schoolGrade.getValue())
                .learningDifficulty(isSelectedTrue(learningDifficultyRb, "Sim:"))
                .learningDifficultyObs(isSelectedTrue(learningDifficultyRb, "Sim:") ? learningDifficultyObs.getText() : null)
                .scholarityObs(scholarityObs.getText())
                .student(student)
                .build();

        studentRepository.save(student);
        addressRepository.save(address);
        habitationRepository.save(habitation);
        healthRepository.save(health);
        socialAssistanceRepository.save(socialAssistance);
        scholarityRepository.save(scholarity);
    }

    /*
     *  Returns true if the selected value in the ToggleGroup is equals to trueOption
     * */
    private boolean isSelectedTrue(ToggleGroup toggleGroup, String trueOption) {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText().equals(trueOption);
    }

    private String getSelectedValue(ToggleGroup toggleGroup) {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText();
    }

    private boolean validFields() {
        return false;
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

    private void validTextField(TextField field, String promptText) {
        if (field.getStyleClass().contains("invalid-field"))
            field.getStyleClass().remove("invalid-field");
        field.setPromptText(promptText);
    }

    private void cleatTextFields() {

    }
}

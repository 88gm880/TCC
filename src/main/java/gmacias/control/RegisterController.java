package gmacias.control;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import gmacias.control.enums.ScreensEnum;
import gmacias.control.repositories.AddressRepository;
import gmacias.control.repositories.HabitationRepository;
import gmacias.control.repositories.HealthRepository;
import gmacias.control.repositories.ScholarityRepository;
import gmacias.control.repositories.SocialAssistanceRepository;
import gmacias.control.repositories.UserRepository;
import gmacias.model.Exceptions.CreateUserException;
import gmacias.model.entity.Address;
import gmacias.model.entity.Habitation;
import gmacias.model.entity.Health;
import gmacias.model.entity.Kin;
import gmacias.model.entity.Scholarity;
import gmacias.model.entity.SocialAssistance;
import gmacias.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

@Controller
@FxmlView("../view/register.fxml")
public class RegisterController implements Initializable {

    /*
     *  ==============================================    FXML Variables    ===============================================
     */
    @FXML
    private TableView<Kin> kinshipTableView;
    @FXML
    private TableColumn<Kin, Double> incomeColumn;
    @FXML
    private TableColumn<Kin, Integer> ageColumn;
    @FXML
    private TableColumn<Kin, String> nameColumn, attendedColumn, kinshipColumn,
            scholarityColumn, occupationColumn;
    @FXML
    private TabPane tabPane;
    @FXML
    private DatePicker birthday, startDate;
    @FXML
    private Spinner<Integer> rooms, bedrooms;
    @FXML
    private CheckBox godfather, deadFather, godmother, deadMother;
    @FXML
    private ChoiceBox<String> lgMaritalStatus, residenceKind, buildingType, schoolGrade, referralInstitution, kinScholarity;
    @FXML
    private Button nextBtn0, nextBtn1, nextBtn2,
            prevBtn1, prevBtn2, prevBtn3, addKin;
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
            schoolName, learningDifficultyObs, otherReferral, scholarityObs,                        //
            kinName, kinAge, kinship, kinOccupation, kinIncome;                                     //

    private ArrayList<Control> page1 = new ArrayList<>();
    /*
     *  ============================================    FXML Variables End    =============================================
     *  ============================================    Autowired Variables    ============================================
     */
    @Autowired
    private UserRepository userRepository;
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
    @Autowired
    private RegisterUtils registerUtils;
    /*
     *  ==========================================    Autowired Variables End    ==========================================
     */
    private int age;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        page1.addAll(Arrays.asList(name, birthday, ageTxt, naturality, fatherName, motherName, addressStreet,
                addressNumber, addressDistrict, addressComplement, addressReference));
        configureKinTable();
        configPickers();
        configToggles();

    }

    @FXML
    public void birthdayOnAction(ActionEvent event) {
        if (!registerUtils.isValidControl(birthday)) {
            birthday.getEditor().clear();
            ageTxt.clear();
            return;
        }
        age = Period.between(birthday.getValue(), LocalDate.now()).getYears();
        ageTxt.setText(String.valueOf(age));
        /*if (birthday.getValue().isAfter(LocalDate.now())) {
            //"Data de nascimento não pode ser maior que hoje");
            birthday.getEditor().clear();
            ageTxt.clear();
            invalidControl(birthday, "Data Inválida");
            return;
        }
        validControl(birthday);*/

    }

    @FXML
    public void nextBtnOnAction(ActionEvent event) {
        if (!registerUtils.validateControlList(page1)) {
            ScreensEnum.showPopup("Há campos inválidos\npara completar");
        } else {
            tabPane.getSelectionModel().selectNext();
        }
    }

    @FXML
    public void prevBtnOnAction(ActionEvent event) {
        tabPane.getSelectionModel().selectPrevious();
    }

    @FXML
    public void registerBtnOnAction(ActionEvent event) {

        try {
            User user = createUser();
            userRepository.save(user);
            addressRepository.save(user.getAddress());
            habitationRepository.save(user.getHabitation());
            healthRepository.save(user.getHealth());
            scholarityRepository.save(user.getScholarity());
            socialAssistanceRepository.save(user.getSocialAssistance());
            ScreensEnum.showPopup("Cadastro completo!");
        } catch (CreateUserException e) {
            ScreensEnum.showPopup(e.getMessage() + "Houve um problema para\n completar o registro!");
        }
    }

    public void addKinColumn(ActionEvent event) {
        kinshipTableView.getItems().add(
                new Kin(kinName.getText(),
                        Integer.parseInt(kinAge.getText()),
                        kinship.getText(),
                        "X",
                        kinScholarity.getValue(),
                        kinOccupation.getText(),
                        Double.parseDouble(kinIncome.getText())
                ));
        kinName.clear();
        kinAge.clear();
        kinship.clear();
        kinScholarity.getSelectionModel().clearSelection();
        kinOccupation.clear();
        kinIncome.clear();
    }

    private void configureKinTable() {
        attendedColumn.setText("Possui irmãos que\nfrequentam a entidade?");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        kinshipColumn.setCellValueFactory(new PropertyValueFactory<>("kinship"));
        attendedColumn.setCellValueFactory(new PropertyValueFactory<>("attended"));
        scholarityColumn.setCellValueFactory(new PropertyValueFactory<>("scholarity"));
        occupationColumn.setCellValueFactory(new PropertyValueFactory<>("occupation"));
        incomeColumn.setCellValueFactory(new PropertyValueFactory<>("income"));
    }

    private void configPickers() {
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
        kinScholarity.getItems().addAll("Nenhuma", "Ensino Fundamental", "Ensino Médio", "Ensino Superior", "Pós-graduação");
    }

    private User createUser() throws CreateUserException {
        //Cria um aluno com os atributos setados
        final User user = User.builder()
                .name(name.getText())
                .birthday(birthday.getValue())
                .age(age)
                .naturality(naturality.getText())
                .sexo(registerUtils.isSelectedTrue(sexoRb, "Masculino") ? 'M' : 'F')
                .fatherName(fatherName.getText())
                .godfather(godfather.isSelected())
                .deadFather(deadFather.isSelected())
                .motherName(motherName.getText())
                .godmother(godmother.isSelected())
                .deadMother(deadMother.isSelected())
                .phone(phone.getText())
                .messagePhone(messagePhone.getText())
                .startDate(startDate.getValue())
                .attendingShift(registerUtils.getSelectedValue(attendingShiftRb))
                .referralInstitution(referralInstitution.getValue().equals("Outros:")
                        ? otherReferral.getText() : referralInstitution.getValue())
                .build();

        final Address address = Address.builder()
                .street(addressStreet.getText())
                .number(addressNumber.getText())
                .district(addressDistrict.getText())
                .complement(addressComplement.getText())
                .reference(addressReference.getText())
                .user(user)
                .build();

        final Habitation habitation = Habitation.builder()
                .residenceKind(residenceKind.getValue().equals("Outros:")
                        ? otherResidenceKind.getText() : residenceKind.getValue())
                .privatePipedWater(registerUtils.isSelectedTrue(pipedWaterRb, "Água encanada particular"))
                .privateEletricity(registerUtils.isSelectedTrue(eletricLightRb, "Luz elétrica particular"))
                .sewer(registerUtils.isSelectedTrue(sewerRb, "Sim"))
                .buildingType(buildingType.getValue().equals("Outros:")
                        ? otherBuildingType.getText() : buildingType.getValue())
                .rooms(rooms.getValue())
                .bedrooms(bedrooms.getValue())
                .user(user)
                .build();

        Health health = Health.builder()
                .physicalIllness(registerUtils.isSelectedTrue(physicalHealthRb, "Sim:"))
                .physicalObs(registerUtils.isSelectedTrue(physicalHealthRb, "Sim:") ? physicalObs.getText() : null)
                .mentalIllness(registerUtils.isSelectedTrue(mentalHealthRb, "Sim:"))
                .mentalObs(registerUtils.isSelectedTrue(mentalHealthRb, "Sim:") ? mentalObs.getText() : null)
                .medicalMonitoring(registerUtils.isSelectedTrue(medicalRb, "Sim:"))
                .medicalObs(registerUtils.isSelectedTrue(medicalRb, "Sim:") ? medicalObs.getText() : null)
                .continuousRemedy(registerUtils.isSelectedTrue(remedyRb, "Sim:"))
                .remedyObs(registerUtils.isSelectedTrue(remedyRb, "Sim:") ? remedyObs.getText() : null)
                .user(user)
                .build();

        Scholarity scholarity = Scholarity.builder()
                .schoolName(schoolName.getText())
                .schoolShift(registerUtils.getSelectedValue(schoolShiftRb))
                .grade(schoolGrade.getValue())
                .learningDifficulty(registerUtils.isSelectedTrue(learningDifficultyRb, "Sim:"))
                .learningDifficultyObs(registerUtils.isSelectedTrue(learningDifficultyRb, "Sim:") ? learningDifficultyObs.getText() : null)
                .scholarityObs(scholarityObs.getText())
                .user(user)
                .build();

        SocialAssistance socialAssistance = SocialAssistance.builder()
                .nis(nis.getText())
                .cras(cras.getText())
                .bolsaFamilia(registerUtils.isSelectedTrue(bolsaFamiliaRb, "Sim?:"))
                .bolsaFamiliaObs(registerUtils.isSelectedTrue(bolsaFamiliaRb, "Sim?:") ? bolsaFamiliaObs.getText() : null)
                .otherAssistances(registerUtils.isSelectedTrue(otherAssistancesRb, "Sim:"))
                .otherAssistancesObs(registerUtils.isSelectedTrue(otherAssistancesRb, "Sim:") ? otherAssistancesObs.getText() : null)
                .user(user)
                .build();

        user.setAddress(address);
        user.setHabitation(habitation);
        user.setHealth(health);
        user.setScholarity(scholarity);
        user.setSocialAssistance(socialAssistance);

        return user;
    }

    private void configToggles() {
        configToggle(physicalHealthRb, physicalObs);
        configToggle(mentalHealthRb, mentalObs);
        configToggle(remedyRb, remedyObs);
        configToggle(medicalRb, medicalObs);
    }

    private void configToggle(ToggleGroup group, TextField textField) {
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (registerUtils.isSelectedTrue(group, "Sim:"))
                    textField.setDisable(false);
                else
                    textField.setDisable(true);
            }
        });
    }
}

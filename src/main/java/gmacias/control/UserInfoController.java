package gmacias.control;

import gmacias.control.enums.ScreensEnum;
import gmacias.model.entity.Address;
import gmacias.model.entity.Health;
import gmacias.model.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

@Controller
@FxmlView("../view/userInfo.fxml")
public class UserInfoController implements Initializable {

    @FXML
    private Label name, birthday, age, naturality, sexo, fatherName, motherName,
            address_street, address_number, address_district, address_complement, address_reference,
            phone, messagePhone, health_physicalIllness, health_physicalObs, health_mentalIllness, health_mentalObs,
            health_medicalMonitoring, health_medicalObs, health_continuousRemedy, health_remedyObs;

    private ArrayList<Label> labels = new ArrayList<>();

    private User user;

    @Autowired
    private DocUtils docUtils;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labels.addAll(List.of(
                name, birthday, age, naturality, sexo, fatherName, motherName,
                address_street, address_number, address_district, address_complement, address_reference,
                phone, messagePhone, health_physicalIllness, health_physicalObs, health_mentalIllness, health_mentalObs,
                health_medicalMonitoring, health_medicalObs, health_continuousRemedy, health_remedyObs));
    }

    public void updateInfo(User user) {
        this.user = user;
        Field field;
        String text;
        for (Label label : labels) {
            try {
                if (label.getId().contains("address_")) {
                    field = Address.class.getDeclaredField(label.getId().replace("address_", ""));
                    field.setAccessible(true);
                    text = field.get(user.getAddress()).toString();
                } else if (label.getId().contains("health_")) {
                    field = Health.class.getDeclaredField(label.getId().replace("health_", ""));
                    field.setAccessible(true);
                    if (field.get(user.getHealth()) instanceof Boolean) {
                        text = ((Boolean) field.get(user.getHealth())) ? "Sim" : "Não";
                    } else
                        text = field.get(user.getHealth()) == null ? "" : field.get(user.getHealth()).toString();
                } else {
                    field = User.class.getDeclaredField(label.getId());
                    field.setAccessible(true);
                    text = field.get(user).toString();
                }
                label.setText(text);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Não existe o field " + label.getId() + " no user");
            }
        }
    }

    @FXML
    void documentOnAction(ActionEvent event) {
        try {
            docUtils.generateDoc(this.user);
            ScreensEnum.showPopup("Documento criado!");
        }catch (Exception e){
            ScreensEnum.showPopup("Erro ao criar documento");
        }
    }
}

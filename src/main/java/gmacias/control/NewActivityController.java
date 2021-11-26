package gmacias.control;

import gmacias.control.repositories.UserRepository;
import gmacias.model.entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/newActivity.fxml")
public class NewActivityController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private DatePicker date;

    @FXML
    private ToggleGroup shiftRb;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, Boolean> selectedColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @Autowired
    private UserRepository userRepository;

    private ObservableList<User> userListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedColumn.setCellValueFactory(
                new PropertyValueFactory<>("selected"));
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        selectedColumn.setCellFactory(
                CheckBoxTableCell.forTableColumn(selectedColumn));
    }

    public void updateTable(){
        userListView = FXCollections.observableList(userRepository.findAllByOrderByName());
        usersTable.setItems(userListView);
    }
}

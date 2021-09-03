package org.openjfx.control;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.repositories.StudentRepository;
import org.openjfx.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/list.fxml")
public class ListController implements Initializable {

    @FXML
    private TableView<Student> studentTableView;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Integer> ageColumn;

    @FXML
    private TableColumn<Student, String> fatherNameColumn;

    @FXML
    private TableColumn<Student, String> motherNameColumn;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        fatherNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("fatherName"));
        motherNameColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("motherName"));
        updateTable();
    }

    private void updateTable(){
        studentTableView.setItems(FXCollections.observableList(studentRepository.findAll()));
    }
}

package org.openjfx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.repositories.StudentRepository;
import org.openjfx.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/list.fxml")
public class ListController implements Initializable {

    @FXML
    private TableView<Student> studentTableView;

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private TextField searchBar;

    private FilteredList<Student> studentsList;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void filterList(ActionEvent event) {
        studentsList.setPredicate(student -> student.getName().toUpperCase().contains(searchBar.getText().toUpperCase()));
    }

    @FXML
    void ListItemClick(MouseEvent event) {
        if (studentListView.getSelectionModel().isEmpty())
            return;
        studentListView.getSelectionModel().getSelectedItem();
        //Escolher opção ou gerar pdf?
        studentListView.getSelectionModel().clearSelection();
    }

    public void updateTable() {
        studentsList = new FilteredList<Student>(FXCollections.observableList(studentRepository.findAllByOrderByName()));
        studentListView.setItems(studentsList);
        //studentsList.setPredicate(null);
    }
}

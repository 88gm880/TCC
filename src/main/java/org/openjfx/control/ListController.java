package org.openjfx.control;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.openjfx.control.enums.ScreensEnum;
import org.openjfx.control.repositories.UserRepository;
import org.openjfx.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("../view/list.fxml")
public class ListController implements Initializable {

    @FXML
    private TableView<User> userTableView;

    @FXML
    private ListView<User> userListView;

    @FXML
    private TextField searchBar;

    private FilteredList<User> usersList;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocUtils docUtils;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configListView();
    }

    public void filterList(KeyEvent event) {
        usersList.setPredicate(user -> user.getName().toUpperCase().contains(searchBar.getText().toUpperCase()));
    }

    @FXML
    void ListItemClick(MouseEvent event) {
        if (userListView.getSelectionModel().isEmpty())
            return;
        //Escolher opção ou gerar pdf?
        docUtils.generateDoc(userListView.getSelectionModel().getSelectedItem());
        ScreensEnum.showPopup((userListView.getSelectionModel().getSelectedItem()).getName());
        userListView.getSelectionModel().clearSelection();
    }

    public void updateTable() {
        usersList = new FilteredList<>(FXCollections.observableList(userRepository.findAllByOrderByName()));
        userListView.setItems(usersList);
    }

    private void configListView(){
        userListView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<User>() {

                    @Override
                    protected void updateItem(User item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });
    }
}

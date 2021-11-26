package gmacias.control;

import gmacias.control.enums.ScreensEnum;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import gmacias.control.repositories.UserRepository;
import gmacias.model.entity.User;
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

    @FXML
    private CheckBox shuttedUsers;

    private FilteredList<User> usersList;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoController userInfoController;

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
        //docUtils.generateDoc(userListView.getSelectionModel().getSelectedItem());
        showUserInfo(userListView.getSelectionModel().getSelectedItem());
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

    private void showUserInfo(User user){
        userInfoController.updateInfo(user);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(user.getName() + " detalhes");
        window.setResizable(false);
        VBox userInfo = (VBox) ScreensEnum.userInfo.getNode();
        try{
            window.setScene(new Scene(userInfo, 870, 570));
        }catch (Exception e) {
            window.setScene(userInfo.getScene());
        }
        window.show();
    }

    public void shuttedUsersOnAction(ActionEvent actionEvent) {
    }
}

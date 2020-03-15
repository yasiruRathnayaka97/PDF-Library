package Controllers;
import Models.AccountManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.*;

public class HistoryController implements Initializable{
    @FXML
    private JFXListView listView;
    @FXML
    private JFXButton btnOpen;
    @FXML
    private JFXButton btnRemove;

    private AccountManager account=AccountManager.getAccount();
    private ObservableList history = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setItems(history);
    }

    public void clickOpen(MouseEvent mouseEvent) {
        System.out.println(listView.getSelectionModel().getSelectedItem());
    }

    public void clickRemove(MouseEvent mouseEvent) {
        history.remove(listView.getSelectionModel().getSelectedIndex());
    }
}
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FavouriteController implements Initializable {
    @FXML
    private JFXListView listView;
    @FXML
    private JFXButton btnOpen;
    @FXML
    private JFXButton btnRemove;


    private ObservableList fav = FXCollections.observableArrayList("RollsRoyce","BMW","Bently");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setItems(fav);
    }
    public void clickOpen(MouseEvent mouseEvent) {
        System.out.println(listView.getSelectionModel().getSelectedItem());
    }

    public void clickRemove(MouseEvent mouseEvent) {
        fav.remove(listView.getSelectionModel().getSelectedIndex());
    }
}

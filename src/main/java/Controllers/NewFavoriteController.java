package Controllers;

import Models.AlertManager;
import Models.FavoriteManager;
import Models.WindowManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class NewFavoriteController implements Initializable{
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXComboBox comboCategory;
    @FXML
    private JFXTextField textNew;

    private WindowManager windowManager;
    private FavoriteManager favouriteManager;
    private AlertManager alertManager;
    private ObservableList<String> categories;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        alertManager = AlertManager.getInstance();
        favouriteManager = FavoriteManager.getInstance();
        categories = favouriteManager.getCategoryNames();
        categories.add("Add category");
        comboCategory.setItems(categories);
        textNew.setVisible(false);

        //add listener
        comboCategory.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
            if(newValue.equals("Add category")){
                textNew.setVisible(true);
            }
            else{
                textNew.setVisible(false);
            }
        });
    }

    public void addFavourite(MouseEvent mouseEvent) throws Exception {
        String res;
        if(comboCategory.getSelectionModel().getSelectedItem().equals("Add category")){
            res = favouriteManager.addFavorite(true,textNew.getText());
        }
        else{
            res = favouriteManager.addFavorite(false,comboCategory.getSelectionModel().getSelectedItem().toString());

        }
        if(res.equals("Enter new category name!")){
            alertManager.showAlert("Enter new category name!");
            return;
        }
        if(res.equals("Category is already existed!")){
            alertManager.showAlert("Category is already existed!");
            return;
        }
        alertManager.showAlert("Successfully added to favorites!");
        windowManager.closeWindow((Stage) btnAdd.getScene().getWindow());
    }

    public void cancel(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnAdd.getScene().getWindow());
    }
}

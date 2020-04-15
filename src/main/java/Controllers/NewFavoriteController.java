package Controllers;

import Models.CategoryItem;
//import Models.FavouriteManager;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class NewFavoriteController {
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXComboBox comboCategory;
    @FXML
    private JFXTextField textNew;

    private WindowManager windowManager;
    //private FavouriteManager favouriteManager;
    private ObservableList<String> categories;

    //@Override
    //public void initialize(URL url, ResourceBundle resourceBundle) {
        /*windowManager = new WindowManager();
        favouriteManager = FavouriteManager.getInstance();
        categories = favouriteManager.getCategoryName();
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

    public void addFavourite(MouseEvent mouseEvent) {
        if(comboCategory.getSelectionModel().getSelectedItem().equals("Add category")){
            favouriteManager.addFavorite(true,textNew.getText());
        }
        else{
            favouriteManager.addFavorite(false,comboCategory.getSelectionModel().getSelectedItem().toString());
        }
        windowManager.closeWindow((Stage) btnAdd.getScene().getWindow());
    }

    public void cancel(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnAdd.getScene().getWindow());
    }*/
}

package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {
    @FXML
    private JFXComboBox<String> comboFavCategory;
    @FXML
    private TableView<FavoriteItem> tableFav;
    @FXML
    private TableColumn<FavoriteItem,String> colFavPath;
    @FXML
    private TableColumn<FavoriteItem,String> colFavKeyword;
    @FXML
    private TableColumn<FavoriteItem,String> colFavSearchType;
    @FXML
    private JFXButton btnClose;

    private PdfManager pdfManager;
    private AlertManager alertManager;
    private WindowManager windowManager;
    private FavoriteManager favoriteManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        favoriteManager = FavoriteManager.getInstance();
        pdfManager = new PdfManager();
        alertManager = AlertManager.getInstance();
        windowManager = new WindowManager();
        comboFavCategory.setItems(favoriteManager.getCategoryNames());

        tableFav.setPlaceholder(new Label("No favorite items"));
        colFavKeyword.prefWidthProperty().bind(tableFav.widthProperty().multiply(0.3));
        colFavSearchType.prefWidthProperty().bind(tableFav.widthProperty().multiply(0.2));
        colFavPath.prefWidthProperty().bind(tableFav.widthProperty().multiply(0.5));

        colFavPath.setCellValueFactory(new PropertyValueFactory<FavoriteItem,String>("path"));
        colFavKeyword.setCellValueFactory(new PropertyValueFactory<FavoriteItem,String>("keyword"));
        colFavSearchType.setCellValueFactory(new PropertyValueFactory<FavoriteItem,String>("searchType"));
        //add listener
        comboFavCategory.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
            tableFav.setItems(favoriteManager.getFavorites(newValue));
        });
    }

    public void deleteCategory(MouseEvent mouseEvent) {
        try {
            favoriteManager.deleteCategory(comboFavCategory.getSelectionModel().getSelectedItem());
            comboFavCategory.setItems(favoriteManager.getCategoryNames());
        }catch (Exception e){
            try {
                alertManager.showAlert("No category has been selected!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteFav(MouseEvent mouseEvent) {
        boolean res = favoriteManager.deleteFav(tableFav.getFocusModel().getFocusedItem(), comboFavCategory.getSelectionModel().getSelectedItem());
        if(!res){
            try {
                alertManager.showAlert("No item has been selected!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearFav(MouseEvent mouseEvent) {
        try {
            favoriteManager.clearFav(comboFavCategory.getSelectionModel().getSelectedItem());
        }catch (Exception e){
            try {
                alertManager.showAlert("No category has been selected!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void openFav(MouseEvent mouseEvent) {
        try {
            pdfManager.openPdf(tableFav.getFocusModel().getFocusedItem().getPath());
        }catch (Exception e){
            try {
                alertManager.showAlert("No item has been selected!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void close(MouseEvent mouseEvent){
        windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
    }
}

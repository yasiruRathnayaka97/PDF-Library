package Controllers;

import Models.FavoriteItem;
import Models.FavoriteManager;
import Models.FileManager;
import Models.PdfManager;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {
    private FavoriteManager favoriteManager;
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

    private PdfManager pdfManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        favoriteManager = FavoriteManager.getInstance();
        pdfManager = new PdfManager();
        comboFavCategory.setItems(favoriteManager.getCategoryNames());

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
        favoriteManager.deleteCategory(comboFavCategory.getSelectionModel().getSelectedItem());
        comboFavCategory.setItems(favoriteManager.getCategoryNames());
    }

    public void deleteFav(MouseEvent mouseEvent) {
        favoriteManager.deleteFav(tableFav.getSelectionModel().getSelectedItem(),comboFavCategory.getSelectionModel().getSelectedItem());
    }

    public void clearFav(MouseEvent mouseEvent) {
        favoriteManager.clearFav(comboFavCategory.getSelectionModel().getSelectedItem());
    }

    public void openFav(MouseEvent mouseEvent) {
        pdfManager.openPdf(tableFav.getSelectionModel().getSelectedItem().getPath());
    }
}

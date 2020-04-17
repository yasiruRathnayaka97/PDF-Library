package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnUser;
    @FXML
    private AnchorPane paneUser;
    @FXML
    private AnchorPane paneHome;
    @FXML
    private AnchorPane paneFav;
    @FXML
    private AnchorPane paneHistory;


    private WindowManager windowManager;
    private AccountManager accountManager;
    private boolean maximized;

    //home pane
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXListView listViewResult;
    @FXML
    private JFXComboBox dropDownSearchType;
    private ObservableList<String> searchTypes;
    private IndexManager indexManager;
    private List<String> searchResult;
    private String keyword;
    private String searchType;
    private AlertManager alertManager;
    private HistoryManager historyManager;
    private FileManager fileManager;
    private PdfManager pdfManager;

    //user vertical box
    @FXML
    private VBox vBoxUser;

    //favorite pane
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

    //History pane
    @FXML
    private TableView<HistoryItem> historyTable;
    @FXML
    private TableColumn<HistoryItem,String> colKeyword;
    @FXML
    private TableColumn<HistoryItem,String> colType;
    @FXML
    private TableColumn<HistoryItem,String> colDirectory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowManager = new WindowManager();
        accountManager = AccountManager.getInstance();
        maximized = false;
        btnUser.setText(accountManager.getUsername());

        //home pane
        alertManager = AlertManager.getInstance();
        historyManager = HistoryManager.getInstance();
        fileManager = new FileManager();
        searchTypes = FXCollections.observableArrayList("content","pdfName","path");
        dropDownSearchType.setItems(searchTypes);
        pdfManager = new PdfManager();

        //favorite pane
        favoriteManager = FavoriteManager.getInstance();
        comboFavCategory.setItems(favoriteManager.getCategoryNames());

        colFavPath.setCellValueFactory(new PropertyValueFactory<FavoriteItem,String>("path"));
        colFavKeyword.setCellValueFactory(new PropertyValueFactory<FavoriteItem,String>("keyword"));
        colFavSearchType.setCellValueFactory(new PropertyValueFactory<FavoriteItem,String>("searchType"));
        //add listener
        comboFavCategory.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
            tableFav.setItems(favoriteManager.getFavorites(newValue));
        });



        //history pane
        colKeyword.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("keyword"));
        colType.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("type"));
        colDirectory.setCellValueFactory(new PropertyValueFactory<HistoryItem,String>("directory"));
        historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
        colKeyword.prefWidthProperty().bind(historyTable.widthProperty().multiply(0.3));
        colType.prefWidthProperty().bind(historyTable.widthProperty().multiply(0.2));
        colDirectory.prefWidthProperty().bind(historyTable.widthProperty().multiply(0.5));

    }

    //----------------------------------------------------------------------------------------------------------------------------------
    //vertical box
    public void handleUser(MouseEvent mouseEvent) {
        vBoxUser.toFront();
    }

    public void handleHome(MouseEvent mouseEvent) {
        paneHome.toFront();
    }

    public void handleDirectory(MouseEvent mouseEvent) {
        windowManager.dirWindow();
    }

    public void handleFav(MouseEvent mouseEvent) {
        paneFav.toFront();
    }

    public void handleHistory(MouseEvent mouseEvent) {
        paneHistory.toFront();
    }

    //------------------------------------------------------------------------------------------------------------------------------
    //Menu bar

    public void handleResize(MouseEvent mouseEvent) {
        maximized = !maximized;
        windowManager.maximizedWindow((Stage) btnClose.getScene().getWindow(),maximized);
    }

    public void handleClose(MouseEvent mouseEvent) {
        windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
    }

    public void handleMinimize(MouseEvent mouseEvent) {
        windowManager.minimizeWindow((Stage) btnClose.getScene().getWindow());
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    //user pane

    public void changeUsername(MouseEvent mouseEvent){
        accountManager.setChangeUsername(true);
        changeUsernamePassword();
    }

    public void changePassword(MouseEvent mouseEvent){
        accountManager.setChangeUsername(false);
        changeUsernamePassword();
    }

    public void changeUsernamePassword(){
        try {
            windowManager.stageLoader("../ChangeWindow.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(MouseEvent mouseEvent){
        accountManager.deleteAccount();
        windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
        try {
            windowManager.stageLoader("SignInSignUp.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signOut(MouseEvent mouseEvent){
        accountManager.signOut();
        windowManager.closeWindow((Stage) btnClose.getScene().getWindow());
        try {
            windowManager.stageLoader("../SignInSignUp.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------
    //Home Pane

    public void clickSearch(MouseEvent mouseEvent) {
        try {
            indexManager = IndexManager.getInstance();
            //check for empty search keywords
            if (textSearch.getText().equals("")) {
                alertManager.showAlert("Enter searching keyword!");
                return;
            }

            //check path is choose
            if (indexManager.getPaths() == null) {
                alertManager.showAlert("Select searching directory!");
                return;
            }

            //check for search Type has selected
            if (dropDownSearchType.getValue() == null) {
                alertManager.showAlert("Select search type directory!");
                return;
            }

            //call search manager
            SearchManager searchManager = new SearchManager();
            searchType = dropDownSearchType.getValue().toString();
            keyword = textSearch.getText();
            searchResult = searchManager.search("./Index", searchType, keyword);

            //display search result in list view
            listViewResult.setItems(FXCollections.observableArrayList(searchResult));

            historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFile(MouseEvent mouseEvent) {
        pdfManager.openPdf(listViewResult.getSelectionModel().getSelectedItem().toString());
    }

    public void addFav(MouseEvent mouseEvent) {
        try {
            favoriteManager.setPath(listViewResult.getSelectionModel().getSelectedItem().toString());
            favoriteManager.setKeyword(textSearch.getText());
            favoriteManager.setSearchType(dropDownSearchType.getSelectionModel().getSelectedItem().toString());
            windowManager.stageLoader("../NewFavorite.fxml",true,null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveSearch(MouseEvent mouseEvent) {

    }

    //--------------------------------------------------------------------------------------------
    //favorite pane

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

    //-------------------------------------------------------------------------------
    //History pane
    public void deleteHisoryFile(MouseEvent mouseEvent) {
        historyManager.deleteOne(historyTable.getSelectionModel().getSelectedItem().getId());
        historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
    }

    public void clearHisoryFile(MouseEvent mouseEvent) {
        historyManager.deleteAll();
        historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
    }
}

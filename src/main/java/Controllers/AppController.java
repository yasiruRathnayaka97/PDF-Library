package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private AnchorPane paneFavourite;
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
    }

    //----------------------------------------------------------------------------------------------------------------------------------
    //vertical box
    public void handleUser(MouseEvent mouseEvent) {
        paneUser.toFront();
    }

    public void handleHome(MouseEvent mouseEvent) {
        paneHome.toFront();
    }

    public void handleDirectory(MouseEvent mouseEvent) {
        windowManager.dirWindow();
    }

    public void handleFavourite(MouseEvent mouseEvent) {
        paneFavourite.toFront();
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFile(MouseEvent mouseEvent) {
        pdfManager.openPdf(listViewResult.getSelectionModel().getSelectedItem().toString());
    }

    public void addFavourite(MouseEvent mouseEvent) {

    }

    public void saveSearch(MouseEvent mouseEvent) {

    }
}

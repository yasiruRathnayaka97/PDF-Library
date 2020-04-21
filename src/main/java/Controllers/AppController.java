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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private JFXButton btnUser;
    @FXML
    private AnchorPane paneHome;

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
    private FavoriteManager favoriteManager;
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
        searchTypes = FXCollections.observableArrayList("content","pdfName");
        dropDownSearchType.setItems(searchTypes);
        pdfManager = new PdfManager();
        favoriteManager = FavoriteManager.getInstance();

    }

    //----------------------------------------------------------------------------------------------------------------------------------
    //vertical box
    public void handleUser(MouseEvent mouseEvent) {
        try {
            windowManager.stageLoader("../User.fxml",false,"Favorite");
            windowManager.closeWindow((Stage) btnUser.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleHome(MouseEvent mouseEvent) {
        paneHome.toFront();
    }

    public void handleDirectory(MouseEvent mouseEvent) {
        windowManager.dirWindow();//methana error ekak enawa
    }

    public void handleFav(MouseEvent mouseEvent) {
        try {
            windowManager.stageLoader("../Favorite.fxml",false,"Favorite");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleHistory(MouseEvent mouseEvent) {
        try {
            windowManager.stageLoader("../History.fxml",false,"History");
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
            searchResult = searchManager.search( searchType, keyword);

            //display search result in list view
            if (!searchResult.isEmpty()){
                listViewResult.setItems(FXCollections.observableArrayList(searchResult));
            }
            else{
                ArrayList<String> out=new ArrayList<String>();
                out.add(keyword+" not match for any "+searchType);
                listViewResult.setItems(FXCollections.observableArrayList(out));
            }


            //historyTable.setItems(FXCollections.observableArrayList(historyManager.getHistory()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFile(MouseEvent mouseEvent) {
        String[] arr=listViewResult.getSelectionModel().getSelectedItem().toString().split(" :: ");
        pdfManager.openPdf(arr[arr.length-1].trim());
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

}

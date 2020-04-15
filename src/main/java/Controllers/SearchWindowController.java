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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SearchWindowController implements Initializable {
    @FXML
    private JFXButton buttonSearch;
    @FXML
    private JFXButton buttonUser;
    @FXML
    private JFXButton buttonFav;
    @FXML
    private JFXButton buttonFolder;
    @FXML
    private JFXButton buttonHistory;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXComboBox dropDownSearchType;
    @FXML
    private JFXTextField textSearch;
    @FXML
    private JFXListView listViewResult;


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
        alertManager = AlertManager.getInstance();
        historyManager = HistoryManager.getInstance();
        fileManager = new FileManager();


        //set search types items
        searchTypes = FXCollections.observableArrayList("content","pdfName","path");
        dropDownSearchType.setItems(searchTypes);
    }

    //handle search operation
    public void clickSearch(MouseEvent mouseEvent) throws Exception {
        indexManager = IndexManager.getInstance();
        //check for empty search keywords
        if(textSearch.getText().equals("")){
            alertManager.showAlert("Enter searching keyword!");
            return;
        }

        //check path is choose
        if(indexManager.getPaths() == null){
            alertManager.showAlert("Select searching directory!");
            return;
        }

        //check for search Type has selected
        if(dropDownSearchType.getValue()==null){
            alertManager.showAlert("Select search type directory!");
            return;
        }

        //call search manager
        //SearchManager searchManager = new SearchManager(IndexManager.getInstance());
        searchType = dropDownSearchType.getValue().toString();
        keyword = textSearch.getText();
        //searchResult = searchManager.search("./Index",searchType,keyword);

        //display search result in list view
        listViewResult.setItems(FXCollections.observableArrayList(searchResult));
    }

    public void openFile(MouseEvent mouseEvent) {
        System.out.println("pdf trying to opening");
        System.out.println(listViewResult.getSelectionModel().getSelectedItem().toString());
        pdfManager.openPdf(listViewResult.getSelectionModel().getSelectedItem().toString());
    }

    public void addFav(MouseEvent mouseEvent) {

    }

    //add favourite
    /*public void addFavourite(MouseEvent mouseEvent) {
        FavouriteManager favouriteManager = new FavouriteManager();
        favouriteManager.insertFavourite((String) listViewResult.getSelectionModel().getSelectedItem(),keyword,searchType);
    }*/
}

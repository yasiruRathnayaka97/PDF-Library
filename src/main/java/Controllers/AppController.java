package Controllers;

import Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.ui.CommonDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AppController implements Initializable {
    @FXML
    private JFXButton buttonSearch;
    @FXML
    private JFXButton buttonUser;
    @FXML
    private JFXButton buttonFavourite;
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
    @FXML
    private JFXButton btnaddFavourite;

    private Stage stage;
    private DirectoryChooser dirChooser;
    private Scene scene;
    private Parent root;

    private File file;
    private List<String> files;
    private ObservableList<String> searchTypes;
    private String path;
    private List<String> paths;
    private IndexManager indexManager;
    private List<String> searchResult;
    private String keyword;
    private String searchType;

    private double xOffset = 0;
    private double yOffset = 0;

    private CommonStore commonStore;

    FileManager fileManager = new FileManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commonStore = CommonStore.getInstance();
        paths = new ArrayList<String>();

        //set search types items
        searchTypes = FXCollections.observableArrayList("content","pdfName","path");
        dropDownSearchType.setItems(searchTypes);
    }

    //handle search operation
    public void clickSearch(MouseEvent mouseEvent) throws Exception {
        //check for empty search keywords
        if(textSearch.getText().equals("")){
            commonStore.showAlert("Enter searching keyword!");
            return;
        }

        //check path is choose
        if(paths==null){
            commonStore.showAlert("Select searching directory!");
            return;
        }

        //check for search Type has selected
        if(dropDownSearchType.getValue()==null){
            commonStore.showAlert("Select search type directory!");
            return;
        }

        //call search manager
        SearchManager searchManager = new SearchManager(commonStore.getIndexManager());
        searchType = dropDownSearchType.getValue().toString();
        keyword = textSearch.getText();
        searchResult = searchManager.search("./Index",searchType,keyword);

        //display search result in list view
        listViewResult.setItems(FXCollections.observableArrayList(searchResult));

        //add to history
        HistoryManager historyManager = new HistoryManager();
        historyManager.insertHistorySearchKeyword(keyword,file.getAbsolutePath());
    }

    //open user stage
    public void clickUser(MouseEvent mouseEvent) throws Exception{
            commonStore.stageLoader("../User.fxml",true,null);
    }

    //load favourite window
    public void clickFavourite(MouseEvent mouseEvent) throws Exception{
        commonStore.stageLoader("../Favourite.fxml",false,"Favourite");
    }

    //load directory chooser
    public void clickFolder(MouseEvent mouseEvent) throws Exception {
        stage = (Stage)anchorPane.getScene().getWindow();
        dirChooser = new DirectoryChooser();

        //get selected directory to file
        file = dirChooser.showDialog(stage);
        files = fileManager.getAllPDFUnderDir(file.getAbsolutePath());

        //save paths to common store
        commonStore.setFiles(files);

        //indexing
        commonStore.stageLoader("../IndexingLoader.fxml",true,null);
    }

    //load history window
    public void clickHistory(MouseEvent mouseEvent) throws Exception {
        commonStore.stageLoader("../History.fxml",false,"History");
    }

    //add favourite
    public void addFavourite(MouseEvent mouseEvent) {
        FavouriteManager favouriteManager = new FavouriteManager();
        favouriteManager.insertFavourite((String) listViewResult.getSelectionModel().getSelectedItem(),keyword,searchType);
    }

    //open selected file
    public void openFile(MouseEvent mouseEvent) {
        PdfManager pdfManager = new PdfManager();
        pdfManager.openPdf((String) listViewResult.getSelectionModel().getSelectedItem());
    }
}
